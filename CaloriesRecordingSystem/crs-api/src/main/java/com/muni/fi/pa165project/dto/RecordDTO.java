package com.muni.fi.pa165project.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Radoslav Karlik
 *
 * DTO object used for list of Records
 */
public class RecordDTO {

    private Long Id;

    private long activityId;

    private String activityName;

    private String atTime;

    private int burnedCalories;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getAtTime() {
        return atTime;
    }

    public void setAtTime(String atTime) {
        this.atTime = atTime;
    }

    public int getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(int burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.atTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RecordDTO)) {
            return false;
        }
        final RecordDTO other = (RecordDTO) obj;
        if (!Objects.equals(this.atTime, other.atTime)) {
            return false;
        }
        return true;
    }

}
