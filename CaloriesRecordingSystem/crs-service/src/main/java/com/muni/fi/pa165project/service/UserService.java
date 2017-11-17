/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.service;

import com.muni.fi.pa165project.entity.User;

/**
 *
 * @author Radoslav Karlik, Lukáš Císar
 */
public interface UserService {
	
	User findById(long userId);
	
	User findByEmail(String email);
	
	void createUser(User user);
	
	void deleteUser(User user);
	
}
