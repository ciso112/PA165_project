package com.muni.fi.pa165project.facade;

import com.muni.fi.pa165project.dto.RecordDTO;
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
     * Get all records
     * @return list of all records
     */
    List<RecordDTO> getAllRecords();

}
