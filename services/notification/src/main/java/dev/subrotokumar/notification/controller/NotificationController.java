package dev.subrotokumar.notification.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.subrotokumar.notification.constants.Constants;
import dev.subrotokumar.notification.dto.ResponseDto;
import dev.subrotokumar.notification.entity.Notification;
import dev.subrotokumar.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Notification")
@RestController
@RequestMapping(path = "/api/v1/notification", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Retrieves user notification data based on the provided user ID.
     *
     * @param userId header to provide request information about userId
     * @return ResponseEntity containing the user's notification data
     */
    @Operation(summary = "Get User Notification Data", description = "Retrieve user's notification data for a given user ID")
    @ApiResponse(responseCode = "200", description = "Data retrieval successful")
    @GetMapping()
    public ResponseEntity<ResponseDto<List<Notification>>> findNotifications(
        @RequestHeader(value=Constants.USER_ID) int userId
    ){
        return ResponseEntity.ok(
            ResponseDto.<List<Notification>>builder()
                .status(HttpStatus.OK)
                .data(notificationService.findMyNotifications(userId))
                .build()  
        );
    }
}
