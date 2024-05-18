package dev.subrotokumar.notification.dto;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Response")
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
