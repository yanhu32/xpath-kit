package io.github.yanhu32.xpathkit.converter;

import io.github.yanhu32.xpathkit.utils.Strings;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yh
 * @since 2021/4/22
 */
public class LocalTimeTextConverter extends AbstractDateTimeTextConverter<LocalTime> {

    public LocalTimeTextConverter() {
        super(null);
    }

    public LocalTimeTextConverter(String format) {
        super(format);
    }

    @Override
    public LocalTime apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        String format = Strings.defaultIfEmpty(getFormat(), DEFAULT_TIME_FORMAT);
        return LocalTime.parse(text, DateTimeFormatter.ofPattern(format));
    }
}
