/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.service.facade;

import com.muni.fi.pa165project.dto.ActivityDTO;
import com.muni.fi.pa165project.entity.Activity;
import com.muni.fi.pa165project.enums.Category;
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
public class ActivityFacadeImpl implements ActivityFacade {
	
	@Autowired
	private ActivityService activityService;
	
	@Override
	public void CreateActivity(ActivityDTO activityDTO) {
		Activity activity = new Activity();
		
		/*activity.setCategory(Category.WORK);
		activity.setDescription(description);
		activity.setName(name);*/
		
		this.activityService.create(activity);
	}
	
}
