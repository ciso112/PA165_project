package com.muni.fi.pa165project.dao;

import com.muni.fi.pa165project.entity.User;
import java.util.List;

/**
 *
 * @author Radim Podola
 */
public interface UserDao {
    /**
     * Inserts user into database
     * @param user 
     */
    void create(User user);

    /**
     * Updates user in database
     * @param user 
     */
    void update(User user);

    /**
     * Deletes user from database
     * @param user 
     */
    void delete(User user);    
    
    /**
     * Finds user by id
     * @param id
     * @return found user
     */
    User findById(long id);

    /**
     * Finds user by email
     * @param email
     * @return found user
     */
    User findByEmail(String email);
    
    /**
     * Returns all users
     * @return list of users
     */
    List<User> findAll();
    
    /**
     * Finds user by username
     * @param username
     * @return found user
     */
    User findByUserName(String username);
}
