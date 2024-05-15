package dev.subrotokumar.accounts.service;

import dev.subrotokumar.accounts.dto.AuthenticationRequestDto;
import dev.subrotokumar.accounts.dto.AuthenticationResponseDto;
import dev.subrotokumar.accounts.dto.RefreshTokenRequestDto;
import dev.subrotokumar.accounts.dto.RegisterAccountRequestDto;

public interface AuthenticationService {

    void registerAccount(RegisterAccountRequestDto accountDto);

    AuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenDto);

    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
}
