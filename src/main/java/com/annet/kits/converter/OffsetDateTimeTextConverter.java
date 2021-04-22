package com.annet.kits.converter;

import com.annet.kits.utils.Strings;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yh
 * @since 2021/4/22
 */
public class OffsetDateTimeTextConverter extends AbstractDateTimeTextConverter<OffsetDateTime> {

    public OffsetDateTimeTextConverter() {
        super(null);
    }

    public OffsetDateTimeTextConverter(String format) {
        super(format);
    }

    @Override
    public OffsetDateTime apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        String format = Strings.defaultIfEmpty(getFormat(), DEFAULT_DATE_TIME_FORMAT);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return ZonedDateTime.of(LocalDateTime.parse(text, formatter), ZoneId.systemDefault()).toOffsetDateTime();
    }
}
