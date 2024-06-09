package dev.subrotokumar.notification.dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;

@Builder
public class ResponseDto<T> {

    final private HttpStatus status;
    final private T data;

    public int getCode(){
        return status.value();
    }

    public String getMessage() {
        return status.getReasonPhrase();  
    }

    public T getData(){
        return data;
    }  
}
