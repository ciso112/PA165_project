package com.muni.fi.pa165project.rest.controllers;

import com.muni.fi.pa165project.dto.RecordDTO;
import com.muni.fi.pa165project.dto.RecordDetailDTO;
import com.muni.fi.pa165project.dto.TrackingSettingsDTO;
import com.muni.fi.pa165project.dto.TrackingSettingsUpdateDTO;
import com.muni.fi.pa165project.dto.UserDetailDTO;
import com.muni.fi.pa165project.dto.UserUpdateDTO;
import com.muni.fi.pa165project.facade.TrackingFacade;
import com.muni.fi.pa165project.facade.UserFacade;
import com.muni.fi.pa165project.rest.ApiUris;
import com.muni.fi.pa165project.rest.exceptions.InternalException;
import com.muni.fi.pa165project.rest.exceptions.ResourceNotFoundException;
import com.muni.fi.pa165project.rest.exceptions.UnprocessableEntityException;
import com.muni.fi.pa165project.rest.security.ApplyAuthorizeFilter;
import com.muni.fi.pa165project.rest.security.SecurityLevel;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.inject.Inject;

/**
 * REST Controller for Users
 *
 * @author Lukáš Císar
 * @author Radim Podola
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_USERS)
public class UsersController {

    final static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Inject
    private UserFacade userFacade;
    
    @Inject
    private TrackingFacade trackingFacade;    

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDetailDTO getUser(@PathVariable("id") long userId) {

        logger.debug("rest getUser({})", userId);
        UserDetailDTO user = userFacade.getUser(userId);

        return user;
    }
    
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<UserDetailDTO> getUsers() {

        logger.debug("rest getUsers()");
        List<UserDetailDTO> users = userFacade.getUsers();
        for(UserDetailDTO user: users){
        	 long numberOfRecords = userFacade.getNumberOfAllRecordsOfUser(user.getId());
        	 user.setNumberOfRecords(numberOfRecords);
        }
        return users;
    }
    
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/{id}/records", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<RecordDTO> getUserRecords(@PathVariable("id") long userId) {
    	 logger.debug("rest getUserRecords({})", userId);
    	List<RecordDTO> records = trackingFacade.getAllRecords(userId);
    	return records;

  
    }

    /**
     * Get a User's settings detail by identifier.
     * Identifier is taken from login information.
     * <p>
     * TEST: curl -X GET -i  http://localhost:8080/pa165/rest/users/settings
     *
     * @param userId identifier for a user
     * @return User detail Data Transfer Object
     * @throws ResourceNotFoundException if resource is not found
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.MEMBER)
    @RequestMapping(value = "/settings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDetailDTO getUserSettingsDetail(@RequestAttribute("userId") long userId) {
        logger.debug("rest getUserSettingsDetail({})", userId);

        UserDetailDTO user = userFacade.getUser(userId);
        if (user != null) {
            return user;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Edit a User's settings detail.
     * Identifier is taken from login information.
     * <p>
     * TEST: curl -X PUT -i -H "Content-Type: application/json"
     * --data '{"name": "Radek", "description": "new description"}' TODO
     * http://localhost:8080/pa165/rest/settings/details/users
     *
     * @param userId identifier for a user
     * @return User detail Data Transfer Object
     * @throws UnprocessableEntityException if resource contains wrong attributes
     * @throws InternalException            in case of any other error
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.MEMBER)
    @RequestMapping(value = "/settings", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final UserDetailDTO editUserSettingsDetail(@RequestAttribute("userId") long userId, @RequestBody UserUpdateDTO user) {
        logger.debug("rest editUserSettingsDetail({})", userId);

        try {
            user.setId(userId);
            return userFacade.editUser(user);
        } catch (ConstraintViolationException ex) {
            throw new UnprocessableEntityException();
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.MEMBER)
    @RequestMapping(value = "/settings/tracking", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final TrackingSettingsUpdateDTO setTrackingSettings(@RequestAttribute("userId") long userId, @RequestBody TrackingSettingsDTO ts) {
        logger.debug("rest editTrackingSettings({}) for user <{}>", ts.getWeeklyCaloriesGoal(), userId);

        try {
            ts.setUserId(userId);
            return userFacade.setTrackingSettings(ts);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.MEMBER)
    @RequestMapping(value = "/settings/tracking", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TrackingSettingsUpdateDTO getTrackingSettings(@RequestAttribute("userId") long userId) {
        logger.debug("rest getTrackingSettings({})", userId);

        try {
            return userFacade.getTrackingSettings(userId);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
