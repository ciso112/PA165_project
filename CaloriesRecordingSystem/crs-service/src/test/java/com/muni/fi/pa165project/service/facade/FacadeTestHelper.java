package com.muni.fi.pa165project.service.facade;

import com.muni.fi.pa165project.dto.*;
import com.muni.fi.pa165project.dto.ActivityCreateDTO;
import com.muni.fi.pa165project.enums.Category;
import com.muni.fi.pa165project.structures.CategoryObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * @author Radim Podola
 * @author Lukáš Císar
 */
public class FacadeTestHelper {

    public static UserRegisterDTO initUserRegister() {
        UserRegisterDTO user = new UserRegisterDTO();
        user.setBirthDate(LocalDate.of(2017, Month.DECEMBER, 15).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        user.setIsMale(true);
        user.setName("Lukas");
        user.setHeight(180);
        user.setWeight(77);
        user.setUsername("ciso112");
        user.setPassword("abcdefgh");
        user.setEmail("ciso112@protonmail.com");
        return user;
    }

    public static ActivityDetailDTO initActivityDetail() {
        ActivityDetailDTO activity = new ActivityDetailDTO();
        activity.setName("Running as hell");
        activity.setDescription("Some dummy description");
        CategoryObject category = CategoryObject.from(Category.RUNNING);
        activity.setCategory(new CategoryDTO(category.getId(), category.getName(), category.getDescription()));
        return activity;
    }
    
    public static ActivityCreateDTO initActivity() {
        ActivityCreateDTO activity = new ActivityCreateDTO();
        activity.setName("Running as hell");
        activity.setDescription("Some dummy description");
        activity.setCategory(Category.RUNNING.getId());
        return activity;
    }

    public static BurnedCaloriesDTO initBurnedCalories() {
        BurnedCaloriesDTO bc = new BurnedCaloriesDTO();
        bc.setUpperWeightBoundary(200);
        bc.setAmount(50);
        return bc;
    }

    public static RecordCreateDTO initCreateRecord(Long userId, Long activityId) {
        RecordCreateDTO record = new RecordCreateDTO();
        record.setUserId(userId);
        record.setDistance(100);
        record.setDuration(1);
        record.setActivityId(activityId);
        record.setAtTime(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(LocalDateTime.now()));
        return record;
    }
}
