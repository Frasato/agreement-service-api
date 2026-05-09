package com.frasato.consortium_service.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.frasato.consortium_service.domain.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenService implements JwtService {

    @Value("${api.security.token.secret}")
    private String secret;

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
