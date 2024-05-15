package dev.subrotokumar.project.service;

import java.util.List;

import dev.subrotokumar.project.dto.CreateProjectDto;
import dev.subrotokumar.project.dto.UpdateProjectDto;
import dev.subrotokumar.project.entity.Project;
import dev.subrotokumar.project.entity.ProjectStatus;

public interface ProjectService {
 
    public List<Project> findAllMyProject(int userId);

    public Project findById(int userId, long projectId);

    public void createProject(int userId, CreateProjectDto createProjectDto);

    public void updateProjectStatus(int userId, Long projectId, ProjectStatus projectStatus);

    public void deleteProject(int userId, long projectId);

    public void updateProject(int userId, UpdateProjectDto updateProjectDto);
}
