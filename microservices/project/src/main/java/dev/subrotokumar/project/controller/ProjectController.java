package dev.subrotokumar.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.subrotokumar.project.constant.Constants;
import dev.subrotokumar.project.dto.CreateProjectDto;
import dev.subrotokumar.project.dto.ResponseDto;
import dev.subrotokumar.project.dto.UpdateProjectDto;
import dev.subrotokumar.project.entity.Project;
import dev.subrotokumar.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = Constants.PROJECT_API_PREFIX, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Project")
@RequiredArgsConstructor
@Validated
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping()
    @Operation(summary = "Find Project")
    public ResponseEntity<ResponseDto<List<Project>>> findProjects(
        @RequestHeader(Constants.USER_ID) int userId
    ) {
        return ResponseEntity.ok(
                ResponseDto.<List<Project>>builder()
                        .status(HttpStatus.OK)
                        .data(projectService.findAllMyProject(userId))
                        .build()
        );
    }

    @GetMapping("/{projectId}")
    @Operation(summary = "Find Project By Id")
    public ResponseEntity<ResponseDto<Project>> findProjectById(
        @RequestHeader(Constants.USER_ID) int userId,
        @PathVariable long projectId
    ) {
        return ResponseEntity.ok(
                ResponseDto.<Project>builder()
                        .status(HttpStatus.OK)
                        .data(projectService.findById(userId, projectId))
                        .build()
        );

    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @Operation(summary = "Created Project")
    public void createProject(
        @RequestHeader(Constants.USER_ID) int userId, 
        @Valid @RequestBody CreateProjectDto createProjectDto
    ) {
        projectService.createProject(userId, createProjectDto);
    }

    @DeleteMapping("/{projectId}")
    @Operation(summary = "Delete Project")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteProject(
        @RequestHeader(Constants.USER_ID) int userId, 
        @PathVariable long projectId
    ) {
        projectService.deleteProject(userId, projectId);
    }

    @PatchMapping()
    @Operation(summary = "Update Project")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateProject(
        @RequestHeader(Constants.USER_ID) int userId,
        @RequestBody() UpdateProjectDto updateProjectDto
    ) {
        projectService.updateProject(userId, updateProjectDto);
    }
}
