package com.muni.fi.pa165project.service.config;

import org.dozer.CustomConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Peter Krasnan.
 */
public class LocalDateTimeConverter implements CustomConverter {
    @Override
    public Object convert(Object o, Object o1, Class<?> type, Class<?> type1) {
        if (type.equals(LocalDateTime.class) && type1.equals(String.class)) {
            return LocalDateTime.parse((String) o1, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        }

        if (type.equals(String.class) && type1.equals(LocalDateTime.class)) {
            return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format((LocalDateTime)o1);
        }
        
        return null;
    }
}
