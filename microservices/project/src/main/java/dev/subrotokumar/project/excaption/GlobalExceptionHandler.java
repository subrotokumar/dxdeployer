package dev.subrotokumar.project.excaption;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.subrotokumar.project.dto.ErrorDto;
import lombok.NoArgsConstructor;


@ControllerAdvice
@NoArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleGlobalException(RuntimeException exception, WebRequest webRequest) {
        ErrorDto errorResponseDTO = ErrorDto
                .builder()
                .path(webRequest.getDescription(false).substring(4))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusMessage(HttpStatus.BAD_REQUEST.name())
                .message(exception.getMessage())
                .time(LocalDateTime.now()).build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleAccountExistsException(ProjectNotFoundException exception,
            WebRequest webRequest) {
        var errorResponseDto = ErrorDto
                .builder()
                .path(webRequest.getDescription(false).substring(4))
                .statusCode(HttpStatus.NOT_FOUND.value())
                .statusMessage(HttpStatus.NOT_FOUND.name())
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}