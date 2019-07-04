package be.artisjaap.properties.model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public enum PropertyType {
    BOOLEAN, STRING, INTEGER, DOUBLE, DATE, DATE_TIME;

    public static PropertyType forClass(Class<?> clazz){
        if(Boolean.class == clazz){
            return BOOLEAN;
        }
        if(Double.class == clazz || Float.class == clazz){
            return DOUBLE;
        }
        if(Long.class == clazz || Integer.class == clazz || Short.class == clazz){
            return INTEGER;
        }
        if(LocalDate.class == clazz){
            return DATE;
        }
        if(LocalDateTime.class == clazz){
            return DATE_TIME;
        }
        return STRING;
    }
}
