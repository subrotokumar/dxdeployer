package dev.subrotokumar.accounts.service.impl;

import static java.lang.String.format;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.net.HttpHeaders;

import dev.subrotokumar.accounts.dto.AuthenticationRequestDto;
import dev.subrotokumar.accounts.dto.AuthenticationResponseDto;
import dev.subrotokumar.accounts.dto.MagicLinkRequestDto;
import dev.subrotokumar.accounts.dto.RefreshTokenRequestDto;
import dev.subrotokumar.accounts.dto.RegisterAccountRequestDto;
import dev.subrotokumar.accounts.dto.TokenDto;
import dev.subrotokumar.accounts.entity.Account;
import dev.subrotokumar.accounts.entity.RefreshToken;
import dev.subrotokumar.accounts.entity.TokenType;
import dev.subrotokumar.accounts.exception.AccountAlreadyExistException;
import dev.subrotokumar.accounts.exception.AccountNotFoundException;
import dev.subrotokumar.accounts.kafka.LoginMagicLinkProducer;
import dev.subrotokumar.accounts.kafka.LoginMagiclink;
import dev.subrotokumar.accounts.mapper.AccountMapper;
import dev.subrotokumar.accounts.repository.AccountRepository;
import dev.subrotokumar.accounts.repository.RefreshTokenRepository;
import dev.subrotokumar.accounts.service.AuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    final private AccountRepository accountRepository;
    final private RefreshTokenRepository refreshTokenRepository;
    final private AccessJwtServiceImpl accessJwtService;
    final private RefreshJwtServiceImpl refreshJwtService;
    final private AuthenticationManager authenticationManager;
    final private LoginMagicLinkProducer loginMagicLinkProducer;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public void registerAccount(RegisterAccountRequestDto accountDto) {
        Account account = AccountMapper.registerAccountDtoToEntity(accountDto);
        account.setPassword(encoder.encode(account.getPassword()));
        var accounts = accountRepository.findByUsernameOrEmail(account.getUsername(), account.getPassword());
        if (!accounts.isEmpty()) {
            log.error(format("Account already exist => throws AccountAlreadyExistException"));
            throw new AccountAlreadyExistException("Account already exists");
        }
        accountRepository.save(account);
    }

    @Override
    public AuthenticationResponseDto refreshToken(RefreshTokenRequestDto request) {
        var username = refreshJwtService.extractUsername(request.getRefreshToken());
        var account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        var accessToken = accessJwtService.generateToken(account);
        var refreshToken = refreshJwtService.generateToken(account);

        refreshTokenRepository.save(
                RefreshToken
                        .builder()
                        .token(refreshToken)
                        .type(TokenType.REFRESH_TOKEN)
                        .tokenId(UUID.nameUUIDFromBytes(refreshToken.getBytes()))
                        .expiry(refreshJwtService.extractExpiration(refreshToken))
                        .build());

        return AuthenticationResponseDto
                .builder()
                .accessToken(
                        TokenDto
                                .builder()
                                .token(accessToken)
                                .expiry(accessJwtService.extractExpiration(accessToken))
                                .build())
                .refreshToken(
                        TokenDto
                                .builder()
                                .token(refreshToken)
                                .expiry(refreshJwtService
                                        .extractExpiration(refreshToken))
                                .build())
                .build();
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        log.info("Authorized");

        Account account = accountRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        log.info("Account info : " + account);
        var accessToken = accessJwtService.generateToken(account, account.getId());
        var refreshToken = refreshJwtService.generateToken(account, account.getId());

        refreshTokenRepository.save(
                RefreshToken
                        .builder()
                        .token(refreshToken)
                        .type(TokenType.REFRESH_TOKEN)
                        .tokenId(UUID.nameUUIDFromBytes(refreshToken.getBytes()))
                        .expiry(refreshJwtService.extractExpiration(refreshToken))
                        .build());

        var authResponse = AuthenticationResponseDto
                .builder()
                .accessToken(
                        TokenDto
                                .builder()
                                .token(accessToken)
                                .expiry(accessJwtService.extractExpiration(accessToken))
                                .build())
                .refreshToken(
                        TokenDto
                                .builder()
                                .token(refreshToken)
                                .expiry(refreshJwtService
                                        .extractExpiration(refreshToken))
                                .build())
                .build();

        String encodedAccessToken = Base64.getUrlEncoder().encodeToString(accessToken.getBytes());
        String encodedRefreshToken = Base64.getUrlEncoder().encodeToString(refreshToken.getBytes());

        ResponseCookie cookie1 = ResponseCookie
                .from("access_token", encodedAccessToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .build();

        ResponseCookie cookie2 = ResponseCookie
                .from("refresh_token", encodedRefreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie1.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, cookie2.toString());

        return authResponse;
    }

    @Override
    public void magiclink(MagicLinkRequestDto magiclinkRequest) {
        var account = accountRepository
                .findByUsernameOrEmail(magiclinkRequest.getUsername(), magiclinkRequest.getEmail())
                .orElseThrow(()-> new AccountNotFoundException("Account not found"));

        if(!account.isEmailVerified()){
                // return;
        }
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", account.getRole());
        extraClaims.put("type", "MAGIC_LINK");
        extraClaims.put("iss", "subrotokumar.dev");
        extraClaims.put("userId", account.getId());
        var magiclink = Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(account.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000*60*5)))
                .signWith(getMagiclinkSecretKey(), SignatureAlgorithm.HS256)
                .compact();
        loginMagicLinkProducer.sendLoginMagiclink(
                LoginMagiclink.builder()
                        .userId(account.getId())
                        .email(account.getEmail())
                        .magiclink(magiclink)
                        .username(account.getUsername())
                        .callbackUrl(null)
                        .build()
        );
    }

    @Override
    public AuthenticationResponseDto verifyMagicLink(String magicLink) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Key getMagiclinkSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode("magiclinkshajvdcjqw2eyuqy82dgiqgwayvxy8b29exyben98273e3gen3zyugny87e3");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
