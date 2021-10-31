package com.matchday.api.security.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.matchday.api.exception.ApiBadRequestException;
import com.matchday.api.security.service.UserDetailsImpl;
import com.matchday.api.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {

    private final static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    public String generarJwt(Authentication authentication) {
        UserDetailsImpl userPrimary = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()//
                .setSubject(userPrimary.getUsername())//
                .setIssuedAt(new Date())//
                .setExpiration(new Date(new Date().getTime() + Constants.EXPIRATION_TIME))//
                .signWith(SignatureAlgorithm.HS512, Constants.SECRET_KEY)//
                .compact();
    }

    public String obtenerJwt(HttpServletRequest request) {
        String header = request.getHeader(Constants.HEADER);

        if (header != null && header.startsWith(Constants.TOKEN_PREFIX))
            return header.replace(Constants.TOKEN_PREFIX, "");

        return null;
    }

    public String obtenerUsernameFromJwt(String jwt) {
        return Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(jwt).getBody().getSubject();
    }

    public boolean validarJwt(String jwt) {
        try {
            Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(jwt);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
            throw new ApiBadRequestException("Invalid JWT signature: {}");
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT Token: {}", e.getMessage());
            throw new ApiBadRequestException("Invalid JWT Token: {}");
        } catch (ExpiredJwtException e) {
            logger.error("The JWT token has expired: {}", e.getMessage());
            throw new ApiBadRequestException("The JWT token has expired: {}");
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is not supported: {}", e.getMessage());
            throw new ApiBadRequestException("JWT token is not supported: {}");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims chain is empty: {}", e.getMessage());
            throw new ApiBadRequestException("JWT claims chain is empty: {}");
        }
    }

}
