package dev.subrotokumar.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.subrotokumar.project.constant.Constants;
import dev.subrotokumar.project.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = Constants.INFO_API_PREFIX, produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*")
@Tag(name = "Info")
public class InfoController {

    @GetMapping("/health")
    @Operation(summary = "Health Check")
    public ResponseEntity<ResponseDto<String>> healthCheck() {
        return ResponseEntity.ok(
                ResponseDto
                        .<String>builder()
                        .status(HttpStatus.OK)
                        .data("Server is running 🚀")
                        .build()
        );
    }
}
