package dev.subrotokumar.accounts.service;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

/**
 * Service interface for handling JWT operations.
 */
public interface JwtService {

    /**
     * Extracts the username from the JWT.
     *
     * @param token the JWT from which the username is extracted
     * @return the username as a String
     */
    String extractUsername(String token);

    /**
     * Extracts the expiration date from the JWT.
     *
     * @param token the JWT from which the expiration date is extracted
     * @return the expiration date
     */
    Date extractExpiration(String token);

    /**
     * Extracts a claim from the JWT using a claims resolver function.
     *
     * @param token the JWT from which the claim is extracted
     * @param claimsResolver the function used to resolve the claim from the
     * token
     * @param <T> the type of the claim being extracted
     * @return the extracted claim
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Generates a JWT for the given user details.
     *
     * @param userDetails the user details for which the token is generated
     * @return the generated JWT as a String
     */
    String generateToken(UserDetails userDetails);

    /**
     * Generates a JWT for the given user details and user ID.
     *
     * @param userDetails the user details for which the token is generated
     * @param userId the user ID to include in the JWT
     * @return the generated JWT as a String
     */
    String generateToken(UserDetails userDetails, int userId);

    /**
     * Validates the JWT against the provided user details.
     *
     * @param token the JWT to validate
     * @param userDetails the user details against which the token is validated
     * @return true if the token is valid, false otherwise
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}
