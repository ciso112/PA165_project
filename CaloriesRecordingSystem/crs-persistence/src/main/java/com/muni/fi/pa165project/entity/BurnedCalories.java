package com.muni.fi.pa165project.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.Min;

/**
 * This class present entity which holds information
 * about an amount of a burned calories per hour
 * while doing a certain activity.
 *
 * @author Radim Podola
 */
@Entity
@Immutable
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"upperWeightBoundary", "activity_id", "amount"})})
public class BurnedCalories implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Min(1)
    private int upperWeightBoundary;

    @Min(1)
    private int amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getUpperWeightBoundary() {
        return upperWeightBoundary;
    }

    public void setUpperWeightBoundary(int weight) {
        this.upperWeightBoundary = weight;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        
        if (id != null) {
            return prime * result + id.hashCode();
        }
        
        result = prime * result + upperWeightBoundary;
        result = prime * result + ((activity == null) ? 0 : activity.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BurnedCalories))
            return false;

        final BurnedCalories other = (BurnedCalories) obj;

        if (id != null && other.id != null) {
            return id.equals(other.getId());
        }
        
        if (activity == null) {
            return false;
        } else if (!activity.equals(other.getActivity()))
            return false;

        if (upperWeightBoundary != other.getUpperWeightBoundary())
            return false;

        return true;
    }
}
