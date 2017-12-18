package com.muni.fi.pa165project.service.facade;


import com.muni.fi.pa165project.dto.*;
import com.muni.fi.pa165project.entity.User;
import com.muni.fi.pa165project.facade.UserFacade;
import com.muni.fi.pa165project.service.MappingService;
import com.muni.fi.pa165project.service.RecordService;
import com.muni.fi.pa165project.service.UserService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Radoslav Karlik
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    final static Logger log = LoggerFactory.getLogger(UserFacadeImpl.class);

    @Autowired
    private MappingService mapper;

    @Autowired
    private UserService userService;
    
    @Autowired
    private RecordService recordService;

    @Override
    public Long createUser(UserRegisterDTO userDto) {
        log.debug("Creating User with username <{}>", userDto.getUsername());

        User user = mapper.map(userDto, User.class);
        this.userService.createUser(user);
        return user.getId();
    }

    @Override
    public UserDetailDTO editUser(UserUpdateDTO userDto) {
        log.debug("Editing User with id <{}>", userDto.getId());

        User user = this.userService.findById(userDto.getId());

        mapper.map(userDto, user);
        User updatedUser = this.userService.updateUser(user);

        return mapper.map(updatedUser, UserDetailDTO.class);
    }

    @Override
    public void removeUser(long userId) {
        log.debug("Removing User with id <{}>", userId);

        this.userService.deleteUser(userId);
    }

    @Override
    public AuthenticatedUserDTO getUser(long id) {
        log.debug("Getting User with id <{}>", id);

        User user = this.userService.findById(id);
        return mapper.map(user, AuthenticatedUserDTO.class);
    }

    @Override
    public UserDetailDTO getUser(String email) {
        log.debug("Getting User with email <{}>", email);

        User user = this.userService.findByEmail(email);
        return mapper.map(user, UserDetailDTO.class);
    }

    @Override
    public TrackingSettingsUpdateDTO setTrackingSettings(TrackingSettingsDTO trackingSettings) {
        log.debug("Setting tracking settings with goal <{}> to User with id <{}>", trackingSettings.getWeeklyCaloriesGoal(), trackingSettings.getUserId());

        User user = this.userService.findById(trackingSettings.getUserId());
        if (user != null) {
            user.getTrackingSettings().setWeeklyCaloriesGoal(trackingSettings.getWeeklyCaloriesGoal());
            user = this.userService.updateUser(user);
        }

        return mapper.map(user.getTrackingSettings(), TrackingSettingsUpdateDTO.class);
    }

    @Override
    public TrackingSettingsUpdateDTO getTrackingSettings(long userId) {
        log.debug("Getting tracking settings for User with id <{}>", userId);

        User user = this.userService.findById(userId);
        if (user == null) {
            return null;
        }
        return mapper.map(user.getTrackingSettings(), TrackingSettingsUpdateDTO.class);
    }

    @Override
    public AuthenticatedUserDTO findByCredentials(UserCredentialsDTO credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        User user = this.userService.findByCredentials(username, password);

        if (user == null) {
            return null;
        }
        
        return mapper.map(user, AuthenticatedUserDTO.class);

    }

    @Override
    public LoginExistsResponseDTO loginExists(LoginExistsRequestDTO dto) {
        boolean usernameExists = this.userService.userWithUsernameExists(dto.getUsername());
        boolean emailExists = this.userService.userWithEmailExists(dto.getEmail());

        LoginExistsResponseDTO response = new LoginExistsResponseDTO();
        response.setEmailExists(emailExists);
        response.setUsernameExists(usernameExists);

        return response;
    }

	@Override
	public List<UserDetailDTO> getUsers() {
		return  mapper.mapToList(userService.getUsers(),UserDetailDTO.class);
	}
	
	@Override
	public long getNumberOfUserRecords(long userId){
		return recordService.getNumberOfUserRecords(userId);
	}
}
