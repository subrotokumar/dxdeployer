package dev.subrotokumar.notification.service;

public interface EmailService {
    public void sendLoginMagiclink(
        String destinationEmail,
        String name,
        String token
    );

    public void sendProjectDeploymentStartMail(
            String username,
            String email,
            String projectName
    );
}
