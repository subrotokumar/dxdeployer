package dev.subrotokumar.accounts.service.impl;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.subrotokumar.accounts.dto.AuthenticationRequestDto;
import dev.subrotokumar.accounts.dto.AuthenticationResponseDto;
import dev.subrotokumar.accounts.dto.RefreshTokenRequestDto;
import dev.subrotokumar.accounts.dto.RegisterAccountRequestDto;
import dev.subrotokumar.accounts.dto.TokenDto;
import dev.subrotokumar.accounts.entity.Account;
import dev.subrotokumar.accounts.entity.RefreshToken;
import dev.subrotokumar.accounts.entity.TokenType;
import dev.subrotokumar.accounts.exception.AccountAlreadyExistException;
import dev.subrotokumar.accounts.exception.AccountNotFoundException;
import dev.subrotokumar.accounts.mapper.AccountMapper;
import dev.subrotokumar.accounts.repository.AccountRepository;
import dev.subrotokumar.accounts.repository.RefreshTokenRepository;
import dev.subrotokumar.accounts.service.AuthenticationService;
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

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public void registerAccount(RegisterAccountRequestDto accountDto) {
        Account account = AccountMapper.registerAccountDtoToEntity(accountDto);
        account.setPassword(encoder.encode(account.getPassword()));
        var accounts = accountRepository.findByUsernameOrEmail(account.getUsername(), account.getPassword());
        if (!accounts.isEmpty()) {
            log.error("Account already exist", AccountAlreadyExistException.class);
            throw new AccountAlreadyExistException("Account already exists");
        }
        accountRepository.save(account);
    }

    @Override
    public AuthenticationResponseDto refreshToken(RefreshTokenRequestDto request) {
        var username = refreshJwtService.extractUsername(request.getRefreshToken());
        var account = accountRepository.findByUsername(username).orElseThrow(() -> new AccountNotFoundException("Account not found"));

        var accessToken = accessJwtService.generateToken(account);
        var refreshToken = refreshJwtService.generateToken(account);

        refreshTokenRepository.save(
                RefreshToken
                        .builder()
                        .token(refreshToken)
                        .type(TokenType.REFRESH_TOKEN)
                        .tokenId(UUID.nameUUIDFromBytes(refreshToken.getBytes()))
                        .expiry(refreshJwtService.extractExpiration(refreshToken))
                        .build()
        );

        return AuthenticationResponseDto
                .builder()
                .accessToken(
                        TokenDto
                                .builder()
                                .token(accessToken)
                                .expiry(accessJwtService.extractExpiration(accessToken))
                                .build()
                )
                .refreshToken(
                        TokenDto
                                .builder()
                                .token(refreshToken)
                                .expiry(refreshJwtService.extractExpiration(refreshToken))
                                .build()
                )
                .build();
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        log.info("Authorised");

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
                        .build()
        );

        return AuthenticationResponseDto
                .builder()
                .accessToken(
                        TokenDto
                                .builder()
                                .token(accessToken)
                                .expiry(accessJwtService.extractExpiration(accessToken))
                                .build()
                )
                .refreshToken(
                        TokenDto
                                .builder()
                                .token(refreshToken)
                                .expiry(refreshJwtService.extractExpiration(refreshToken))
                                .build()
                )
                .build();
    }

}
