package dev.subrotokumar.subscription.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.google.common.net.HttpHeaders;

import dev.subrotokumar.subscription.model.dto.ResponseDto;

@FeignClient(
    name = "ACCOUNTS",
    url = "${application.config.accounts-url}"
)
public interface AccountClient {
    @GetMapping("/user")
    public Optional<ResponseDto<String>> getUserInfo(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationn
    );
}
