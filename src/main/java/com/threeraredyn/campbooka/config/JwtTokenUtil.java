package com.threeraredyn.campbooka.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.threeraredyn.campbooka.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour
     
    private String SECRET_KEY = "@4SECRET_KEY_FOR_CAMPBOOKA_APPLICATION*###";

    public boolean validateAccessToken(String token) {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        }
        catch(ExpiredJwtException e) {
            LOGGER.error("JWT expired", e.getMessage());
        }
        catch(IllegalArgumentException e) {
            LOGGER.error("Token is null, empty or only whitespace", e.getMessage());
        }
        catch(MalformedJwtException e) {
            LOGGER.error("JWT is invalid", e);
        }
        catch(UnsupportedJwtException e) {
            LOGGER.error("JWT is not supported", e);
        }
        return false;
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build().parseClaimsJws(token).getBody();
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }
     
    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
                .setIssuer("Campbooka")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
                 
    }
}
