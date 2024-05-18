package dev.subrotokumar.notification.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.subrotokumar.notification.dto.ResponseDto;


@RestController
@RequestMapping("/api/v1/notification")
public class InfoController {
    @GetMapping("/health")
    public ResponseEntity<ResponseDto<String>> healthCheck() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseDto
                    .<String>builder()
                    .status(HttpStatus.OK)
                    .data("Server is runnning")
                    .build()
            );
    }
    
}
