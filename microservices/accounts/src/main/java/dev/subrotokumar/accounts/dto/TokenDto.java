package dev.subrotokumar.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Schema(name = "Token")
public class TokenDto {
    String token;
    final Date expiry;
}