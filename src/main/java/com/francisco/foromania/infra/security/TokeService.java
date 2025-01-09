package com.francisco.foromania.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.francisco.foromania.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokeService {

    public static String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256("password");
            return JWT.create()
                    .withIssuer("Foromania")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaDeExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public static Instant generarFechaDeExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }

    public String getSubject(String token) {
        if (token == null){
            throw new RuntimeException("Token nullo");
        }
        DecodedJWT verifier =null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("password");
            verifier = JWT.require(algorithm)
                    .withIssuer("Foromania")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null){
            throw new RuntimeException("Subject invalido");
        }
        return verifier.getSubject();
    }
}
