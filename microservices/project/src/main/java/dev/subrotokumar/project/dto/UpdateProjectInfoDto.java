package dev.subrotokumar.project.dto;

import java.time.LocalDateTime;
import java.util.List;

import dev.subrotokumar.project.entity.ProjectStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProjectInfoDto {
    private Integer userId;
    private Long id;
    // private String slug;
    private String title;
    private String description;
    private String githubUrl;
    private ProjectStatus status;
    private LocalDateTime lastDeployedAt;
    List<String> tags;
}
