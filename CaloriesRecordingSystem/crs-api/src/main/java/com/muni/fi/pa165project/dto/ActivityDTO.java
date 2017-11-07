/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.dto;

import com.muni.fi.pa165project.enums.Category;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Radoslav Karlik
 */
public class ActivityDTO {
	
	private Long id;

	private String name;
		
	private String description;
	
	private Category category;

	private Set<BurnedCaloriesDTO> burnedCalories = new HashSet<>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<BurnedCaloriesDTO> getBurnedCalories() {
		return burnedCalories;
	}

	public void setBurnedCalories(Set<BurnedCaloriesDTO> burnedCalories) {
		this.burnedCalories = burnedCalories;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 13 * hash + Objects.hashCode(this.name);
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
		if (!(obj instanceof ActivityDTO)) {
			return false;
		}
		final ActivityDTO other = (ActivityDTO) obj;
		if (!Objects.equals(this.name, other.getName())) {
			return false;
		}
		return true;
	}

	
}
