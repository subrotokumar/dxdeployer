package dev.subrotokumar.project.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import dev.subrotokumar.project.constant.ErrorContants;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(){
        super(ErrorContants.UNAUTHORIZED_OPERATION);
    }

    public UnauthorizedException(String message){
        super(message);
    }
}
