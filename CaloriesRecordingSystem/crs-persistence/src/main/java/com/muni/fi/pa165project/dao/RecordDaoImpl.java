package com.muni.fi.pa165project.dao;

import com.muni.fi.pa165project.entity.Record;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Radoslav Karlik
 */
@Repository
public class RecordDaoImpl implements RecordDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Record findById(long id) {
        return this.em.find(Record.class, id);

    }

    @Override
    public void create(Record record) {
        this.em.persist(record);
    }

    @Override
    public Record update(Record record) {
        return this.em.merge(record);
    }

    @Override
    public void delete(Record record) {
        boolean isManaged = this.em.contains(record);

        if (isManaged) {
            this.em.remove(record);
        } else {
            this.delete(record.getId());
        }
    }

    @Override
    public void delete(long id) {
        Record record = this.findById(id);

        if (record != null) {
            this.em.remove(record);
        }
    }

    @Override
    public List<Record> findAll() {
        return this.em.createQuery("SELECT r FROM Record r",
                Record.class)
                .getResultList();
    }

    //Due to weird BETWEEN behaviour with Java8 Times, t has to be like this
    @Override
    public List<Record> findByTime(long userId, LocalDateTime from, LocalDateTime to) {
        List<Record> sorted = this.em.createQuery("SELECT r FROM Record r " +
                        "WHERE r.user.id=:userId ORDER BY r.atTime ASC", Record.class)
                .setParameter("userId", userId)
                .getResultList();
        return sorted.stream()
                .filter(r -> r.getAtTime().isAfter(from) && r.getAtTime().isBefore(to)
                        || r.getAtTime().isEqual(from) || r.getAtTime().isEqual(to))
                .collect(Collectors.toList());
    }

    @Override
    public List<Record> getAllRecordsOfUserSortedFromNewest(long userId) {
        return this.em.createQuery("SELECT r FROM Record r WHERE r.user.id=:userId ORDER BY r.atTime DESC",
                Record.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    
    public long getNumberOfAllRecordsOfUser(long userId) {
		return this.em.createQuery("SELECT COUNT(r) FROM Record r WHERE r.user.id=:userId",
                Long.class)
                .setParameter("userId", userId)
                .getSingleResult();
	}

}
