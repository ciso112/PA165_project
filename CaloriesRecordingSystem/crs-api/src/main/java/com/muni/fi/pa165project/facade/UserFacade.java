/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.facade;

import java.util.List;

import com.muni.fi.pa165project.dto.RecordDTO;
import com.muni.fi.pa165project.dto.filters.RecordTimeFilterDTO;

/**
 *
 * @author Radoslav Karlik
 * @author Radim Podola
 */
public interface UserFacade {

    /**
     * Get progress of burned calories in current week
     * @param userId User id
     * @return amount of burned calories
     */
    int getWeekProgressOfBurnedCalories(long userId);
    
    /**
     * Get last N records
     * @param userId user id
     * @param count number of last records
     * @return list of last N records
     */
    List<RecordDTO> getLastNRecords(long userId, int count);

    /**
     * Get records by filter
     * @param userId user id
     * @param timeFilter filter specifing time
     * @return list of records
     */
    List<RecordDTO> getFilteredRecords(long userId, RecordTimeFilterDTO timeFilter);

    /**
     * Get all records
     * @param userId user id
     * @return list of all records
     */
    List<RecordDTO> getAllRecords(long userId);
}
