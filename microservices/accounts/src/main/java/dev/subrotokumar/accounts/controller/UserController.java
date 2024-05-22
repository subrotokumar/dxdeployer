package dev.subrotokumar.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.subrotokumar.accounts.constants.AccountConstants;
import dev.subrotokumar.accounts.constants.Constants;
import dev.subrotokumar.accounts.dto.AccountDto;
import dev.subrotokumar.accounts.dto.ResponseDto;
import dev.subrotokumar.accounts.entity.Role;
import dev.subrotokumar.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Controller for handling user-related operations such as retrieving and
 * deleting user data.
 */
@RestController
@RequestMapping(
        path = AccountConstants.USER_API_PREFIX,
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@Validated
@CrossOrigin(origins = "*")
@Tag(name = "User", description = "Operations related to user management")
@RequiredArgsConstructor
public class UserController {

    private final AccountService accountService;

    /**
     * Retrieves user data based on the provided user ID.
     *
     * @param userId the ID of the user whose data is to be retrieved
     * @param userRole the role of the user whose data is to be retrieved
     * @return ResponseEntity containing the user's account data
     */
    @Operation(summary = "Get User Data", description = "Retrieve user data for a given user ID")
    @ApiResponse(responseCode = "200", description = "Data retrieval successful")
    @GetMapping()
    public ResponseEntity<ResponseDto<AccountDto>> getUserData(
        @RequestHeader(Constants.USER_ID) int userId, 
        @RequestHeader(name=Constants.USER_ROLE,defaultValue="USER") String userRole
    ) {
        Role role = Role.valueOf(userRole);
        return ResponseEntity.ok(
                ResponseDto.<AccountDto>builder()
                        .status(HttpStatus.OK)
                        .data(accountService.getAccountInfo(userId))
                        .build()
        );
    }

    /**
     * Deletes a user account based on the provided user ID.
     *
     * @param userId the ID of the user whose account is to be deleted
     */
    @Operation(summary = "Delete User", description = "Delete a user account for a given user ID")
    @ApiResponse(responseCode = "204", description = "User deleted successfully")
    @DeleteMapping()
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void DeleteUser(@RequestHeader(Constants.USER_ID) int userId) {
        accountService.deleteAccount(userId);
    }
}
