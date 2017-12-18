package com.muni.fi.pa165project.service;

import com.muni.fi.pa165project.dao.RecordDao;
import com.muni.fi.pa165project.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
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
    public Record update(Record record) {
        return this.recordDao.update(record);
    }

    @Override
    public void remove(long id) {
        this.recordDao.delete(id);
    }

    @Override
    public Record getRecord(long id) {
        return this.recordDao.findById(id);
    }

    @Override
    public List<Record> getAllRecordsOfUser(long userId) {
        return this.recordDao.getAllRecordsOfUserSortedFromNewest(userId);
    }

    @Override
    public List<Record> getLastNRecordsOfUser(long userId, int count) {
        List<Record> records = this.getAllRecordsOfUser(userId);
        if (count < records.size())
            return records.subList(0, count);
        else
            return records;
    }

    @Override
    public List<Record> getFilteredRecords(long userId, LocalDateTime from, LocalDateTime to) {
        return this.recordDao.findByTime(userId, from, to);
    }

	@Override
	public long getNumberOfUserRecords(long userId) {
		return recordDao.getNumberOfAllRecordsOfUser(userId);
		
	}
    
     
}
