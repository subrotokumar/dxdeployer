package dev.subrotokumar.subscription.model.record;

import org.springframework.context.annotation.Role;

public record UserResponse(
    String username,
    String email,
    Role role
) {
    
}
