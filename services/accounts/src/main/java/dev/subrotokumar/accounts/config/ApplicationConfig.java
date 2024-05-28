package dev.subrotokumar.accounts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import dev.subrotokumar.accounts.exception.AccountNotFoundException;
import dev.subrotokumar.accounts.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final AccountRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            return repository.findByUsername(username)
                    .orElseThrow(() -> new AccountNotFoundException("User not found"));
        };
    }
}
