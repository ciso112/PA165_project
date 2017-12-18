/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.rest.security;

import com.muni.fi.pa165project.dto.AuthenticatedUserDTO;
import com.muni.fi.pa165project.entity.User;
import com.muni.fi.pa165project.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.sql.Date;
import java.time.LocalDate;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 *
 * @author Radoslav Karlik
 */
@Service
@ComponentScan(basePackageClasses = UserService.class)
public class AuthorizationService {
    
    final static Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
    
    @Inject
    private UserService userService;
    
    public static String getTokenForUser(AuthenticatedUserDTO user) {
        Key key = KeyManager.getKey();
        
        String token = Jwts
                .builder()
                .claim("userId", user.getId())
                .claim("isAdmin", user.getIsAdmin())
                .setExpiration(Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        
        logger.debug("generated Token: " + token);
        
        return token;
    }
    
    public Long authorizeUser(String token, boolean shouldBeAdmin) {
        Key key = KeyManager.getKey();
        Claims claims;

        try {
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch(Exception e) {
            claims = null;
        }

        if (claims != null) {
            logger.debug(" claims: " + claims);

            Long userId = claims.get("userId", Long.class);
            
            if (userId != null) {
                logger.debug("trying to authorize User with id: " + userId);
                User user = this.userService.findById(userId);

                if (user != null) {
                    // is authorized
                    if (shouldBeAdmin ? user.getIsAdmin() : true) {
                        return userId;
                    }
                }
            }
        }
        
        return null;
    }
    
}
