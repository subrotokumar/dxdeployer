package dev.subrotokumar.gateway.filter;

import java.security.Key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.google.common.net.HttpHeaders;

import dev.subrotokumar.gateway.exception.InvalidAuthorizationToken;
import dev.subrotokumar.gateway.exception.MissingAuthenticationHeader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Value("${application.security.jwt.access.secret}")
    private String secretKey;

    public AuthenticationFilter() {
        super(Config.class);
    }

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.info("Path => {}", exchange.getRequest().getPath());
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new MissingAuthenticationHeader();
                }

                var authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
                if(authHeaders==null || authHeaders.isEmpty()){
                    throw new MissingAuthenticationHeader();
                }
                String authHeader = authHeaders.getFirst();
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    Claims claim = Jwts
                            .parserBuilder()
                            .setSigningKey(getSignInKey())
                            .build()
                            .parseClaimsJws(authHeader)
                            .getBody();
                    String username = claim.getSubject();
                    String id = claim.get("userId").toString();
                    String role = claim.get("role").toString();
                    log.debug("X-USER [ Username: {}, Id: {}, Role: {} ]", username, id, role);
                    var request = exchange
                            .getRequest()
                            .mutate()
                            .header("X-USER-ID", id)
                            .header("X-USER-NAME", username)
                            .header(username, "X-USER-ROLE", role)
                            .build();
                            
                    exchange = exchange.mutate().request(request).build();
                } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SignatureException
                        | IllegalArgumentException e) {
                    throw new InvalidAuthorizationToken();
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}