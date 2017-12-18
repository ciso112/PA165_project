package com.muni.fi.pa165project.dto;

import java.time.LocalDateTime;

/**
 * @author Radim Podola
 *
 * DTO object used for creating Record
 */
public class RecordCreateDTO {

    private long activityId;

    private long userId;

    private String atTime;

    private int distance;

    private int duration;

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAtTime() {
        return atTime;
    }

    public void setAtTime(String atTime) {
        this.atTime = atTime;
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
}
