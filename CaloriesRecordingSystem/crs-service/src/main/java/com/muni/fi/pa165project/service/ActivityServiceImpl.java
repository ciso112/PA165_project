/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.service;

import com.muni.fi.pa165project.dao.ActivityDao;
import com.muni.fi.pa165project.entity.Activity;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Radoslav Karlik
 */
@Service
public class ActivityServiceImpl implements ActivityService {

	@Inject
	private ActivityDao activityDao;
	
	@Override
	public void create(Activity activity) {
		this.activityDao.create(activity);
	}
	
}
