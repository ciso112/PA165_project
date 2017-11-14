/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.service.facade;

import com.muni.fi.pa165project.dto.ActivityDTO;
import com.muni.fi.pa165project.dto.filters.ActivityFilterDTO;
import com.muni.fi.pa165project.dto.BurnedCaloriesDTO;
import com.muni.fi.pa165project.entity.Activity;
import com.muni.fi.pa165project.entity.BurnedCalories;
import com.muni.fi.pa165project.enums.Category;
import com.muni.fi.pa165project.facade.ActivityFacade;
import com.muni.fi.pa165project.service.ActivityService;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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

	@Override
	public List<ActivityDTO> getAllActivities() {
		List<Activity> activities = this.activityService.getAllActivities();
		List<ActivityDTO> activitiesDto = super.mapToList(activities, ActivityDTO.class);
		return activitiesDto;
	}

	@Override
	public void removeBurnedCaloriesFromActivity(BurnedCaloriesDTO burnedCaloriesDTO) {
		Activity activity = this.activityService.findById(burnedCaloriesDTO.getActivityId());
		activity.getBurnedCalories().removeIf(bc -> Objects.equals(bc.getId(), burnedCaloriesDTO.getId()));
		this.activityService.update(activity);
	}

	@Override
	public void removeActivity(long id) {
		this.activityService.remove(id);
	}

	@Override
	public List<ActivityDTO> getActivities(ActivityFilterDTO activityFilter) {
		Collection<Category> categories = activityFilter.getCategories();
		List<Activity> filteredActivities = this.activityService.getFilteredActivities(categories);
		List<ActivityDTO> activitiesDTO = super.mapToList(categories, ActivityDTO.class);
		return activitiesDTO;
	}
	
}
