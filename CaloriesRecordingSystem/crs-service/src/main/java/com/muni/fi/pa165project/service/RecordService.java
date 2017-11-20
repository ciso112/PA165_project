package com.muni.fi.pa165project.service;

import com.muni.fi.pa165project.entity.Record;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Radoslav Karlik
 * @author Radim Podola
 */
public interface RecordService {

    /**
     * Create record
     * @param record record to create
     */
    void create(Record record);
    
    /**
     * Update record
     * @param record record to update
     */
    void update(Record record);

    /**
     * Delete record
     * @param id id of record to delete
     */
    void remove(long id);

    /**
     * Get record object
     * @param id id of record to get
     * @return record object
     */
    Record getRecord(long id);
    
    /**
     * Get all records
     * @return list of all records
     */
    List<Record> getAllRecords();
    
    /**
     * Get records at certain date
     * @param date date
     * @return list of records
     */
    List<Record> getFilteredRecords(LocalDate date);
    
    /**
     * Get records between certain times
     * @param from starting time
     * @param to ending time
     * @return list of records
     */
    List<Record> getFilteredRecords(LocalDateTime from, LocalDateTime to);
}
