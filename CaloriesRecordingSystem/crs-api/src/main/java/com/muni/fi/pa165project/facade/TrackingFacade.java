package com.muni.fi.pa165project.facade;

import com.muni.fi.pa165project.dto.RecordDTO;
import com.muni.fi.pa165project.dto.filters.RecordTimeFilterDTO;
import java.util.List;

/**
 * 
 * @author Radoslav Karlik
 * @author Radim Podola
 */
public interface TrackingFacade {

    /**
     * Create record
     * @param recordDto record to create
     */
    void createRecord(RecordDTO recordDto);

    /**
     * Update record
     * @param recordDto record to create 
     */
    void updateRecord(RecordDTO recordDto);

    /**
     * Remove record
     * @param id record id
     */
    void removeRecord(long id);

    /**
     * Get record
     * @param id record id
     * @return record
     */
    RecordDTO getRecord(long id);
    
    /**
     * Get all user's records
     * @param userId User id
     * @return list of all user's records
     */
    List<RecordDTO> getAllRecords(long userId);
    
    /**
     * Get last N user's records
     * @param userId user id
     * @param count number of last records
     * @return list of last N user's records
     */
    List<RecordDTO> getLastNRecords(long userId, int count);

    /**
     * Get filtered user's records
     * @param userId user id
     * @param timeFilter filter specifing time
     * @return list of user's records
     */
    List<RecordDTO> getFilteredRecords(long userId, RecordTimeFilterDTO timeFilter);

    /**
     * Get progress of burned calories in the current week
     * @param userId User id
     * @return percentage of calories burned of targeted amount (0..100)
     */
    int getWeekProgressOfBurnedCalories(long userId);
}
