package com.muni.fi.pa165project.service;

import java.util.List;

import com.muni.fi.pa165project.entity.User;

/**
 * @author Radoslav Karlik
 * @author Lukáš Císar
 * @author Radim Podola
 */
public interface UserService {

    /**
     * Find user by ID
     *
     * @param userId ID of user to find
     * @return user object
     */
    User findById(long userId);

    /**
     * Find user by email
     *
     * @param email email of user to find
     * @return user object
     */
    User findByEmail(String email);

    /**
     * Create user
     *
     * @param user user to create
     */
    void createUser(User user);

    /**
     * Delete user
     *
     * @param userId user to delete
     */
    void deleteUser(long userId);

    /**
     * Update user
     *
     * @param user user to update
     * @return updated user
     */
    User updateUser(User user);

    /**
     * Get progress of weekly calories goal
     *
     * @param userId user's id to update
     * @return int percentage of fulfillment of a set week-calories goal
     */
    int getProgressOfWeeklyCaloriesGoal(long userId);
    
    /**
     * Finds user by his Username
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
    
    /**
     *Returns a list of users
     * @return list of users
     */
    List<User> getUsers();
    
}
