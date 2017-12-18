package com.muni.fi.pa165project.dao;

import com.muni.fi.pa165project.entity.Activity;
import com.muni.fi.pa165project.enums.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

/**
 * @author Radim Podola
 */
@Repository
public class ActivityDaoImpl implements ActivityDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Activity activity) {
        this.em.persist(activity);
    }

    @Override
    public Activity update(Activity activity) {
        return this.em.merge(activity);
    }

    @Override
    public void delete(Activity activity) {
        boolean isManaged = this.em.contains(activity);

        if (isManaged) {
            this.em.remove(activity);
        } else {
            this.delete(activity.getId());
        }
    }

    @Override
    public void delete(long id) {
        Activity activity = this.findById(id);

        if (activity != null) {
            this.em.remove(activity);
        }
    }

    @Override
    public Activity findById(long id) {
        return this.em.find(Activity.class, id);
    }

    @Override
    public List<Activity> findAll() {
        return this.em.createQuery("SELECT a from Activity a",
                Activity.class).getResultList();
    }

    @Override
    public List<Activity> findByName(String text) {
        TypedQuery<Activity> query;
        query = em.createQuery("SELECT a FROM Activity a "
                + "WHERE a.name LIKE :text", Activity.class);
        query.setParameter("text", "%" + text + "%");

        return query.getResultList();
    }

    @Override
    public List<Activity> findByCategory(Category category) {
        TypedQuery<Activity> query;
        query = this.em.createQuery("SELECT a FROM Activity a "
                + "WHERE a.category = :cat", Activity.class);
        query.setParameter("cat", category);

        return query.getResultList();
    }

    @Override
    public List<Activity> findByCategories(Collection<Category> categories) {
        return this.em.createQuery("SELECT a FROM Activity a where a.category IN :categories", Activity.class)
                .setParameter("categories", categories)
                .getResultList();
    }
}
