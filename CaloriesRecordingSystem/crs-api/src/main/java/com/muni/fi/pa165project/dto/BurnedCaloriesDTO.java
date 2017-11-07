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
public class BurnedCaloriesDTO {
	
	private Long id;
	
    private int upperWeightBoundary;
 
    private int amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		int hash = 5;
		hash = 67 * hash + this.upperWeightBoundary;
		hash = 67 * hash + this.amount;
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
		if (this.upperWeightBoundary != other.getUpperWeightBoundary()) {
			return false;
		}
		if (this.amount != other.getAmount()) {
			return false;
		}
		return true;
	}
	
}
