package dev.subrotokumar.authentication.service.impl;

import org.springframework.stereotype.Service;

import dev.subrotokumar.authentication.model.User;
import dev.subrotokumar.authentication.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(int userId) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }
}
