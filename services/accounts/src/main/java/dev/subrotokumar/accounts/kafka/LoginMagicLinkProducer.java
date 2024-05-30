package dev.subrotokumar.accounts.kafka;

import static java.lang.String.format;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginMagicLinkProducer {
   private final KafkaTemplate<String, LoginMagiclink> kafkaTemplate;
   public void sendLoginMagiclink(LoginMagiclink sendMagicLink) {
        log.info(format("Login-Magiclink-Produce: %s", sendMagicLink.toString()));
        Message<LoginMagiclink> message = MessageBuilder
            .withPayload(sendMagicLink)
            .setHeader(KafkaHeaders.TOPIC, "login-magiclink-topic")
            .build();
        kafkaTemplate.send(message);
   }
}
