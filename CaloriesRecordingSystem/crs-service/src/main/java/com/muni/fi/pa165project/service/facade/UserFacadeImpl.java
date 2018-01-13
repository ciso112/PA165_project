package com.muni.fi.pa165project.service.facade;

import com.muni.fi.pa165project.dto.*;
import com.muni.fi.pa165project.entity.User;
import com.muni.fi.pa165project.facade.UserFacade;
import com.muni.fi.pa165project.service.MappingService;
import com.muni.fi.pa165project.service.RecordService;
import com.muni.fi.pa165project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

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

        //hashing password with generated salt
        String salt = getSalt();
        user.getLoginDetails().setSalt(salt);
        user.getLoginDetails().setPassword(getHashedPass(userDto.getPassword(), salt));
        this.userService.createUser(user);

        return user.getId();
    }

    @Override
    public Long createAdmin(UserRegisterDTO userDto) {
        log.debug("Creating Admin with username <{}>", userDto.getUsername());

        User user = mapper.map(userDto, User.class);
        user.setIsAdmin(true);
        //hashing password with generated salt
        String salt = getSalt();
        user.getLoginDetails().setSalt(salt);
        user.getLoginDetails().setPassword(getHashedPass(userDto.getPassword(), salt));
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
    public UserDetailDTO getUser(long id) {
        log.debug("Getting User with id <{}>", id);

        User user = this.userService.findById(id);
        return mapper.map(user, UserDetailDTO.class);
    }

    @Override
    public UserDetailDTO getUser(String email) {
        log.debug("Getting User with email <{}>", email);

        User user = this.userService.findByEmail(email);
        return mapper.map(user, UserDetailDTO.class);
    }
    
    @Override
	public List<UserDetailDTO> getUsers() {
		return  mapper.mapToList(userService.getUsers(),UserDetailDTO.class);
	}
    @Override
    public final List<RecordDetailDTO> getUsersRecords(long userId) {
    	return  mapper.mapToList(recordService.getAllRecordsOfUser(userId),RecordDetailDTO.class);
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
    public UserDetailDTO checkUserCredentials(UserCredentialsDTO credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        
        User user = this.userService.findByUsername(username);
        if (user == null) {
            return null;
        }
        if (!user.getLoginDetails().getPassword().equals(getHashedPass(password, user.getLoginDetails().getSalt())))
            return null;
        
        return mapper.map(user, UserDetailDTO.class);
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
	public long getNumberOfAllRecordsOfUser(long userId){
		return recordService.getNumberOfAllRecordsOfUser(userId);
	}

    private String getHashedPass(String pass, String salt){
        byte[] hashPass = pass.concat(salt).getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(hashPass);
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            log.error("No MD5 Algorithm to hash password");
            return pass;
        }
    }

    private String getSalt(){
        final Random r = new SecureRandom();
        byte[] salt = new byte[16];
        r.nextBytes(salt);
        return DatatypeConverter.printHexBinary(salt);
    }
}
