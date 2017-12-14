package com.muni.fi.pa165project.dao;

import com.muni.fi.pa165project.config.AppContextConfiguration;
import com.muni.fi.pa165project.entity.User;
import com.muni.fi.pa165project.enums.GenderEnum;
import com.muni.fi.pa165project.structures.LoginDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Lukáš Císar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContextConfiguration.class)
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    private User user;

    @Before
    public void initUser() {
        this.user = new User();
        user.setBirthDate(LocalDate.now());
        user.setIsMale(true);
        user.setName("Lukas");
        user.setHeight(180);
        user.setWeight(77);
        user.setIsAdmin(true);

        LoginDetails login = new LoginDetails();
        login.setUsername("ciso112");
        login.setPassword("abcdefgh");
        login.setEmail("ciso112@protonmail.com");
        this.user.setLoginDetails(login);
        userDao.create(user);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCreate() {

        userDao.create(user);

        List<User> users = this.userDao.findAll();
        Assert.assertTrue(users.size() == 1);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdate() {

        userDao.create(user);


        user.setName("Martin");
        userDao.update(user);

        User dbUser = userDao.findById(user.getId());

        Assert.assertEquals("Martin", dbUser.getName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDelete() {

        int initialCount = userDao.findAll().size();
        Assert.assertTrue("Nothing to delete", initialCount != 0);

        userDao.delete(user);

        int finalCount = userDao.findAll().size();

        Assert.assertTrue("User was not deleted", (initialCount == (finalCount + 1)));

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindByUserName() {
        User foundUser = userDao.findByUserName("ciso112");

        Assert.assertEquals(user.getName(), foundUser.getName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindAll() {

        List<User> users = userDao.findAll();

        Assert.assertTrue("Users is null", users != null);
        Assert.assertTrue("Users have incorrect number of results", users.size() == 1);

        User usr = new User();

        usr.setBirthDate(LocalDate.now());
        usr.setName("Vlado");
        usr.setWeight(75);
        usr.setHeight(175);
        LoginDetails login = new LoginDetails();
        login.setUsername("eavf");
        login.setPassword("abcdefgh");
        login.setEmail("vlado@protonmail.com");
        usr.setLoginDetails(login);
        userDao.create(usr);
        users = userDao.findAll();

        Assert.assertTrue("Users have incorrect number of results2", users.size() == 2);

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindByEmail() {

        User foundUser = userDao.findByEmail("ciso112@protonmail.com");
        Assert.assertEquals(user.getLoginDetails().getEmail(), foundUser.getLoginDetails().getEmail());
    }
}


