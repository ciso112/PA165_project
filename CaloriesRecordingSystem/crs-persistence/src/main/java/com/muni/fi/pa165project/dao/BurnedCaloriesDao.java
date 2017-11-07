package com.muni.fi.pa165project.dao;

import com.muni.fi.pa165project.entity.BurnedCalories;

import java.util.List;

/**
 * @author Peter Krasnan
 */
public interface BurnedCaloriesDao {

    /**
     * Finds BurnedCalories by id
     *
     * @param id
     * @return found Burned Calories
     */
    BurnedCalories findById(long id);

    /**
     * Returns all BurnedCalories
     *
     * @return list of all BurnedCalories
     */
    List<BurnedCalories> findAll();

    /**
     * Inserts record into database
     *
     * @param burnedCalories
     */
    void create(BurnedCalories burnedCalories);

    /**
     * Updates record in database
     *
     * @param burnedCalories
     */
    void update(BurnedCalories burnedCalories);

    /**
     * Deletes record from database
     *
     * @param burnedCalories
     */
    void delete(BurnedCalories burnedCalories);
}
