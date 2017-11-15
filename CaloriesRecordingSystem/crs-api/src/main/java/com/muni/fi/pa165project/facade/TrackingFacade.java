package com.muni.fi.pa165project.facade;

import com.muni.fi.pa165project.dto.RecordDTO;
import com.muni.fi.pa165project.dto.filters.RecordTimeFilterDTO;
import java.util.List;

/**
 *
 * @author Radim Podola
 */
public interface TrackingFacade {

    /**
     * Create record
     * @param recordDto 
     */
    void createRecord(RecordDTO recordDto);

    /**
     * Update record
     * @param recordDto 
     */
    void updateRecord(RecordDTO recordDto);

    /**
     * Remove record
     * @param id record id
     */
    void removeRecord(long id);

    /**
     * Get all records for given user
     * @param userId user id
     * @return list of all records
     */
    List<RecordDTO> getAllRecords(long userId);

    /**
     * Get record
     * @param id record id
     * @return record
     */
    RecordDTO getRecord(long id);
    
    /**
     * Get last N records for given user
     * @param userId user id
     * @param count number of last records
     * @return list of last N records
     */
    List<RecordDTO> getLastNRecords(long userId, int count);

    /**
     * Get progress of burned calories in current week
     * @param userId User id
     * @return amount of burned calories
     */
    int getWeekProgressOfBurnedCalories(long userId);

    /**
     * Get records by filter
     * @param timeFilter filter specifing time
     * @return list of records
     */
    List<RecordDTO> getFilteredRecords(RecordTimeFilterDTO timeFilter);    
}
