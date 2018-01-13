package com.muni.fi.pa165project.data;

import com.muni.fi.pa165project.dto.*;
import com.muni.fi.pa165project.entity.User;
import com.muni.fi.pa165project.enums.Category;
import com.muni.fi.pa165project.facade.ActivityFacade;
import com.muni.fi.pa165project.facade.TrackingFacade;
import com.muni.fi.pa165project.facade.UserFacade;
import com.muni.fi.pa165project.service.MappingService;
import com.muni.fi.pa165project.service.UserService;
import com.muni.fi.pa165project.structures.LoginDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Loads some sample data to populate the CRS database.
 *
 * @author Radim Podola
 */
@Component
@Transactional
public class SampleDataLoaderImpl implements SampleDataLoader {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoaderImpl.class);

    @Autowired
    ActivityFacade acFacade;

    @Autowired
    UserFacade userFacade;

    @Autowired
    TrackingFacade trackingFacade;

    @Autowired
    UserService userService;

    @Autowired
    MappingService mapper;

    @Override
    @SuppressWarnings("unused")
    public void loadData() {
        log.debug("loadData() start");

        loadActivities();
        loadUsers();
        loadRecords();
    }

    private void loadRecords() {
        long userId = userFacade.getUser("admin@fi.muni.cz").getId();
        long user2Id = userFacade.getUser("rnovak@fi.muni.cz").getId();
        long activityId = acFacade.getAllActivities().get(0).getId();
        createRecord(userId, activityId, LocalDateTime.now(), 100, 50);
        createRecord(userId, activityId, LocalDateTime.now().minusHours(1), 200, 100);
        createRecord(userId, activityId, LocalDateTime.now().minusDays(2), 10, 10);
        createRecord(user2Id, activityId, LocalDateTime.now().minusDays(4), 11, 04);
    }

    private Long createRecord(long userId, long activityId, LocalDateTime time, int distance, int duration) {
        RecordCreateDTO record = new RecordCreateDTO();
        record.setUserId(userId);
        record.setActivityId(activityId);
        record.setAtTime(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(time));
        record.setDistance(distance);
        record.setDuration(duration);

        return trackingFacade.createRecord(record);
    }

    private void loadUsers(){
        //lets create administrator
        createAdmin("Adam Mokrý", true, "17-12-1997", 172, 60, true,
                "admin", "admin", "admin@fi.muni.cz");
        //lets create some members
        createUser("Radim Novák", true,"25-06-1991", 171, 70, false,
                "rnovak", "12345", "rnovak@fi.muni.cz");
        createUser("Martina Tlustá", false, "11-02-2001", 189, 96, false,
                "mtlusta", "12345", "mtlusta@fi.muni.cz");
    }

    private void createUser(String name, boolean male, String date, int height, int weight, boolean admin,
                            String username, String password, String email){
        UserRegisterDTO user = new UserRegisterDTO();
        user.setName(name);
        user.setIsMale(male);
        user.setBirthDate(date);
        user.setHeight(height);
        user.setWeight(weight);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userFacade.createUser(user);
    }

    private void createAdmin(String name, boolean male, String date, int height, int weight, boolean admin,
                             String username, String password, String email){
        UserRegisterDTO user = new UserRegisterDTO();
        user.setName(name);
        user.setIsMale(male);
        user.setBirthDate(date);
        user.setHeight(height);
        user.setWeight(weight);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userFacade.createAdmin(user);
    }

    private void loadActivities() {
        Long id;
        id = createActivity("Aerobics", "General aerobics exercise", Category.AEROBICS);
        addBurnedCalories(id, 384, 59);
        addBurnedCalories(id, 457, 70);
        addBurnedCalories(id, 531, 82);
        addBurnedCalories(id, 605, 93);
        id = createActivity("Basketball", "Playing competitive basketball game", Category.SPORT);
        addBurnedCalories(id, 472, 59);
        addBurnedCalories(id, 563, 70);
        addBurnedCalories(id, 654, 82);
        addBurnedCalories(id, 745, 93);
        id = createActivity("Cycling", "Leisure bicycling", Category.CYCLING);
        addBurnedCalories(id, 236, 59);
        addBurnedCalories(id, 281, 70);
        addBurnedCalories(id, 327, 82);
        addBurnedCalories(id, 372, 93);
        id = createActivity("Cycle racing", "Racing on bicycles", Category.CYCLING);
        addBurnedCalories(id, 944, 59);
        addBurnedCalories(id, 1126, 70);
        addBurnedCalories(id, 1308, 82);
        addBurnedCalories(id, 1489, 93);
        id = createActivity("Farming", "Baling hay, cleaning barn", Category.WORK);
        addBurnedCalories(id, 472, 59);
        addBurnedCalories(id, 563, 70);
        addBurnedCalories(id, 654, 82);
        addBurnedCalories(id, 745, 93);
        id = createActivity("Fishing", "Fishing from the boat", Category.HOBBY);
        addBurnedCalories(id, 148, 59);
        addBurnedCalories(id, 176, 70);
        addBurnedCalories(id, 204, 82);
        addBurnedCalories(id, 233, 93);
        id = createActivity("Golf", "General playing golf", Category.SPORT);
        addBurnedCalories(id, 266, 59);
        addBurnedCalories(id, 317, 70);
        addBurnedCalories(id, 368, 82);
        addBurnedCalories(id, 419, 93);
        id = createActivity("Ballroom dancing", "Fast ballroom dancing", Category.DANCE);
        addBurnedCalories(id, 325, 59);
        addBurnedCalories(id, 387, 70);
        addBurnedCalories(id, 449, 82);
        addBurnedCalories(id, 512, 93);
        id = createActivity("Boxing", "Punching bag", Category.SPORT);
        addBurnedCalories(id, 354, 59);
        addBurnedCalories(id, 422, 70);
        addBurnedCalories(id, 490, 82);
        addBurnedCalories(id, 558, 93);
        id = createActivity("Running", "Running around 6 mph (10 min mile)", Category.RUNNING);
        addBurnedCalories(id, 590, 59);
        addBurnedCalories(id, 704, 70);
        addBurnedCalories(id, 817, 82);
        addBurnedCalories(id, 931, 93);
    }

    private Long createActivity(String name, String description, Category category){
        ActivityCreateDTO a = new ActivityCreateDTO();
        a.setName(name);
        a.setDescription(description);
        a.setCategory(category.getId());
        return acFacade.createActivity(a);
    }

    private void addBurnedCalories(Long activityId, int amount, int edge){
        ActivityDetailDTO detail = acFacade.getActivityDetail(activityId);
        ActivityUpdateDTO update = mapper.map(detail, ActivityUpdateDTO.class);
        BurnedCaloriesDTO bc = new BurnedCaloriesDTO();
        bc.setAmount(amount);
        bc.setUpperWeightBoundary(edge);
        update.getBurnedCalories().add(bc);
        acFacade.editActivity(update);
    }
}
