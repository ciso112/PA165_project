/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.service.facade;

import com.muni.fi.pa165project.dto.RecordDTO;
import com.muni.fi.pa165project.entity.Activity;
import com.muni.fi.pa165project.entity.Record;
import com.muni.fi.pa165project.entity.User;
import com.muni.fi.pa165project.facade.TrackingFacade;
import com.muni.fi.pa165project.service.ActivityService;
import com.muni.fi.pa165project.service.RecordService;
import com.muni.fi.pa165project.service.UserService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Radoslav Karlik
 */

@Service
@Transactional
public class TrackingFacadeImpl extends FacadeBase implements TrackingFacade {

	@Autowired
	private RecordService recordService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void createRecord(RecordDTO recordDto) {
		Record record = super.map(recordDto, Record.class);
		
		Activity activity = this.activityService.findById(recordDto.getActivityId());
		User user = this.userService.findById(recordDto.getUserId());
		
		record.setActivity(activity);
		record.setUser(user);
		
		this.recordService.createRecord(record);
	}

	@Override
	public List<RecordDTO> getAllRecords(long userId) {
		List<Record> recordEntites = this.recordService.getAllRecordsOfUser(userId);
		List<RecordDTO> records = super.mapToList(recordEntites, RecordDTO.class);
		return records;
	}
	
}
