package dev.subrotokumar.notification.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.subrotokumar.notification.dto.EmailDetailDto;
import dev.subrotokumar.notification.service.EmailService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmailController {
 
    private EmailService emailService;
 
    @PostMapping("/sendMail")
    public boolean sendMail(@RequestBody EmailDetailDto details) {
        boolean status = emailService.sendSimpleMail(details);
        return status;
    }
}