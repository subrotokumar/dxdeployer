package dev.subrotokumar.accounts.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaMagiclinkTopicConfig {
    @Bean
    public NewTopic magicLinkTopic(){
        return TopicBuilder
            .name("login-magiclink-topic")
            .build();
    }
}
