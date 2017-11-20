package com.muni.fi.pa165project.service.facade;

import com.muni.fi.pa165project.dto.RecordDTO;
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
        
        float amount = activityService.getBurnedCalory(activity.getId(), weight);
        record.setBurnedCalories((int) (amount * record.getDuration()));
        
        this.recordService.create(record);
    }

    @Override
    public void updateRecord(RecordDTO recordDto) {
        
        Record record = super.map(recordDto, Record.class);
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

    @Override
    public List<RecordDTO> getAllRecords() {
        
        List<Record> recordEntites = this.recordService.getAllRecords();
        List<RecordDTO> records = super.mapToList(recordEntites, RecordDTO.class);
        
        return records;
    }
}
