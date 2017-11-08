/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Radoslav Karlik
 */
public class RecordDTO {
	
	private Long Id;
	
	private long activityId;
	
	private long userId;
	
	private LocalDateTime atTime;
	
	private double weight;

    private int burnedCalories;

    private int distance;

    private int duration;

	public Long getId() {
		return Id;
	}

	public void setId(Long Id) {
		this.Id = Id;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public LocalDateTime getAtTime() {
		return atTime;
	}

	public void setAtTime(LocalDateTime atTime) {
		this.atTime = atTime;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getBurnedCalories() {
		return burnedCalories;
	}

	public void setBurnedCalories(int burnedCalories) {
		this.burnedCalories = burnedCalories;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 17 * hash + (int) (this.userId ^ (this.userId >>> 32));
		hash = 17 * hash + Objects.hashCode(this.atTime);
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
		if (!(obj instanceof RecordDTO)) {
			return false;
		}
		final RecordDTO other = (RecordDTO) obj;
		if (this.userId != other.userId) {
			return false;
		}
		if (!Objects.equals(this.atTime, other.atTime)) {
			return false;
		}
		return true;
	}
	
}
