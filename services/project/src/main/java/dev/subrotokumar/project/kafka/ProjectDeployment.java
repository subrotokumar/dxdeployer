package dev.subrotokumar.project.kafka;

import java.util.List;

import dev.subrotokumar.project.entity.ProjectStatus;
import dev.subrotokumar.project.entity.ProjectType;
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
