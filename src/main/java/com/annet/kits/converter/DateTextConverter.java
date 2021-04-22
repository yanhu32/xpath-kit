package com.annet.kits.converter;

import com.annet.kits.utils.Strings;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author yh
 * @since 2021/4/22
 */
public class DateTextConverter extends AbstractDateTimeTextConverter<Date> {

    public DateTextConverter() {
        super(null);
    }

    public DateTextConverter(String format) {
        super(format);
    }

    @Override
    public Date apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        String format = Strings.defaultIfEmpty(getFormat(), DEFAULT_DATE_TIME_FORMAT);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return Date.from(LocalDateTime.parse(text, formatter).atZone(ZoneId.systemDefault()).toInstant());
    }
}
