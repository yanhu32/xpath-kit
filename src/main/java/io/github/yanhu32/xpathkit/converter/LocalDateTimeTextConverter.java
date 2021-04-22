package io.github.yanhu32.xpathkit.converter;

import io.github.yanhu32.xpathkit.utils.Strings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yh
 * @since 2021/4/22
 */
public class LocalDateTimeTextConverter extends AbstractDateTimeTextConverter<LocalDateTime> {

    public LocalDateTimeTextConverter() {
        super(null);
    }

    public LocalDateTimeTextConverter(String format) {
        super(format);
    }

    @Override
    public LocalDateTime apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        String format = Strings.defaultIfEmpty(getFormat(), DEFAULT_DATE_TIME_FORMAT);
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(format));
    }
}
