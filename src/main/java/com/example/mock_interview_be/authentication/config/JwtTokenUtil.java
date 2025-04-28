package com.example.mock_interview_be.authentication.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class JwtTokenUtil {
//    @Value("${jwt.secret}")
//    private String SECRET_KEY;

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey getSecretKey() {
        // Use the plain secret string as bytes, no base64 encoding/decoding
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // Generates a JWT token based on the service name
    public String generateToken(String serviceName){
        long expirationTime = 1000 * 60 * 15; // 15 minutes

        return Jwts.builder()
                .subject(serviceName)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ expirationTime))
                .signWith(getSecretKey())
                .compact();
    }

    public String extractServiceName(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


}
