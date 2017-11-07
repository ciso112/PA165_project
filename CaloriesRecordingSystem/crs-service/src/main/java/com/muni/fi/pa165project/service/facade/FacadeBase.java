/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.service.facade;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Radoslav Karlik
 */
@Service
public abstract class FacadeBase {
	
	@Autowired
	protected DozerBeanMapper mapper;
	
	protected <T> T map(Object source, Class<T> destinationClass) {
		return this.mapper.map(source, destinationClass);
	}
	
}
