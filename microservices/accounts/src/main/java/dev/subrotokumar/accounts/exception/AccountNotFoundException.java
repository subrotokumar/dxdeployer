package dev.subrotokumar.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an account is not found in the database. This class is
 * annotated with {@link ResponseStatus} to automatically return a 404 Not Found
 * HTTP status code when this exception is thrown and handled by the Spring
 * Framework.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message) {
        super(message);
    }
}
