package dev.subrotokumar.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Exception thrown when an attempt is made to create an account that already
 * exists. This class is annotated with {@link ResponseStatus} to automatically
 * return a 400 Bad Request HTTP status code when this exception is thrown and
 * handled by the Spring Framework.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Account already exists")
@EqualsAndHashCode(callSuper = true)
@Data
public class AccountAlreadyExistException extends RuntimeException {
    private final String message;
}
