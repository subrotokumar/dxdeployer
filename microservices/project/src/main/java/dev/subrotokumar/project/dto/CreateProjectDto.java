package dev.subrotokumar.project.dto;

import java.util.List;

import dev.subrotokumar.project.constant.Constants;
import dev.subrotokumar.project.constant.ErrorContants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateProjectDto {
    private String name;

    @NotBlank(message=ErrorContants.EMPTY_TITLE)
    private String title;

    @NotBlank(message=ErrorContants.EMPTY_DESCRIPTION)
    private String description;

    @Pattern(regexp=Constants.GITHUB_URL_REGEX, message=ErrorContants.INVALID_GITHUB_URL)
    private String githubUrl;
    
    private List<String> tags;
}
