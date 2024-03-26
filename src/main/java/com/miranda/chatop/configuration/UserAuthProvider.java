package com.miranda.chatop.configuration;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.miranda.chatop.model.dtos.UserDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init(){
        //convertion de la secretKey en une chaîne de caractères Base64.
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    public String createToken(UserDto userDto){
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000);

        String token = JWT.create()
                .withClaim("id", userDto.getId())
                .withIssuer(userDto.getEmail())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("name", userDto.getName())
                .sign(Algorithm.HMAC256(secretKey));

        return token;
    }

    //Cette methode prend en entrée un token JWT
    // gère la validation du token
    // extrait les information de l'utilisateur qui utilise pour créer un objet 'Authentication'
    public Authentication validateToken(String token){

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT decoded = verifier.verify(token);

            UserDto userDto = UserDto.builder()
                    .id(decoded.getClaim("id").asInt())
                    .email(decoded.getIssuer())
                    .name(decoded.getClaim("name").asString())
                    .build();

        return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());

    }
}
