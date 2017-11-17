package com.muni.fi.pa165project.dto;

import java.time.LocalDate;
import java.util.Objects;
import com.muni.fi.pa165project.enums.GenderEnum;
import com.muni.fi.pa165project.enums.UserEnum;

/**
*
* @author Lukáš Císar
*/

public class UserDTO {

	private Long id;

	private String name;

	private LocalDate birthDate;

	private double weight;

	private double height;

	private GenderEnum gender;

	private UserEnum userRole;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public UserEnum getUserRole() {
		return userRole;
	}

	public void setUserRole(UserEnum userRole) {
		this.userRole = userRole;
	}
	
	@Override
	public int hashCode() {
		int hash = 11;
		hash = 7 * hash + Objects.hashCode(this.name);
		hash = 7 * hash + Objects.hashCode(this.weight);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RecordDTO)) {
			return false;
		}
		final UserDTO other = (UserDTO) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}



}
