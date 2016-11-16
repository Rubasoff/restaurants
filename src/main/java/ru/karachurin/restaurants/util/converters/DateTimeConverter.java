package ru.karachurin.restaurants.util.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Денис on 16.11.2016.
 */
@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        Instant instant = attribute.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        Instant instant = dbData.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
