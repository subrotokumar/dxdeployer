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

/**
 * Controller for handling informational operations such as health checks and
 * API documentation redirection.
 */
@RestController
@RequestMapping(path = Constants.INFO_API_PREFIX, produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*")
@Tag(name = "Info", description = "Operations related to informational endpoints")
public class InfoController {

    /**
     * Endpoint for checking the health of the server.
     *
     * @return ResponseDto with the status and message indicating the health of
     * the server.
     */
    @Operation(summary = "Health Check", description = "Check the health of the server")
    @GetMapping("/health")
    public ResponseEntity<ResponseDto<String>> healthCheck() {
        return ResponseEntity.ok(ResponseDto
                .<String>builder()
                .status(HttpStatus.OK)
                .data("Server is running")
                .build());
    }
}
