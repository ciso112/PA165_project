package com.muni.fi.pa165project.service;

import com.muni.fi.pa165project.dao.RecordDao;
import com.muni.fi.pa165project.entity.Record;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Radoslav Karlik
 * @author Radim Podola
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordDao recordDao;

    @Override
    public void create(Record record) {
        this.recordDao.create(record);
    }

    @Override
    public void update(Record record) {
        this.recordDao.update(record);
    }

    @Override
    public void remove(long id) {
        Record record = this.recordDao.findById(id);
        this.recordDao.delete(record);
    }

    @Override
    public Record getRecord(long id) {
        return this.recordDao.findById(id);
    }

    @Override
    public List<Record> getAllRecords() {
        return this.recordDao.findAll();
    }

    @Override
    public List<Record> getFilteredRecords(LocalDate date) {
        return this.recordDao.findByDate(date);
    }

    @Override
    public List<Record> getFilteredRecords(LocalDateTime from, LocalDateTime to) {
        return this.recordDao.findByTime(from, to);
    }
}
