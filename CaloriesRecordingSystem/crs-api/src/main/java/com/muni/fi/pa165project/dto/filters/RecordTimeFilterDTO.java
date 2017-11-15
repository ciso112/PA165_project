package com.muni.fi.pa165project.dto.filters;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Radim Podola
 */
public class RecordTimeFilterDTO {

    private LocalDateTime from;

    private LocalDateTime to;
    
    private LocalDate date;

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
