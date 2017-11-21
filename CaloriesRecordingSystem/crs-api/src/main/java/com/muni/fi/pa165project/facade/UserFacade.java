package com.muni.fi.pa165project.facade;

import com.muni.fi.pa165project.dto.UserDTO;

/**
 *
 * @author Radoslav Karlik
 * @author Radim Podola
 */
public interface UserFacade {

    /**
     * Create User
     * @param userDto user object
     */
    void createUser(UserDTO userDto);

    /**
     * Update User
     * @param userDto user object
     */
    void updateUser(UserDTO userDto);

    /**
     * Remove User
     * @param id user id
     */
    void removeUser(long id);
}
