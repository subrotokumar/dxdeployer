package dev.subrotokumar.project.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.subrotokumar.project.client.AccountClient;
import dev.subrotokumar.project.dto.CreateProjectDto;
import dev.subrotokumar.project.dto.UpdateProjectDto;
import dev.subrotokumar.project.dto.UpdateProjectInfoDto;
import dev.subrotokumar.project.entity.Project;
import dev.subrotokumar.project.entity.ProjectStatus;
import dev.subrotokumar.project.excaption.ProjectNotFoundException;
import dev.subrotokumar.project.excaption.UnauthorizedException;
import dev.subrotokumar.project.mapper.ProjectMapper;
import dev.subrotokumar.project.repository.ProjectRepository;
import dev.subrotokumar.project.service.ContainerService;
import dev.subrotokumar.project.service.ProjectService;
import dev.subrotokumar.slugsmith.SlugSmith;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    final private ProjectRepository projectRepository;
    final private ContainerService containerService;
    final private AccountClient accountClient;

    @Override
    public List<Project> findAllMyProject(int userId) {
        return projectRepository.findAllByUserId(userId);
    }

    @Override
    public void deleteProject(int userId, long projectId) {
        var project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        if(project.getUserId()!=userId){
            throw new UnauthorizedException();
        }
        projectRepository.deleteProjectWhereIdAndUserIdEquals(projectId, userId);
    }

    @Override
    public Project findById(int userId, long projectId) {
        var project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        if(project.getUserId()!=userId){
            throw new UnauthorizedException();
        }
        return project;
    }

    @Override
    public void createProject(int userId, CreateProjectDto createProjectDto) {
        var project = ProjectMapper.dtoToEntity(createProjectDto);
        project.setSlug(SlugSmith.generateSlug());
        project.setUserId(userId);
        // boolean status = containerService.startTask(project.getSlug(), project.getGithubUrl());
        // if (status) {
        //     project.setStatus(ProjectStatus.INACTIVE);
            projectRepository.save(project);
        // }
    }

    @Override
    public void updateProject(int userId, UpdateProjectDto dto) {

        updateProject( 
            UpdateProjectInfoDto
                .builder()
                .id(dto.getId())
                .userId(userId)
                // .slug(dto.getSlug())
                .githubUrl(dto.getGithubUrl())
                .status(dto.getStatus())
                .build()
        );
    }

    @Override
    public void updateProjectStatus(int userId, Long projectId, ProjectStatus status) {
        updateProject(
            UpdateProjectInfoDto
                .builder()
                .id(projectId)
                .userId(userId)
                .status(status)
                .build()
        );
    }

    private void updateProject(UpdateProjectInfoDto info){
        projectRepository.updateProject(
            info.getUserId(), 
            info.getId(), 
            info.getTitle(), 
            info.getDescription(),
            info.getGithubUrl(), 
            info.getStatus(),
            info.getLastDeployedAt(),
            info.getTags()
        );
    }
}
