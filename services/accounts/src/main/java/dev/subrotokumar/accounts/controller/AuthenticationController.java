package dev.subrotokumar.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.subrotokumar.accounts.constants.AccountConstants;
import dev.subrotokumar.accounts.dto.AuthenticationRequestDto;
import dev.subrotokumar.accounts.dto.AuthenticationResponseDto;
import dev.subrotokumar.accounts.dto.MagicLinkRequestDto;
import dev.subrotokumar.accounts.dto.RefreshTokenRequestDto;
import dev.subrotokumar.accounts.dto.RegisterAccountRequestDto;
import dev.subrotokumar.accounts.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for handling authentication-related operations such as user
 * registration, login, and token refresh.
 */
@Tag(name = "Authentication")
@RestController
@RequestMapping(path = AccountConstants.AUTH_API_PREFIX, produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "*")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authService;

    /**
     * Registers a new user account with the provided account details.
     *
     * @param registerAccountDto DTO containing registration details
     */
    @Operation(summary = "Register Account", description = "Create a new user account")
    @ApiResponse(responseCode = "201", description = "Http Status Created")
    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registerAccount(
            @Valid @RequestBody RegisterAccountRequestDto registerAccountDto
    ) {
        authService.registerAccount(registerAccountDto);
    }

    /**
     * Authenticates a user based on the provided credentials.
     *
     * @param authenticationRequestDto DTO containing authentication credentials
     * @param response http response
     * @return AuthenticationResponseDto with authentication details
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @Valid @RequestBody AuthenticationRequestDto authenticationRequestDto,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authService.authenticate(authenticationRequestDto, response));
    }

    /**
     * Refreshes the authentication token using a valid refresh token.
     *
     * @param refreshTokenDto DTO containing the refresh token
     * @return AuthenticationResponseDto with new authentication details
     */
    @GetMapping("/refresh")
    public ResponseEntity<AuthenticationResponseDto> refreshToken(
        @Valid @RequestBody RefreshTokenRequestDto refreshTokenDto
    ) {
        log.info(refreshTokenDto.getRefreshToken());
        return ResponseEntity.ok(authService.refreshToken(refreshTokenDto));
    }

    /**
     * send the magiclink token to the register email
     *
     * @param magicLinkRequestDto DTO containing the refresh token
     */
    @PostMapping("/magiclink")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void magiclink(@RequestBody @Valid MagicLinkRequestDto magicLinkRequestDto) {
        authService.magiclink(magicLinkRequestDto);
    }

    /**
     * Authenticates a user based on magiclink.
     * 
     * @param verifyMagiclinkDto DTO containing the refresh token
     * @param response http response
     * @return AuthenticationResponseDto with new authentication details
     */
    @PostMapping("/magiclink/verify")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<AuthenticationResponseDto> verifyMagicLink(
        @RequestParam("token") String token,
        HttpServletResponse response
    ) {
        return ResponseEntity.ok(authService.verifyMagicLink(token, response));
    }

}
