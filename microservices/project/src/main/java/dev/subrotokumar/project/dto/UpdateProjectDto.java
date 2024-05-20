package dev.subrotokumar.project.dto;

import dev.subrotokumar.project.entity.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProjectDto {
    private Long id;
    private String slug;
    private String githubUrl;
    private ProjectStatus status;
}
