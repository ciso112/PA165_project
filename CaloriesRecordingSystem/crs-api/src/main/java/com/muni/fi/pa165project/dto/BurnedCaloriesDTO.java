/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.dto;

import java.util.Objects;

/**
 *
 * @author Radoslav Karlik
 */
public class BurnedCaloriesDTO {
	
	private Long id;
	
	private long activityId;
	
    private int upperWeightBoundary;
 
    private int amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}
	
	public int getUpperWeightBoundary() {
		return upperWeightBoundary;
	}

	public void setUpperWeightBoundary(int upperWeightBoundary) {
		this.upperWeightBoundary = upperWeightBoundary;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.activityId);
		hash = 97 * hash + this.upperWeightBoundary;
		hash = 97 * hash + this.amount;
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
		if (!(obj instanceof BurnedCaloriesDTO)) {
			return false;
		}
		final BurnedCaloriesDTO other = (BurnedCaloriesDTO) obj;
		if (this.activityId != other.getActivityId()) {
			return false;
		}
		if (this.upperWeightBoundary != other.getUpperWeightBoundary()) {
			return false;
		}
		if (this.amount != other.getAmount()) {
			return false;
		}
		return true;
	}
	
}
