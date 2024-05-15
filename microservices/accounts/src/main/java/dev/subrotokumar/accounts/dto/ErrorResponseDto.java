package dev.subrotokumar.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@Schema(name = "Error")
public class ErrorResponseDto {
    private String path;
    private int statusCode;
    private String statusMessage;
    private String message;
    private LocalDateTime time;
}
