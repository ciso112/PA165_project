package com.muni.fi.pa165project.service;

import com.muni.fi.pa165project.entity.Activity;
import com.muni.fi.pa165project.enums.Category;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Radoslav Karlik
 * @author Radim Podola
 */
public interface ActivityService {

    void create(Activity activity);

    Activity findById(long id);

    void update(Activity activity);

    List<Activity> getAllActivities();

    void remove(long id);

    List<Activity> getFilteredActivities(Collection<Category> categories);

    /**
    * Get the burned calory for activity with certain weight
    * @param id activity id
    * @param weight weight
    * @return burned calories per hour
    */
    int getBurnedCaloriesPerHour(long id, double weight);
}
