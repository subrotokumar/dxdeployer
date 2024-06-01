package dev.subrotokumar.notification.service.impl;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import dev.subrotokumar.notification.email.EmailTemplate;
import dev.subrotokumar.notification.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Async
    @Override
    public void sendLoginMagiclink(
            String destinationEmail,
            String name,
            String token
    ) {
        try {  
            token = format("http://localhost:4200/auth?magiclink=%s", token);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper messageHelper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    UTF_8.name()
            );

            messageHelper.setFrom("info@dxdeployer.dev");
            messageHelper.setSubject("Login to DxDeployer");

            messageHelper.setText(EmailTemplate.loginMagiclink(token), true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(format("INFO - Magiclink successfully send to %s", destinationEmail));
        } catch (MessagingException | MailException e) {
            log.warn("WARNING - Cannot send email to {} due to {}", destinationEmail, e.getMessage());
        }
    }
}
