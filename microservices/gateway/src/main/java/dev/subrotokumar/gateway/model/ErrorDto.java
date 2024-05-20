package dev.subrotokumar.gateway.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorDto {
    private int code;
    private String status;
    private String message;
    private LocalDateTime time;
}
