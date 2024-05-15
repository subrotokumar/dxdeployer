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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.subrotokumar.accounts.constants.AccountConstants;
import dev.subrotokumar.accounts.dto.AuthenticationRequestDto;
import dev.subrotokumar.accounts.dto.AuthenticationResponseDto;
import dev.subrotokumar.accounts.dto.RefreshTokenRequestDto;
import dev.subrotokumar.accounts.dto.RegisterAccountRequestDto;
import dev.subrotokumar.accounts.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Authentication")
@RestController
@RequestMapping(path = AccountConstants.AUTH_API_PREFIX, produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "*")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authService;

    @Operation(summary = "Register Account", description = "Create a new user account")
    @ApiResponse(responseCode = "201", description = "Http Status Created")
    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody RegisterAccountRequestDto registerAccountDto
    ) {
        authService.registerAccount(registerAccountDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return ResponseEntity.ok(authService.authenticate(authenticationRequestDto));
    }

    @GetMapping("/refresh")
    public ResponseEntity<AuthenticationResponseDto> refreshToken(@Valid @RequestBody RefreshTokenRequestDto refreshTokenDto) {
        log.info(refreshTokenDto.getRefreshToken());
        return ResponseEntity.ok(authService.refreshToken(refreshTokenDto));
    }

}
