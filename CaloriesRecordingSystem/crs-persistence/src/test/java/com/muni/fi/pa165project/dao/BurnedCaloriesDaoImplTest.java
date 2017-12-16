package com.muni.fi.pa165project.dao;

import com.muni.fi.pa165project.config.AppContextConfiguration;
import com.muni.fi.pa165project.entity.Activity;
import com.muni.fi.pa165project.entity.BurnedCalories;
import com.muni.fi.pa165project.enums.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Radoslav Karlik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContextConfiguration.class)
@Transactional
public class BurnedCaloriesDaoImplTest {

    @Autowired
    private BurnedCaloriesDao bcDao;

    @Autowired
    private ActivityDao acDao;

    private BurnedCalories bc1;
    private BurnedCalories bc2;
    private BurnedCalories bc3;

    private Activity ac;

    @Transactional
    private void createDummyActivity() {
        this.ac = new Activity();
        this.ac.setName("Activity 1");
        this.ac.setDescription("test activity");
        this.ac.setCategory(Category.RUNNING);
        this.acDao.create(ac);
    }

    @Before
    public void init() {
        createDummyActivity();

        this.bc1 = new BurnedCalories();
        this.bc1.setUpperWeightBoundary(100);
        this.bc1.setActivity(ac);
        this.bc1.setAmount(50);

        this.bc2 = new BurnedCalories();
        this.bc2.setUpperWeightBoundary(200);
        this.bc2.setActivity(ac);
        this.bc2.setAmount(100);

        this.bc3 = new BurnedCalories();
        this.bc3.setUpperWeightBoundary(300);
        this.bc3.setActivity(ac);
        this.bc3.setAmount(200);

        this.bcDao.create(bc1);
        this.bcDao.create(bc2);
        this.bcDao.create(bc3);
    }

    @Test
    @Rollback(true)
    public void testCreate() {
        BurnedCalories bc = new BurnedCalories();

        bc.setUpperWeightBoundary(75);
        bc.setAmount(200);
        bc.setActivity(ac);

        this.bcDao.create(bc);

        List<BurnedCalories> bcs = this.bcDao.findAll();
        Assert.assertTrue(bcs.size() == 4);
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Rollback(true)
    public void testCreateWithoutActivity() {
        BurnedCalories bc = new BurnedCalories();

        bc.setUpperWeightBoundary(75);
        bc.setAmount(200);

        this.bcDao.create(bc);
    }

    @Test
    @Rollback(true)
    public void testUpdate() {
        this.bc2.setUpperWeightBoundary(201);
        this.bcDao.update(bc2);

        BurnedCalories bc2FromDB = this.bcDao.findById(this.bc2.getId());

        Assert.assertTrue(201 == bc2FromDB.getUpperWeightBoundary());
    }

    @Test
    @Rollback(true)
    public void testRemove() {
        int initialCount = this.bcDao.findAll().size();

        this.bcDao.delete(bc1);

        int finalCount = this.bcDao.findAll().size();

        Assert.assertEquals(initialCount, finalCount + 1);
    }

    @Test
    @Rollback(true)
    public void testFindAll() {
        List<BurnedCalories> bcs = this.bcDao.findAll();

        Assert.assertTrue(bcs.size() == 3);
    }

    @Test
    @Rollback(true)
    public void testFindById() {
        BurnedCalories bcs = this.bcDao.findById(bc1.getId());

        Assert.assertTrue(bcs.equals(bc1));
    }

    @Test
    @Rollback(true)
    public void testgetWeightRange() {
        BurnedCalories bcs = this.bcDao.getWeightRange(ac.getId(), 20);
        Assert.assertTrue(bcs.equals(bc1));
        bcs = this.bcDao.getWeightRange(ac.getId(), 500);
        Assert.assertTrue(bcs.equals(bc3));
        bcs = this.bcDao.getWeightRange(ac.getId(), 200);
        Assert.assertTrue(bcs.equals(bc2));
        Assert.assertNull(this.bcDao.getWeightRange(2, 500));
    }
}
