package com.backend.portfolio.auth;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.backend.portfolio.auth.models.JwtResponseModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager implements Serializable {
    /** 
    *
    */
    private static final long serialVersionUID = 7008375124389347049L;
    public static final long TOKEN_VALIDITY = 10 * 60 * 60; // TODO: is this 10 minutes?
    @Value("${secret}")
    private String jwtSecret;

    public JwtResponseModel generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Date expiresAt = new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
        String token = Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) 
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return new JwtResponseModel(token, expiresAt.getTime());
    }

    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(userDetails.getUsername()) && !isTokenExpired);
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}