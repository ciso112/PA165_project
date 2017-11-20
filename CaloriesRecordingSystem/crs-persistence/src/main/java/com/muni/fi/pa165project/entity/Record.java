package com.muni.fi.pa165project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Peter Krasnan
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "atTime"})})
public class Record implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Activity activity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime atTime;

    private double weight;

    private int burnedCalories;

    private int distance;

    private int duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAtTime() {
        return atTime;
    }

    public void setAtTime(LocalDateTime atTime) {
        this.atTime = atTime;
    }

    public int getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(int burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((atTime == null) ? 0 : atTime.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Record)) {
            return false;
        }

        final Record other = (Record) obj;

        if (user == null) {
            return false;
        } else {
            if (!user.equals(other.user)) {
                return false;
            }
        }

        if (atTime == null) {
            return false;
        } else {
            if (!atTime.equals(other.atTime)) {
                return false;
            }
        }
        return true;
    }
}
