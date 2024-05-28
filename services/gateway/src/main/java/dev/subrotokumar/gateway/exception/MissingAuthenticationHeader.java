package dev.subrotokumar.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class MissingAuthenticationHeader extends RuntimeException {
    public MissingAuthenticationHeader(){
        super("Missing Authentication Header Exception");
    }
}
