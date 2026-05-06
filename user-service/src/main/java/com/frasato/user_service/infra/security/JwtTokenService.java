package com.frasato.user_service.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.frasato.user_service.domain.model.User;
import com.frasato.user_service.domain.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtTokenService implements TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Override
    public String generateToken(User user) {
        return JWT.create()
                .withIssuer("user-service")
                .withSubject(user.getDocument())
                .withClaim("role", user.getRole())
                .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                .sign(Algorithm.HMAC256(secret));
    }

    @Override
    public String validateToken(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("user-service")
                .build()
                .verify(token)
                .getSubject();
    }

    @Override
    public String getRole(String token){
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("user-service")
                .build()
                .verify(token)
                .getClaim("role")
                .asString();
    }
}
