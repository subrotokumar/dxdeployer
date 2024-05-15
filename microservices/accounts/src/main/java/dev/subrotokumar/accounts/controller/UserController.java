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
import dev.subrotokumar.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(
        path = AccountConstants.USER_API_PREFIX,
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@Validated
@CrossOrigin(origins = "*")
@Tag(name = "User")
@RequiredArgsConstructor
public class UserController {

    private final AccountService accountService;

    @GetMapping()
    public ResponseEntity<ResponseDto<AccountDto>> getUserData(@RequestHeader(Constants.USER_ID) int userId) {
        return ResponseEntity.ok(
                ResponseDto.<AccountDto>builder()
                        .status(HttpStatus.OK)
                        .data(accountService.getAccountInfo(userId))
                        .build()
        );
    }

    @DeleteMapping()
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void DeleteUser(@RequestHeader(Constants.USER_ID) int userId) {
        accountService.deleteAccount(userId);
    }
}
