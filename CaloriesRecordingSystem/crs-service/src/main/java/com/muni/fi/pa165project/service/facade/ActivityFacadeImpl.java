/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.service.facade;

import com.muni.fi.pa165project.dto.ActivityDTO;
import com.muni.fi.pa165project.dto.BurnedCaloriesDTO;
import com.muni.fi.pa165project.entity.Activity;
import com.muni.fi.pa165project.entity.BurnedCalories;
import com.muni.fi.pa165project.facade.ActivityFacade;
import com.muni.fi.pa165project.service.ActivityService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Radoslav Karlik
 */
@Service
@Transactional
public class ActivityFacadeImpl extends FacadeBase implements ActivityFacade {
	
	@Autowired
	private ActivityService activityService;
	
	@Override
	public void createActivity(ActivityDTO activityDTO) {
		Activity activity = super.map(activityDTO, Activity.class);
		this.activityService.create(activity);
	}

	@Override
	public void addBurnedCaloriesToActivity(BurnedCaloriesDTO burnedCaloriesDTO) {
		BurnedCalories bc = super.map(burnedCaloriesDTO, BurnedCalories.class);
		
		long activityId = burnedCaloriesDTO.getActivityId();
		
		Activity activity = this.activityService.findById(activityId);
		activity.getBurnedCalories().add(bc);
		this.activityService.update(activity);
	}
	
}
