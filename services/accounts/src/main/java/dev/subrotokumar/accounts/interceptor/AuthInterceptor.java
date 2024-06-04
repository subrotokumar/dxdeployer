package dev.subrotokumar.accounts.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.common.net.HttpHeaders;

import dev.subrotokumar.accounts.config.SecureEndpointConfig;
import dev.subrotokumar.accounts.constants.Constants;
import dev.subrotokumar.accounts.exception.UnauthorizedOperationException;
import dev.subrotokumar.accounts.service.impl.AccessJwtServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    final private AccessJwtServiceImpl accessJwtService;
    final private SecureEndpointConfig secureEndpointConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler ) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if(!path.contains("user")) return true;
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader = authHeader.substring(7);
        } else {
            throw new UnauthorizedOperationException();
        }
        try {
            Claims claim = accessJwtService.extractAllClaims(authHeader);
            String username = claim.getSubject();
            String id = claim.get("userId").toString();
            String role = claim.get("role").toString();
            request.setAttribute(Constants.USERNAME, username);
            request.setAttribute(Constants.USER_ID, id);
            request.setAttribute(Constants.USER_ROLE, role);
        } catch(ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SignatureException | IllegalArgumentException e){
            throw new UnauthorizedOperationException();
        }
        return true;
    }
}
