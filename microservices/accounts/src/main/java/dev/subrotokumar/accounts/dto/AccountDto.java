package dev.subrotokumar.accounts.dto;

import dev.subrotokumar.accounts.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(name = "Account")
public class AccountDto {

    private String username;
    private String email;
    private Role role;
}
