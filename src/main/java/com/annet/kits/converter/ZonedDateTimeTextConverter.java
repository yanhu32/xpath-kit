package com.annet.kits.converter;

import com.annet.kits.utils.Strings;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yh
 * @since 2021/4/22
 */
public class ZonedDateTimeTextConverter extends AbstractDateTimeTextConverter<ZonedDateTime> {

    public ZonedDateTimeTextConverter() {
        super(null);
    }

    public ZonedDateTimeTextConverter(String format) {
        super(format);
    }

    @Override
    public ZonedDateTime apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        String format = Strings.defaultIfEmpty(getFormat(), DEFAULT_DATE_TIME_FORMAT);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return ZonedDateTime.of(LocalDateTime.parse(text, formatter), ZoneId.systemDefault());
    }
}
