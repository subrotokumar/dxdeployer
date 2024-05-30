package dev.subrotokumar.accounts.dto;

import dev.subrotokumar.accounts.constants.ErrorConstanst;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MagicLinkRequestDto {
    @Email(message=ErrorConstanst.INVALID_EMAIL)
    private String email;

    private String username;
}
