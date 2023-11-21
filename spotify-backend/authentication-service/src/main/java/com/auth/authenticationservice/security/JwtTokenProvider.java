package com.auth.authenticationservice.security;

import com.auth.authenticationservice.exceptiion.AuthenticationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-milliseconds}")
    private long jwtExpiration;


    public String generateJwtToken(Authentication authentication){

        String username= authentication.getName();
        Date currentDate=new Date();

        Date expire=new Date(currentDate.getTime() + jwtExpiration);

        String token=Jwts.builder()
                .setSubject(username)
                .setExpiration(expire)
                .signWith(key())
                .compact();
        return token;

    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret));
    }

    // get username from jwt token

    public String getUsername(String token){
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username= claims.getSubject();
        return username;
    }

    //validate Jwt token

    public Boolean validateToken(String token){
       try {
           Jwts.parserBuilder()
                   .setSigningKey(key())
                   .build()
                   .parse(token);
           return true;
       }catch (MalformedJwtException e){
           throw  new AuthenticationException("Invalid JWT token - token Malformed ");
       }catch (ExpiredJwtException e){
           throw  new AuthenticationException("Invalid JWT token - token Expired ");
       }catch (UnsupportedJwtException e){
           throw  new AuthenticationException("Invalid JWT token - token unsupported ");
       }catch (IllegalArgumentException e){
           throw  new AuthenticationException("Invalid JWT token - token Illegal ");
       }
    }

}
