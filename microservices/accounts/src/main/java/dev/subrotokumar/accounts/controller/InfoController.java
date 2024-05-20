package dev.subrotokumar.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import dev.subrotokumar.accounts.constants.AccountConstants;
import dev.subrotokumar.accounts.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller for handling informational operations such as health checks and
 * API documentation redirection.
 */
@RestController
@RequestMapping(path = AccountConstants.INFO_API_PREFIX, produces = {MediaType.APPLICATION_JSON_VALUE})
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

    /**
     * Endpoint for redirecting to the Swagger API documentation.
     *
     * @return ModelAndView that redirects to the Swagger UI.
     */
    @RequestMapping(value = "/docs", method = RequestMethod.GET)
    public ModelAndView apiDocs() {
        return new ModelAndView("redirect:" + "http://localhost:1111/swagger-ui/index.html");
    }

}
