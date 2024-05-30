package dev.subrotokumar.notification.kafka;

import static java.lang.String.format;
import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import dev.subrotokumar.notification.entity.Notification;
import static dev.subrotokumar.notification.entity.NotificationType.LOGIN_MAGICLINK;
import dev.subrotokumar.notification.repository.NotificationRepository;
import dev.subrotokumar.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics="login-magiclink-topic")
    public void consumeLoginMagiclink(LoginMagiclink loginMagiclink) {
        log.info(format("Consuming the message from login-magiclink-topic %s", loginMagiclink));
        repository.save(Notification
                        .builder()
                        .userId(loginMagiclink.getUserId())
                        .type(LOGIN_MAGICLINK)
                        .date(LocalDateTime.now())
                        .loginMagiclink(loginMagiclink)
                        .build()
        );
        // emailService.sendLoginMagiclink(
        //         loginMagiclink.getEmail(),
        //         loginMagiclink.getUsername(),
        //         loginMagiclink.getMagiclink()
        // );

    }
}
