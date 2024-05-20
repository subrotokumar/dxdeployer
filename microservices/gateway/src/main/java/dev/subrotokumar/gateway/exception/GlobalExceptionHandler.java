package dev.subrotokumar.gateway.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import dev.subrotokumar.gateway.model.ErrorDto;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleGlobalException(RuntimeException exception) {
        ErrorDto errorResponseDTO = ErrorDto
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAuthorizationToken.class)
    public ResponseEntity<ErrorDto> handleInvalidAuthorizationToken(InvalidAuthorizationToken exception) {
        var errorResponseDto = ErrorDto
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @ExceptionHandler(MissingAuthenticationHeader.class)
    public ResponseEntity<ErrorDto> handleMissingAuthenticationHeader(MissingAuthenticationHeader exception,
            WebRequest webRequest) {
        var errorResponseDto = ErrorDto
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }
}