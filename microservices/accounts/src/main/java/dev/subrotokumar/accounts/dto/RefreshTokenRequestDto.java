package dev.subrotokumar.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(name = "RefreshTokenRequest")
public class RefreshTokenRequestDto {
    private String refreshToken;
}
