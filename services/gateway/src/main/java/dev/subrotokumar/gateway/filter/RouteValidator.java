package dev.subrotokumar.gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/api/v1/account/info/health",
            "/api/v1/account/auth",
            "/api/v1/account/api-docs",
            "/api/v1/account/swagger-ui/index.html",
            "/v3/api-docs",
            "/swagger-ui.html",
            "/v3/api-docs/swagger-config"
    );

    public Predicate<ServerHttpRequest> isSecured
            = request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().startsWith(uri));

}
