/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.service.facade;

import com.muni.fi.pa165project.service.utils.DozerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Radoslav Karlik
 */
@Service
public abstract class FacadeBase {
	
	@Autowired
	private DozerHelper dozerHelper;
	
	protected <T> T map(Object source, Class<T> destinationClass) {
		return source == null ? null : this.dozerHelper.map(source, destinationClass);
	}
	
	protected <T, Z> List<T> mapToList(Collection<Z> source, Class<T> destinationClass) {
		return source == null ? null : this.dozerHelper.mapToList(source, destinationClass);
	}
	
}
