package dev.subrotokumar.accounts.service;

import dev.subrotokumar.accounts.dto.AuthenticationRequestDto;
import dev.subrotokumar.accounts.dto.AuthenticationResponseDto;
import dev.subrotokumar.accounts.dto.MagicLinkRequestDto;
import dev.subrotokumar.accounts.dto.RefreshTokenRequestDto;
import dev.subrotokumar.accounts.dto.RegisterAccountRequestDto;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Service interface for managing authentication processes.
 */
public interface AuthenticationService {

    /**
     * Registers a new user account with the provided account details.
     *
     * @param accountDto {@code RegisterAccountRequestDto} containing registration details
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
     * @param response http response
     * @return AuthenticationResponseDto with authentication details
     */
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto, HttpServletResponse response);

    /**
     * Authenticates a user and send magiclink to email
     *
     * @param magiclinkRequest DTO containing authentication credentials
     */
    void magiclink(MagicLinkRequestDto magiclinkRequest);

    AuthenticationResponseDto verifyMagicLink(String magicLink, HttpServletResponse response);
}
