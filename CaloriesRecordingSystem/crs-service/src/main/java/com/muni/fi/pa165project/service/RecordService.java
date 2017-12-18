package com.muni.fi.pa165project.service;

import com.muni.fi.pa165project.entity.Record;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Radoslav Karlik
 * @author Radim Podola
 */
public interface RecordService {

    /**
     * Create record
     *
     * @param record record to create
     */
    void create(Record record);

    /**
     * Update record
     *
     * @param record record to update
     * @return updated record
     */
    Record update(Record record);

    /**
     * Delete record
     *
     * @param id id of record to delete
     */
    void remove(long id);

    /**
     * Get record object
     *
     * @param id id of record to get
     * @return record object
     */
    Record getRecord(long id);

    /**
     * Get all records of user
     * sorted by time
     *
     * @param userId user's id
     * @return list of all records
     */
    List<Record> getAllRecordsOfUser(long userId);

    /**
     * get last N records of user
     *
     * @param userId user's id
     * @param count  count of last records
     * @return list of records
     */
    List<Record> getLastNRecordsOfUser(long userId, int count);

    /**
     * Get User records between certain times
     *
     * @param userId user id
     * @param from   starting time
     * @param to     ending time
     * @return list of records
     */
    List<Record> getFilteredRecords(long userId, LocalDateTime from, LocalDateTime to);
    
    /**
     * Get User records between certain times
     *
     * @param userId user id
     * @return long number of records
     */
    long getNumberOfUserRecords(long userId);

}
