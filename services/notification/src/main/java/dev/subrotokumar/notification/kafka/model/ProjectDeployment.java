package dev.subrotokumar.notification.kafka.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDeployment {
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String githubUrl;
    private ProjectStatus status;
    private ProjectType type;
    private Integer userId;
    private List<String> tags;
}
