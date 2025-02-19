package com.example.sileo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.sileo.domain.Usuario.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;
    public String generateToken(Usuario user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("login-with-jwt")
                    .withSubject(user.getEmail())
                    .withClaim("userId", user.getId().toString())
                    .withExpiresAt(getExpirationTime())
                    .sign(algorithm);

            return token;

        } catch (JWTCreationException e) {
            throw new RuntimeException("Error creating token");
        }
    }

    public String validadeJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant getExpirationTime() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }

    public String   getIdFromToken(String token) {

        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("login-with-jwt")
                    .build()
                    .verify(token);

            return decodedJWT.getClaim("userId").asString();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid Token");
        }

    }
}
