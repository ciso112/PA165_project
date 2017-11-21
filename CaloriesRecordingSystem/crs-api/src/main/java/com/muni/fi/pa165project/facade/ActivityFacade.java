package com.muni.fi.pa165project.facade;

import com.muni.fi.pa165project.dto.ActivityDTO;
import com.muni.fi.pa165project.dto.ActivityDetailDTO;
import com.muni.fi.pa165project.dto.filters.ActivityFilterDTO;
import com.muni.fi.pa165project.dto.BurnedCaloriesDTO;
import java.util.List;

/**
 *
 * @author Radoslav Karlik
 */
public interface ActivityFacade {

    /**
     * Create Activity object
     * @param activityDTO activity information
     */
    void createActivity(ActivityDTO activityDTO);

    /**
     * Edit Activity object
     * @param activityDTO updated activity information
     */
    void editActivity(ActivityDTO activityDTO);
    
    /**
     * Remove Activity object
     * @param id activity id
     */
    void removeActivity(long id);

    /**
     * Add Burned Calorie record to Activity
     * @param burnedCaloriesDTO burned calorie object
     */
    void addBurnedCalorie(BurnedCaloriesDTO burnedCaloriesDTO);

    /**
     * Edit Burned Calorie record in Activity
     * @param burnedCaloriesDTO burned calorie object
     */
    void editBurnedCalorie(BurnedCaloriesDTO burnedCaloriesDTO);

    /**
     * Remove Burned Calorie record from Activity
     * @param burnedCaloriesDTO burned calorie object
     */
    void removeBurnedCalorie(BurnedCaloriesDTO burnedCaloriesDTO);

    /**
     * Get activity detail including calories table
     * @param id activity id
     * @return Activity detail object
     */
    ActivityDetailDTO getActivityDetail(Long id);

    /**
     * Get list of all activities
     * @return list of Activity objects
     */
    List<ActivityDTO> getAllActivities();

    /**
     * Get list of filtered activities
     * @param activityFilter activity filter
     * @return list of Activity objects
     */
    List<ActivityDTO> getActivities(ActivityFilterDTO activityFilter);
}
