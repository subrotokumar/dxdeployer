package dev.subrotokumar.accounts.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import dev.subrotokumar.accounts.entity.Role;
import dev.subrotokumar.accounts.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class AccessJwtServiceImpl implements JwtService {

  @Value("${application.security.jwt.access.secret}")
  private String secretKey;

  @Value("${application.security.jwt.access.expiration}")
  private long jwtExpiration;


  @Override
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  @Override
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

 
  @Override
  public String generateToken(
    UserDetails userDetails) {
    return buildToken(Role.USER, userDetails, jwtExpiration, 0);
  }

  @Override
  public String generateToken(
    UserDetails userDetails, int userId) {
    return buildToken(Role.USER, userDetails, jwtExpiration, userId);
  }

  private String buildToken(
    Role role,
      UserDetails userDetails,
      long expiration, int userId) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", role.name());
        extraClaims.put("type", "ACCESS_TOKEN");
        extraClaims.put("iss", "subrotokumar.dev");
        if(userId!=0)
        extraClaims.put("userId", userId);
    return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  @Override
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  @Override
  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}