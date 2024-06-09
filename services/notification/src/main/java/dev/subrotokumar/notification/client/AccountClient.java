package dev.subrotokumar.notification.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import dev.subrotokumar.notification.constants.Constants;
import dev.subrotokumar.notification.dto.AccountDto;
import dev.subrotokumar.notification.dto.FeignResponseDto;

@FeignClient(name= "ACCOUNTS")
@Service
public interface AccountClient {
    @GetMapping("/api/v1/account/user")
    public FeignResponseDto<AccountDto> getUserData(@RequestHeader(Constants.USER_ID) int userId);
}
