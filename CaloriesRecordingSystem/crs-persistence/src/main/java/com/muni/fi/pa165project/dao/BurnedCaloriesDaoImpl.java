package com.muni.fi.pa165project.dao;

import java.util.List;

import com.muni.fi.pa165project.entity.BurnedCalories;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
*
* @author Lukáš Císar
*/
@Repository
public class BurnedCaloriesDaoImpl implements BurnedCaloriesDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
    public void create(BurnedCalories burnedCalories) {
        this.em.persist(burnedCalories);
    }

    @Override
    public void update(BurnedCalories burnedCalories) {
        this.em.merge(burnedCalories);
    }

    @Override
    public void delete(BurnedCalories burnedCalories) {
        boolean isManaged = this.em.contains(burnedCalories);
        
        if (isManaged) {
            this.em.remove(burnedCalories);
        } else {
        	BurnedCalories actual = this.findById(burnedCalories.getId());
            
            if (actual != null) {
                this.em.remove(actual);
            }	
        }
    }
    
    @Override
    public BurnedCalories findById(long id) {
        return this.em.find(BurnedCalories.class, id);
    }

    @Override
    public List<BurnedCalories> findAll() {
        return this.em.createQuery("SELECT bc from BurnedCalories bc",
        		BurnedCalories.class).getResultList();
    }

}
