/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.facade;

import com.muni.fi.pa165project.dto.ActivityDTO;
import com.muni.fi.pa165project.dto.BurnedCaloriesDTO;
import java.util.List;

/**
 *
 * @author Radoslav Karlik
 */
public interface ActivityFacade {
	
	void createActivity(ActivityDTO activityDTO);
	
	void addBurnedCaloriesToActivity(BurnedCaloriesDTO burnedCaloriesDTO);
	
	void removeBurnedCaloriesFromActivity(BurnedCaloriesDTO burnedCaloriesDTO);
	
	void removeActivity(long id);
	
	List<ActivityDTO> getAllActivities();
	
}
