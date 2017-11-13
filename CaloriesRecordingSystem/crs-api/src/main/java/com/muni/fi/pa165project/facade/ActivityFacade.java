/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.facade;

import com.muni.fi.pa165project.dto.ActivityDTO;
import com.muni.fi.pa165project.dto.BurnedCaloriesDTO;

/**
 *
 * @author Radoslav Karlik
 */
public interface ActivityFacade {
	
	public void createActivity(ActivityDTO activityDTO);
	
	public void addBurnedCaloriesToActivity(BurnedCaloriesDTO burnedCaloriesDTO);
	
}
