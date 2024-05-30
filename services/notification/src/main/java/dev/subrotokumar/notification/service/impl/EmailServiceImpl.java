package dev.subrotokumar.notification.service.impl;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import static dev.subrotokumar.notification.email.EmailTemplate.LOGIN_MAGICLINK_EMAIL;
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
    private final SpringTemplateEngine templateEngine;

    @Async
    @Override
    public void sendLoginMagiclink(
            String destinationEmail,
            String name,
            String token
    ) {
        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper messageHelper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    UTF_8.name()
            );

            messageHelper.setFrom("subrotokumar@outlook.in");
            messageHelper.setSubject(LOGIN_MAGICLINK_EMAIL.getSubject());

            final String templateName = LOGIN_MAGICLINK_EMAIL.getTemplate();
            Map<String, Object> variables = new HashMap<>();
            variables.put("name", name);
            variables.put("token", token);

            Context context = new Context();
            context.setVariables(variables);

            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(format("INFO - Magiclink successfully send to %s with template %s", destinationEmail, templateName));
        } catch (MessagingException | MailException e) {
            log.warn("WARNING - Cannot send email to {}", destinationEmail);
        }
    }
}
