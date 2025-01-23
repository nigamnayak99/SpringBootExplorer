package com.nigam.springbootexplorer.util;

import com.nigam.springbootexplorer.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

@Component
public class JWTTokenHelper {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    @Value("${jwt.authenticationHeader}")
    private String AUTHENTICATION_HEADER;

    @Value("${jwt.expiresIn}")
    private String EXPIRES_IN;

    private final MacAlgorithm SIGNATURE_ALGORITHM = Jwts.SIG.HS512;

    /**
     * Calculates the expiration date for the token based on the current system time
     * and the predefined expiration interval.
     *
     * @return the expiration date for the token
     */
    private Date getTokenExpirationDate() {
        long currentMillis = Utility.getCurrentSystemTimeInMillisSeconds.get();
        Instant expirationInstant = Instant.ofEpochMilli(currentMillis).plus(Long.parseLong(EXPIRES_IN), ChronoUnit.MILLIS);
        return Date.from(expirationInstant);
    }

    /**
     * Retrieves the signing key used for generating and verifying JWT tokens.
     * The key is derived from a predefined secret key.
     *
     * @return the signing key as a SecretKey instance
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generates a JWT token for a given user. The token includes the user's ID, email,
     * roles, issued date, and expiration date, and is signed with a secure key.
     *
     * @param user the user for whom the token is generated
     * @return the generated JWT token as a String
     */
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("email", user.getEmail())
                .claim("roles", Set.of("ADMIN", "USER"))
                .issuedAt(new Date())
                .expiration(getTokenExpirationDate())
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Extracts the user ID from a given JWT token. The token is parsed and verified
     * using the signing key to retrieve claims, including the subject (user ID).
     *
     * @param token the JWT token from which the user ID is extracted
     * @return the user ID as a Long
     * @throws JwtException if the token is invalid or cannot be parsed
     */
    public Long getUserIdFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return Long.valueOf(claims.getSubject());
        } catch (JwtException e) {
            // Log the exception or rethrow it as a custom exception
            throw new IllegalArgumentException("Invalid or expired token", e);
        }
    }


}
