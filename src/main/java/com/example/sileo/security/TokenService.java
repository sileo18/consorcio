package com.example.sileo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.sileo.domain.Usuario.Usuario;

import com.nimbusds.oauth2.sdk.id.Issuer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TokenService {

    private static final String SECRET = "segredo";

    private static final String EMISSOR  = "sileo-api";

    @Bean
    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

           String token = JWT.create()
                   .withIssuer(EMISSOR) // Define o emissor do token
                   .withIssuedAt(creationDate()) // Define a data de emissão do token
                   .withExpiresAt(expirationDate()) // Define a data de expiração do token
                   .withSubject(usuario.getEmail()) // Define o assunto do token
                   .sign(algorithm); // Assina o token com o algoritmo definido

                    return token;
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Erro ao gerar token.", exception);
        }

    }

    @Bean
    public String getSubjectFromToken(String token) {
        try {
            // Define o algoritmo HMAC SHA256 para verificar a assinatura do token passando a chave secreta definida
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.require(algorithm)
                    .withIssuer(EMISSOR) // Define o emissor do token
                    .build()
                    .verify(token) // Verifica a validade do token
                    .getSubject(); // Obtém o assunto (neste caso, o nome de usuário) do token
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    @Bean
    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }

    @Bean
    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant();
    }
}
