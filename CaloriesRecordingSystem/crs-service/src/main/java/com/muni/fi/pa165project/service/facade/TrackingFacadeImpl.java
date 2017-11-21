package com.muni.fi.pa165project.service.facade;

import com.muni.fi.pa165project.dto.RecordDTO;
import com.muni.fi.pa165project.dto.filters.RecordTimeFilterDTO;
import com.muni.fi.pa165project.entity.Activity;
import com.muni.fi.pa165project.entity.Record;
import com.muni.fi.pa165project.entity.User;
import com.muni.fi.pa165project.facade.TrackingFacade;
import com.muni.fi.pa165project.service.ActivityService;
import com.muni.fi.pa165project.service.RecordService;
import com.muni.fi.pa165project.service.UserService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Radoslav Karlik
 * @author Radim Podola
 */
@Service
@Transactional
public class TrackingFacadeImpl extends FacadeBase implements TrackingFacade {

    @Autowired
    private RecordService recordService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Override
    public void createRecord(RecordDTO recordDto) {
        Record record = super.map(recordDto, Record.class);

        Activity activity = this.activityService.findById(recordDto.getActivityId());
        User user = this.userService.findById(recordDto.getUserId());

        double weight = user.getWeight();
        record.setWeight(weight);
        record.setActivity(activity);
        record.setUser(user);        
        record.setBurnedCalories(calculateAmountOfCalories(record));
        
        this.recordService.create(record);
    }

    @Override
    public void updateRecord(RecordDTO recordDto) {
        
        Record record = super.map(recordDto, Record.class);
        
        //TODO will this work?? Is ID in DTO known??
        Record old = this.recordService.getRecord(record.getId());
        if (!record.getActivity().equals(old.getActivity())
            || record.getDuration() != old.getDuration()){
            record.setBurnedCalories(calculateAmountOfCalories(record));
        }
        
        this.recordService.update(record);
    }

    @Override
    public void removeRecord(long id) {
        
        this.recordService.remove(id);
    }

    @Override
    public RecordDTO getRecord(long id) {
        
        RecordDTO recordDto = super.map(this.recordService.getRecord(id), RecordDTO.class);
        
        return recordDto;
    }
    
    private int calculateAmountOfCalories(Record record){
        double weight = record.getUser().getWeight();
        Long activityId = record.getActivity().getId();
        
        int amount = activityService.getBurnedCaloriesPerHour(activityId, weight);
        return (int) amount * record.getDuration();
    }

    @Override
    public List<RecordDTO> getAllRecords(long userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecordDTO> getLastNRecords(long userId, int count) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecordDTO> getFilteredRecords(long userId, RecordTimeFilterDTO timeFilter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getWeekProgressOfBurnedCalories(long userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
