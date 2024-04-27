package dev.subrotokumar.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.subrotokumar.authentication.model.User;
import dev.subrotokumar.authentication.service.UserService;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;
    
    @GetMapping("/{userId}")
    public User getMethodName(@PathVariable int userId) {
        return userService.getUserById(userId);
    }
    
}