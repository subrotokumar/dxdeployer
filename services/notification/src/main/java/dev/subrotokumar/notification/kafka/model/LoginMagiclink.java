package dev.subrotokumar.notification.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginMagiclink {
    private int userId;
    private String username;
    private String email;
    private String magiclink;
    private String callbackUrl;
}
