/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.dto;

/**
 *
 * @author Radoslav Karlik
 */
public class TrackingSettingsDTO {
	
	private long userId;
	
	private int weeklyCaloriesGoal;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public int getWeeklyCaloriesGoal() {
		return weeklyCaloriesGoal;
	}

	public void setWeeklyCaloriesGoal(int weeklyCaloriesGoal) {
		this.weeklyCaloriesGoal = weeklyCaloriesGoal;
	}
	
}