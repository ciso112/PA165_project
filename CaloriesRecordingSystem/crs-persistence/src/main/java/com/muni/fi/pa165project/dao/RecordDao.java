package com.muni.fi.pa165project.dao;

import com.muni.fi.pa165project.entity.Record;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Lukáš Císar
 */
public interface RecordDao {

    /**
     * Finds record by id
     *
     * @param id record id
     * @return record object
     */
    Record findById(long id);

    /**
     * Insert record into database
     *
     * @param record record object
     */
    void create(Record record);

    /**
     * Update record in database
     *
     * @param record record object
     * @return updated record
     */
    Record update(Record record);

    /**
     * Delete record from database
     *
     * @param record record object
     */
    void delete(Record record);

    /**
     * Delete record from database
     *
     * @param id record id
     */
    void delete(long id);

    /**
     * Returns all records
     *
     * @return list of record objects
     */
    List<Record> findAll();

    /**
     * Returns all records of User from specific time frame ordered from earliest
     *
     * @param userId user id
     * @param from start timestamp
     * @param to end timestamp
     * @return list of record objects
     */
    List<Record> findByTime(long userId, LocalDateTime from, LocalDateTime to);

    /**
     * Returns all records of user sorted by time from newest
     *
     * @param userId user id
     * @return list of record objects
     */
    List<Record> getAllRecordsOfUserSortedFromNewest(long userId);
    
    /**
     * Returns all records of user sorted by time from newest
     *
     * @param userId user id
     * @return number of records
     */
    long getNumberOfAllRecordsOfUser(long userId);
    
    
    

}
