package dev.subrotokumar.notification.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.subrotokumar.notification.kafka.model.LoginMagiclink;
import dev.subrotokumar.notification.kafka.model.ProjectDeployment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    private String id;
    private int userId;
    private NotificationType type;
    private LocalDateTime date;
    private LoginMagiclink loginMagiclink;
    private ProjectDeployment projectDeployment;
}
