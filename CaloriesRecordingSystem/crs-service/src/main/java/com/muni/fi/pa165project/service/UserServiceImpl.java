/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.service;

import com.muni.fi.pa165project.dao.UserDao;
import com.muni.fi.pa165project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Radoslav Karlik, Lukáš Císar
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findById(long userId) {
		return this.userDao.findById(userId);
	}
	
	@Override
	public User findByEmail(String email){
		return this.userDao.findByEmail(email);
	}
	
	@Override
	public void createUser(User user){
		this.userDao.create(user);
	}
	
	@Override
	public void deleteUser(User user){
		this.userDao.delete(user);
	}
	
}
