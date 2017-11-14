/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.dto.filters;

import com.muni.fi.pa165project.enums.Category;
import java.util.Set;

/**
 *
 * @author Radoslav Karlik
 */
public class ActivityFilterDTO {
	
	private Set<Category> categories;

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
}
