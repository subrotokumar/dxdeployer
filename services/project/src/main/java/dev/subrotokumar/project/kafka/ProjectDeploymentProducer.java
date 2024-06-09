package dev.subrotokumar.project.kafka;

import static java.lang.String.format;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import dev.subrotokumar.project.entity.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectDeploymentProducer {
   private final KafkaTemplate<String, ProjectDeployment> kafkaTemplate;
   public void sendStatus(Project project) {
        var projectDeployment = ProjectDeployment.builder()
            .id(project.getId())
            .slug(project.getSlug())
            .title(project.getSlug())
            .description(project.getDescription())
            .status(project.getStatus())
            .githubUrl(project.getGithubUrl())
            .type(project.getType())
            .userId(project.getUserId())
            .tags(project.getTags())
            .build();

        log.info(format("Login-Magiclink-Produce: %s", projectDeployment.toString()));
        Message<ProjectDeployment> message = MessageBuilder
            .withPayload(projectDeployment)
            .setHeader(KafkaHeaders.TOPIC, "project-deployment-topic")
            .build();
        kafkaTemplate.send(message);
   }
}
