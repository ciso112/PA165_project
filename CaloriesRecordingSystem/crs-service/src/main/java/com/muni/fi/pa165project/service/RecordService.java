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

    void create(Record record);

    void update(Record record);

    void remove(long id);

    Record getRecord(long id);
    
    List<Record> getAllRecordsOfUser(long userId);

    List<Record> getFilteredRecords(LocalDate date);
    
    List<Record> getFilteredRecords(LocalDateTime from, LocalDateTime to);
}
