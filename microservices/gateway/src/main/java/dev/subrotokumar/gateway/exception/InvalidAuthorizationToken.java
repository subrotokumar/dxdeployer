package dev.subrotokumar.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
public class InvalidAuthorizationToken extends RuntimeException {
    public InvalidAuthorizationToken(){
        super("Invalid Authorization Token");
    }
}