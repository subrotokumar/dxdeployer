package dev.subrotokumar.accounts.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.subrotokumar.accounts.dto.ErrorResponseDto;
import lombok.NoArgsConstructor;

/**
 * GlobalExceptionHandler
 */
@ControllerAdvice
@NoArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//     @Override
//     protected ResponseEntity<Object> handleMethodArgumentNotValid(
//             MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
//     ) {
//         Map<String, String> validationErrors = new HashMap<>();
//         var validationErrorList = ex.getBindingResult().getAllErrors();

//         validationErrorList.forEach((error) -> {
//             String fieldName = ((FieldError) error).getField();
//             String validationMsg = error.getDefaultMessage();
//             validationErrors.put(fieldName, validationMsg);
//         });
//         return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
//     }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = ErrorResponseDto
                .builder()
                .path(webRequest.getDescription(false).substring(4))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusMessage(HttpStatus.BAD_REQUEST.name())
                .message(exception.getMessage())
                .time(LocalDateTime.now()).build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccountAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleAccountExistsException(AccountAlreadyExistException exception,
            WebRequest webRequest) {
        var errorResponseDto = ErrorResponseDto
                .builder()
                .path(webRequest.getDescription(false).substring(4))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusMessage(HttpStatus.BAD_REQUEST.name())
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorResponseDto);
    }
}