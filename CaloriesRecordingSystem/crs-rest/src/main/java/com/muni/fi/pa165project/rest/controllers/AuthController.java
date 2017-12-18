package com.muni.fi.pa165project.rest.controllers;

import com.muni.fi.pa165project.dto.AuthenticatedUserDTO;
import com.muni.fi.pa165project.dto.LoginExistsRequestDTO;
import com.muni.fi.pa165project.dto.LoginExistsResponseDTO;
import com.muni.fi.pa165project.dto.TokenDTO;
import com.muni.fi.pa165project.dto.UserCredentialsDTO;
import com.muni.fi.pa165project.dto.UserDetailDTO;
import com.muni.fi.pa165project.dto.UserRegisterDTO;
import com.muni.fi.pa165project.facade.UserFacade;
import com.muni.fi.pa165project.rest.ApiUris;
import com.muni.fi.pa165project.rest.exceptions.UnprocessableEntityException;
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
        logger.debug("rest authenticate() username <{}>", credentials.getUsername());
        
        AuthenticatedUserDTO user = this.userFacade.findByCredentials(credentials);
        
        if (user == null) {
            throw new UnprocessableEntityException();
        }
        
        String token = AuthorizationService.getTokenForUser(user);
        return new TokenDTO(token);
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Object register(@RequestBody UserRegisterDTO userDTO){
        logger.debug("rest register()");

        LoginExistsRequestDTO dto = new LoginExistsRequestDTO();
        dto.setEmail(userDTO.getEmail());
        dto.setUsername(userDTO.getUsername());
        LoginExistsResponseDTO response = this.userFacade.loginExists(dto);
        
        if (response.isEmailExists() || response.isUsernameExists()) {
            return response;
        }

        AuthenticatedUserDTO user;
        try {
            long userId = this.userFacade.createUser(userDTO);
            user = this.userFacade.getUser(userId);
        } catch (Exception e){
            throw new UnprocessableEntityException();
        }

        String token = AuthorizationService.getTokenForUser(user);
        return new TokenDTO(token);
	}
}
