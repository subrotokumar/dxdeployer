package dev.subrotokumar.accounts.dto;

import java.time.LocalDateTime;

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
    private boolean emailVerified;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

}
