package com.uninter.api.sghss.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.uninter.api.sghss.domain.entity.Usuario;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Setter
    private Double expirationHours = 2.0; // Duração do token em horas = 2 horas

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API SGHSS")
                    .withSubject(usuario.getLogin())
                    .withIssuedAt(java.time.Instant.now())
                    .withExpiresAt(generateExpirationDate()) // Token válido por 2 horas
                    .sign(algorithm);

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API SGHSS")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado", exception);
        }
    }

    private Instant generateExpirationDate() {
        var hoursInSeconds = (long) (expirationHours * 3600);
        return Instant.now().plusSeconds(hoursInSeconds); // Token válido por 2 horas
    }
}
