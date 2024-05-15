package dev.subrotokumar.project.mapper;

import java.util.ArrayList;

import dev.subrotokumar.project.dto.CreateProjectDto;
import dev.subrotokumar.project.entity.Project;
import dev.subrotokumar.project.entity.ProjectStatus;

public class ProjectMapper {
    public static Project dtoToEntity(CreateProjectDto dto){
        if(dto.getTags()==null){
            dto.setTags(new ArrayList<>());
        }
        return Project
            .builder()
            .id(null)
            .slug(dto.getName())
            .title(dto.getTitle())
            .description(dto.getDescription())
            .githubUrl(dto.getGithubUrl())
            .status(ProjectStatus.INACTIVE)
            .tags(dto.getTags())
            .build();
    }
}