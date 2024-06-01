package dev.subrotokumar.gateway.filter;

import java.security.Key;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.google.common.net.HttpHeaders;

import dev.subrotokumar.gateway.exception.ExpiredTokenException;
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
                MultiValueMap<String, ResponseCookie> cookies = exchange.getResponse().getCookies();
                ResponseCookie accessTokenCookie = cookies.getFirst("access_token");
                System.out.println("Cookie "+accessTokenCookie);
                String authHeader = "";
                if (accessTokenCookie != null) {
                    authHeader = accessTokenCookie.getValue();
                    String token = new String(Base64.getUrlDecoder().decode(authHeader));
                    authHeader = "Bearer "+token;
                    System.out.println("Cookie "+authHeader);
                } else {
                    var authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
                    if (authHeaders == null || authHeaders.isEmpty()) {
                        throw new MissingAuthenticationHeader();
                    }
                    authHeader = authHeaders.getFirst();
                }

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
                    var request = exchange
                            .getRequest()
                            .mutate()
                            .header("X-USER-ID", id)
                            .header("X-USER-NAME", username)
                            .header(username, "X-USER-ROLE", role)
                            .build();

                    exchange = exchange.mutate().request(request).build();
                }  catch(ExpiredJwtException e){
                    throw new ExpiredTokenException(e.getMessage());
                } catch (MalformedJwtException | UnsupportedJwtException | SignatureException
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
