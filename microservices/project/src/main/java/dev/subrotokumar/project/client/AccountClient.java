package dev.subrotokumar.project.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import dev.subrotokumar.project.constant.Constants;
import dev.subrotokumar.project.dto.AccountDto;
import dev.subrotokumar.project.dto.FeignResponseDto;

@FeignClient(name= "ACCOUNTS")
public interface AccountClient {
    @GetMapping("/api/v1/account/user")
    public FeignResponseDto<AccountDto> getUserData(@RequestHeader(Constants.USER_ID) int userId);
}
