package com.muni.fi.pa165project.facade;

import java.util.List;

import com.muni.fi.pa165project.dto.AuthenticatedUserDTO;
import com.muni.fi.pa165project.dto.LoginExistsRequestDTO;
import com.muni.fi.pa165project.dto.LoginExistsResponseDTO;
import com.muni.fi.pa165project.dto.TrackingSettingsDTO;
import com.muni.fi.pa165project.dto.TrackingSettingsUpdateDTO;
import com.muni.fi.pa165project.dto.UserCredentialsDTO;
import com.muni.fi.pa165project.dto.UserDetailDTO;
import com.muni.fi.pa165project.dto.UserRegisterDTO;
import com.muni.fi.pa165project.dto.UserUpdateDTO;


/**
 * @author Radoslav Karlik
 * @author Radim Podola
 */
public interface UserFacade {

    /**
     * Create User
     *
     * @param userDto user object
     * @return user id or null if user was not created
     */
    Long createUser(UserRegisterDTO userDto);

    /**
     * Edit User
     *
     * @param userDto user object
     * @return edited user detail
     */
    UserDetailDTO editUser(UserUpdateDTO userDto);

    /**
     * Remove User
     *
     * @param id user id
     */
    void removeUser(long id);

    /**
     * Get information about User
     *
     * @param id user id
     */
    AuthenticatedUserDTO getUser(long id);

    /**
     * Get information about User by email
     *
     * @param email users's email
     */
    UserDetailDTO getUser(String email);
    
    /**
     * Get information about Users 
     *
    */
    List<UserDetailDTO> getUsers();

    /**
     * Set tracking settings for user (goals)
     *
     * @param trackingSettings tracking settings of user
     */
    TrackingSettingsUpdateDTO setTrackingSettings(TrackingSettingsDTO trackingSettings);

    /**
     * Get tracking settings
     *
     * @param userId user id
     * @return tracking settings of user
     */
    TrackingSettingsUpdateDTO getTrackingSettings(long userId);

    /**
     * Finds user by his credentials
     *
     * @param credentials
     * @return User if credentials match, null otherwise
     */
    AuthenticatedUserDTO findByCredentials(UserCredentialsDTO credentials);
    
    /**
     * Checks whether any user with given username or email exists
     *
     * @param dto
     * @return
     */
    LoginExistsResponseDTO loginExists(LoginExistsRequestDTO dto);
    
    /**
     * Returns a number of usr records
     * @param userId
     * @return number of records
     */
	long getNumberOfUserRecords(long userId);
}
