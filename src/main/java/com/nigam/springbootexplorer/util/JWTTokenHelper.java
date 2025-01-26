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

    private final String ACCESS = "access";
    private final String REFRESH = "refresh";

    /**
     * Generates a token (access or refresh) with its expiration date.
     *
     * @param tokenType the type of token to generate ("access" or "refresh")
     * @return a map containing the token and its expiration date
     * @throws IllegalArgumentException if the token type is invalid
     */
    public Date generateTokenExpirationDate(String tokenType) {
        long currentMillis = Utility.getCurrentSystemTimeInMillisSeconds.get();

        // Determine expiration duration based on token type
        long expiresIn;
        if (ACCESS.equalsIgnoreCase(tokenType)) {
            expiresIn = Long.parseLong(EXPIRES_IN);
        } else if (REFRESH.equalsIgnoreCase(tokenType)) {
            expiresIn = Long.parseLong(EXPIRES_IN) * 48 * 30 * 6; // Adjust multiplier for refresh token
        } else {
            throw new IllegalArgumentException("Invalid token type: " + tokenType);
        }

        // Calculate expiration date and return it.
        Instant expirationInstant = Instant.ofEpochMilli(currentMillis).plus(expiresIn, ChronoUnit.MILLIS);
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
     * Generates a JWT access token for a given user. The token includes the user's ID, email,
     * roles, issued date, and expiration date, and is signed with a secure key.
     *
     * @param user the user for whom the token is generated
     * @return the generated JWT token as a String
     */
    public String generateAccessToken(User user) {
        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("email", user.getEmail())
                .claim("roles", Set.of("ADMIN", "USER"))
                .issuedAt(new Date())
                .expiration(generateTokenExpirationDate(ACCESS))
                .signWith(getSigningKey())
                .compact();
    }


    /**
     * Generates a refresh token for the given user.
     *
     * @param user the user for whom the refresh token is to be generated
     * @return a JWT refresh token as a String
     *
     * The token includes the user's ID as the subject,
     * an expiration date generated specifically for refresh tokens,
     * and is signed using a secure signing key.
     */
    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .expiration(generateTokenExpirationDate(REFRESH))
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
