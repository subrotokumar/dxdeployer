package dev.subrotokumar.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an internal server error occurs. This class is
 * annotated with {@link ResponseStatus} to automatically return a 500 Internal
 * Server Error HTTP status code when this exception is thrown and handled by
 * the Spring Framework.
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message) {
        super(message);
    }
}
