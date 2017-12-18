package com.muni.fi.pa165project.service;

import com.muni.fi.pa165project.dao.RecordDao;
import com.muni.fi.pa165project.dao.UserDao;
import com.muni.fi.pa165project.entity.Record;
import com.muni.fi.pa165project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

/**
 * @author Radoslav Karlik
 * @author Lukáš Císar
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RecordDao recordDao;

    @Override
    public User findById(long userId) {
        return this.userDao.findById(userId);
    }

    @Override
    public User findByEmail(String email) {
        return this.userDao.findByEmail(email);
    }

    @Override
    public void createUser(User user) {
        this.userDao.create(user);
    }

    @Override
    public void deleteUser(long userId) {
        this.userDao.delete(userId);
    }

    @Override
    public User updateUser(User user) {
        return this.userDao.update(user);
    }

    @Override
    public int getProgressOfWeeklyCaloriesGoal(long userId) {
        double sum = 0;
        User user = this.findById(userId);
        if (user == null)
            throw new DataRetrievalFailureException("User not found");

        double goal = user.getTrackingSettings().getWeeklyCaloriesGoal();
        if (goal <= 0)
            return 100;

        LocalDate monday = LocalDate.now().with(previousOrSame(MONDAY));
        LocalDateTime start = LocalDateTime.of(monday, LocalTime.MIDNIGHT);
        LocalDateTime end = start.plusWeeks(1).minusSeconds(1);

        //already sorted from earliest
        List<Record> lastWeekRecords = this.recordDao.findByTime(userId, start, end);

        for (Record r : lastWeekRecords) {
            sum += r.getBurnedCalories();
        }
        return (int) Math.max(((sum / goal) * 100), 100);
    }

    @Override
    public User findByCredentials(String username, String password) {
        return this.userDao.findByCredentials(username, password);
    }

    @Override
    public boolean userWithEmailExists(String email) {
        return this.userDao.userWithEmailExists(email);
    }

    @Override
    public boolean userWithUsernameExists(String username) {
        return this.userDao.userWithUsernameExists(username);
    }

	@Override
	public List<User> getUsers() {
		return this.userDao.findAll();
	}
}
