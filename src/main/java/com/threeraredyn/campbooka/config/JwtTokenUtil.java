package com.threeraredyn.campbooka.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey("SECRET_KEY").parseClaimsJws(token);
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
        return Jwts.parser().setSigningKey("SECRET_KEY").parseClaimsJws(token).getBody();
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }
}
