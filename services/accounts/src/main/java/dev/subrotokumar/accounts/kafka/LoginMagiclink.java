package dev.subrotokumar.accounts.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginMagiclink {
    private int userId;
    private String username;
    private String email;
    private String magiclink;
    private String callbackUrl;
}
