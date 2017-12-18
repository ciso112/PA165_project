package com.muni.fi.pa165project.dto;

/**
 * @author Radim Podola
 *         <p>
 *         DTO object used for Record editation
 */
public class RecordUpdateDTO extends RecordGetUpdateDTO {

    private long userId;
    
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
