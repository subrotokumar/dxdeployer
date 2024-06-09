package dev.subrotokumar.gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/api/v1/account/info",
            "/api/v1/account/auth",
            "/api/v1/account/api-docs",
            "/api/v1/account/v3",
            "/api/v1/project/v3",
            "/api/v1/notification/v3",
            "/api/v1/account/swagger-ui",
            "/api/v1/project/swagger-ui",
            "/api/v1/notification/swagger-ui",
            "/v3",
            "/swagger-ui"
    );

    public Predicate<ServerHttpRequest> isSecured
            = request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().startsWith(uri));

}
