package com.muni.fi.pa165project.dao;

import com.muni.fi.pa165project.entity.User;
import com.muni.fi.pa165project.structures.LoginDetails;

import java.util.List;

/**
 * @author Radim Podola
 */
public interface UserDao {
    /**
     * Inserts user into database
     *
     * @param user user object
     */
    void create(User user);

    /**
     * Updates user in database
     *
     * @param user user object
     * @return updated user
     */
    User update(User user);

    /**
     * Deletes user from database
     *
     * @param user user object
     */
    void delete(User user);

    /**
     * Deletes user from database
     *
     * @param id user id
     */
    void delete(long id);

    /**
     * Finds user by id
     *
     * @param id user id
     * @return found user
     */
    User findById(long id);

    /**
     * Finds user by email
     *
     * @param email user email
     * @return found user
     */
    User findByEmail(String email);

    /**
     * Returns all users
     *
     * @return list of users
     */
    List<User> findAll();

    /**
     * Finds user by username
     *
     * @param username username (login)
     * @return found user
     */
    User findByUserName(String username);
    
    /**
     * Finds user by his username
     * @param username 
     * @return User if credentials match, null otherwise
     */
    User findByUsername(String username);
    
    /**
     * Checks whether a user with given email exists
     * @param email
     * @return 
     */
    boolean userWithEmailExists(String email);
    
    /**
     * Checks whether a user with given username exists
     * @param username
     * @return 
     */
    boolean userWithUsernameExists(String username);
    
         
}
