package dev.subrotokumar.accounts.service;

import dev.subrotokumar.accounts.dto.AuthenticationRequestDto;
import dev.subrotokumar.accounts.dto.AuthenticationResponseDto;
import dev.subrotokumar.accounts.dto.RefreshTokenRequestDto;
import dev.subrotokumar.accounts.dto.RegisterAccountRequestDto;

/**
 * Service interface for managing authentication processes.
 */
public interface AuthenticationService {

    /**
     * Registers a new user account with the provided account details.
     *
     * @param accountDto DTO containing registration details
     */
    void registerAccount(RegisterAccountRequestDto accountDto);

    /**
     * Refreshes the authentication token using a valid refresh token.
     *
     * @param refreshTokenDto DTO containing the refresh token
     * @return AuthenticationResponseDto with new authentication details
     */
    AuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenDto);

    /**
     * Authenticates a user based on the provided credentials.
     *
     * @param authenticationRequestDto DTO containing authentication credentials
     * @return AuthenticationResponseDto with authentication details
     */
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
}
