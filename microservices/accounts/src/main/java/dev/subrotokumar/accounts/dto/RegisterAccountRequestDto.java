package dev.subrotokumar.accounts.dto;

import dev.subrotokumar.accounts.constants.ErrorConstanst;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
    name = "RegisterAccountRequest",
    description = "Schema to hold detail information required for register account"
)
public class RegisterAccountRequestDto {
    @NotEmpty(message=ErrorConstanst.EMPTY_USERNAME)
    @Size(min = 6,message = ErrorConstanst.INVALID_USERNAME_LENGTH)
    private String username;

    @Email(message = ErrorConstanst.INVALID_EMAIL)
    private String email;
    
    @NotEmpty @Size(min = 6, message = ErrorConstanst.INVALID_PASSWORD_LENGTH)
    private String password;
}
