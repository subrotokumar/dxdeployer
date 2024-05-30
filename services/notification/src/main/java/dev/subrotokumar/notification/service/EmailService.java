package dev.subrotokumar.notification.service;

public interface EmailService {
    public void sendLoginMagiclink(
        String destinationEmail,
        String name,
        String token
    );
}
