package dev.subrotokumar.accounts.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import dev.subrotokumar.accounts.constants.Constants;

@Component
public class SecureEndpointConfig {
    final private Set<String> endpoint;

    public SecureEndpointConfig() {
        endpoint = new HashSet<>(List.of(Constants.secureEndpoint));
    }

    public boolean isSecure(String path) {
        return endpoint.contains(path);
    }
}
