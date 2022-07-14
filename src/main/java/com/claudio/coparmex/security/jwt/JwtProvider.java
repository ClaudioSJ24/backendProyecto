package com.claudio.coparmex.security.jwt;

import com.claudio.coparmex.models.entities.UserM;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        UserM userM = (UserM) authentication.getPrincipal();
        return Jwts.builder().setSubject(userM.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getUserToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){

        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;

        }catch (MalformedJwtException e){
            logger.error("Token mal formado "+e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("Token no soportado "+e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("Token espirado "+e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("Token vacio "+e.getMessage());
        }catch (SignatureException e){
            logger.error("Token mal firmado "+e.getMessage());
        }
        return false;
    }
}
