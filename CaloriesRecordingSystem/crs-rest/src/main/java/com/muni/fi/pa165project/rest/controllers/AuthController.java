/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.rest.controllers;

import com.muni.fi.pa165project.dto.TokenDTO;
import com.muni.fi.pa165project.dto.UserCredentialsDTO;
import com.muni.fi.pa165project.dto.UserDTO;
import com.muni.fi.pa165project.dto.UserRegisterDTO;
import com.muni.fi.pa165project.facade.UserFacade;
import com.muni.fi.pa165project.rest.ApiUris;
import com.muni.fi.pa165project.rest.security.AuthorizationService;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Radoslav Karlik
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_AUTHENTICATION)
public class AuthController {
    
    final static Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Inject
    private UserFacade userFacade;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final TokenDTO authenticate(@RequestBody UserCredentialsDTO credentials) {
        logger.debug("rest authenticate()");

        UserDTO user = this.userFacade.findByCredentials(credentials);
        
        if (user == null) {
            throw new IllegalArgumentException("credentials");
        }
        
        String token = AuthorizationService.getTokenForUser(user.getId());
        return new TokenDTO(token);
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TokenDTO register(@RequestBody UserRegisterDTO userDTO){
        logger.debug("rest createUser()");

        long userId = this.userFacade.createUser(userDTO);
        
        String token = AuthorizationService.getTokenForUser(userId);
        return new TokenDTO(token);
	}
}