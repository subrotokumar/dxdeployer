package dev.subrotokumar.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an attempt is made to create an account that already
 * exists. This class is annotated with {@link ResponseStatus} to automatically
 * return a 400 Bad Request HTTP status code when this exception is thrown and
 * handled by the Spring Framework.
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Account already exists")
public class UnauthorizedOperationException extends RuntimeException {

    public UnauthorizedOperationException(String message) {
        super(message);
    }

    public UnauthorizedOperationException(){
        super("Unauthorized Operation");
    }

}
