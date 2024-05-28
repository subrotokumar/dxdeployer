package dev.subrotokumar.project.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(name = "Error")
public class ErrorDto {
    private String path;
    private int statusCode;
    private String statusMessage;
    private String message;
    private LocalDateTime time;
}
