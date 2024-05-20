package dev.subrotokumar.project.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import dev.subrotokumar.project.constant.ErrorContants;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(){
        super(ErrorContants.PROJECT_NOT_FOUND);
    }
    
    public ProjectNotFoundException(String message){
        super(message);
    }
}
