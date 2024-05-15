package dev.subrotokumar.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AccountAlreadyExistException extends  RuntimeException {
    public AccountAlreadyExistException(String message){
        super(message);
    }
}
