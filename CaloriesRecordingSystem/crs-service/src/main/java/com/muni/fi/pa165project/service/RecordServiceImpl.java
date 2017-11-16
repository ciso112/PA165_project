package com.muni.fi.pa165project.service;

import com.muni.fi.pa165project.dao.RecordDao;
import com.muni.fi.pa165project.dao.UserDao;
import com.muni.fi.pa165project.entity.Record;
import com.muni.fi.pa165project.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private UserDao userDao;

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
    public List<Record> getAllRecordsOfUser(long userId) {
        User user = this.userDao.findById(userId);
        return new ArrayList<>(user.getActivityRecords());
    }

    @Override
    public List<Record> getFilteredRecords(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Record> getFilteredRecords(LocalDateTime from, LocalDateTime to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
    
    private int calculateBurnedCalories(){
        return 0; //TODO
    }

    @Override
    public List<Record> getAllRecords() {
        return this.recordDao.findAll();
    }

    @Override
    public List<Record> getLastNRecordsOfUser(long userId) {
      // TODO Auto-generated method stub
      return null;
    }
}
