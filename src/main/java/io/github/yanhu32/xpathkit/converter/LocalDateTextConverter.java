package io.github.yanhu32.xpathkit.converter;

import io.github.yanhu32.xpathkit.utils.Strings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author yh
 * @since 2021/4/22
 */
public class LocalDateTextConverter extends AbstractDateTimeTextConverter<LocalDate> {

    public LocalDateTextConverter() {
        super(null);
    }

    public LocalDateTextConverter(String format) {
        super(format);
    }

    @Override
    public LocalDate apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        String format = Strings.defaultIfEmpty(getFormat(), DEFAULT_DATE_FORMAT);
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(format));
    }
}
