package dev.subrotokumar.accounts.dto;

import dev.subrotokumar.accounts.constants.ErrorConstanst;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(name = "AuthenticationRequest")
public class AuthenticationRequestDto {
    @Size(min = 6,message = ErrorConstanst.INVALID_USERNAME_LENGTH)
    @NotEmpty(message = ErrorConstanst.EMPTY_USERNAME)
    private String username;

    @NotEmpty(message = ErrorConstanst.INVALID_USERNAME_LENGTH)
    @Size(min = 6, message = ErrorConstanst.INVALID_PASSWORD_LENGTH)
    private String password;
}
