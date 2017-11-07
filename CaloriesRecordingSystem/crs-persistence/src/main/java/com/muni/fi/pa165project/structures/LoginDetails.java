/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.structures;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Radoslav Karlik
 */
@Embeddable
public class LoginDetails implements Serializable {
	
	@Column(nullable = false, unique = true)
	@Length(min = 3, max = 255)
	private String username;
	
	@Column(nullable = false)
	@Length(min = 5, max = 63)
	private String password;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
