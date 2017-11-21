package com.muni.fi.pa165project.service.facade;

import com.muni.fi.pa165project.dto.ActivityDTO;
import com.muni.fi.pa165project.dto.RecordDTO;
import com.muni.fi.pa165project.dto.UserDTO;
import com.muni.fi.pa165project.enums.Category;
import com.muni.fi.pa165project.enums.GenderEnum;
import com.muni.fi.pa165project.facade.ActivityFacade;
import com.muni.fi.pa165project.facade.TrackingFacade;
import com.muni.fi.pa165project.facade.UserFacade;
import com.muni.fi.pa165project.structures.LoginDetails;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 *
 * @author Radim Podola
 */
public class TrackingFacadeImplTest {
    
    @Autowired
    private TrackingFacade rcFac;
    
    @Autowired
    private ActivityFacade acFac = new ActivityFacadeImpl();
    
    @Autowired
    private UserFacade usFac;
    
    private ActivityDTO act;
    private UserDTO user;
    private RecordDTO record;

    @Before
    public void initActivityDTO() {
        this.act = new ActivityDTO();
        this.act.setName("Run");
        this.act.setDescription("running by feet");
        this.act.setCategory(Category.DANCING);
        
        acFac.createActivity(this.act);
        this.act = acFac.getAllActivities().get(0);
    }
    
    @Before
    public void initUserDTO() {
        this.user = new UserDTO();
        this.user.setName("Radim");
        this.user.setGender(GenderEnum.MALE);
        this.user.setBirthDate(LocalDate.now());
        LoginDetails login = new LoginDetails();
        user.setHeight(123);
        user.setWeight(75);
        login.setUsername("rpo");
        login.setPassword("12312345");
        login.setEmail("rp@see.com");
        //this.user.setLoginDetails(login);
        
        this.user.setId(1L);
        //usFac.createUser(user);
    }
    
    @Before
    public void initRecord() {
        this.record = new RecordDTO();
        this.record.setAtTime(LocalDateTime.now());
        this.record.setActivityId(this.act.getId());
        this.record.setUserId(this.user.getId());
        this.record.setDistance(100);
    }

    /**
     * Test of createRecord method, of class TrackingFacadeImpl.
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testCreateRecord() {
        
        rcFac.createRecord(record);
        
        List<RecordDTO> records = rcFac.getAllRecords();
        
        Assert.assertEquals(1, records.size());
        Assert.assertTrue(this.user.getId().equals(records.get(0).getUserId()));
        Assert.assertTrue(this.act.getId().equals(records.get(0).getActivityId()));
    }

    /**
     * Test of updateRecord method, of class TrackingFacadeImpl.
     */
    @org.junit.Test
    public void testUpdateRecord() {
        System.out.println("updateRecord");
        RecordDTO recordDto = null;
        TrackingFacadeImpl instance = new TrackingFacadeImpl();
        instance.updateRecord(recordDto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeRecord method, of class TrackingFacadeImpl.
     */
    @org.junit.Test
    public void testRemoveRecord() {
        System.out.println("removeRecord");
        long id = 0L;
        TrackingFacadeImpl instance = new TrackingFacadeImpl();
        instance.removeRecord(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecord method, of class TrackingFacadeImpl.
     */
    @org.junit.Test
    public void testGetRecord() {
        System.out.println("getRecord");
        long id = 0L;
        TrackingFacadeImpl instance = new TrackingFacadeImpl();
        RecordDTO expResult = null;
        RecordDTO result = instance.getRecord(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllRecords method, of class TrackingFacadeImpl.
     */
    @org.junit.Test
    public void testGetAllRecords() {
        System.out.println("getAllRecords");
        TrackingFacadeImpl instance = new TrackingFacadeImpl();
        List<RecordDTO> expResult = null;
        List<RecordDTO> result = instance.getAllRecords();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
