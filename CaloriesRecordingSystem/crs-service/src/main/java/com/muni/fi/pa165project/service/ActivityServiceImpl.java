/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.service;

import com.muni.fi.pa165project.dao.ActivityDao;
import com.muni.fi.pa165project.entity.Activity;
import com.muni.fi.pa165project.enums.Category;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Radoslav Karlik
 */
@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityDao activityDao;
	
	@Override
	public void create(Activity activity) {
		this.activityDao.create(activity);
	}

	@Override
	public Activity findById(long id) {
		return this.activityDao.findById(id);
	}

	@Override
	public void update(Activity activity) {
		this.activityDao.update(activity);
	}

	@Override
	public List<Activity> getAllActivities() {
		return this.activityDao.findAll();
	}

	@Override
	public void remove(long id) {
		Activity activity = this.findById(id);
		this.activityDao.delete(activity);
	}

	@Override
	public List<Activity> getFilteredActivities(Collection<Category> categories) {
		return this.activityDao.findByCategories(categories);
	}
	
}
