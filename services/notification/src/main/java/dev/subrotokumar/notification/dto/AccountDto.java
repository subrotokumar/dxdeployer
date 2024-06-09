package dev.subrotokumar.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AccountDto {
    private String username;
    private String email;
    private String role;
}
