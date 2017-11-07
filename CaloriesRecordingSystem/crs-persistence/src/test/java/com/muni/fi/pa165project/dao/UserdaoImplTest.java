package com.muni.fi.pa165project.dao;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.muni.fi.pa165project.config.TestConfig;
import com.muni.fi.pa165project.entity.User;
import com.muni.fi.pa165project.enums.GenderEnum;
import com.muni.fi.pa165project.enums.UserEnum;
import com.muni.fi.pa165project.structures.LoginDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserdaoImplTest {

	@Autowired
	private UserDao userDao;

	private User user;

	@Before
	public void initUser() {
		this.user = new User();
		user.setBirthDate(LocalDate.now());
		user.setGender(GenderEnum.MALE);
		user.setName("Lukas");
		user.setHeight(180);
		user.setWeight(77);
		user.setUserRole(UserEnum.ADMINISTRATOR);

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
	public void deleteTest() {

		int initialCount = userDao.findAll().size();
		Assert.assertTrue("Nothing to delete",initialCount != 0);

		userDao.delete(user);

		int finalCount = userDao.findAll().size();

		Assert.assertTrue("User was not deleted", (initialCount == (finalCount + 1)));

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindByUserName(){
		User foundUser = userDao.findByUserName("ciso112");

		Assert.assertEquals(user.getName(), foundUser.getName());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindAll(){

		List<User> users = userDao.findAll();

		Assert.assertTrue("Users is null", users != null);
		Assert.assertTrue("Users have incorrect number of results", users.size() == 1);

		User usr = new User();

		usr.setBirthDate(LocalDate.now());
		usr.setName("Vlado");
		usr.setWeight(75);
		usr.setHeight(125);
		LoginDetails login = new LoginDetails();
		login.setUsername("eavf");
		login.setPassword("abcdefgh");
		login.setEmail("vlado@protonmail.com");
		usr.setLoginDetails(login);
		userDao.create(usr);
		users = userDao.findAll();

		Assert.assertTrue("Users have incorrect number of results2", users.size() == 2);

	}
}


