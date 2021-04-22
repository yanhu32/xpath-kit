package io.github.yanhu32.xpathkit.converter;

import io.github.yanhu32.xpathkit.utils.Strings;

/**
 * @author yh
 * @since 2021/4/22
 */
public class LongTextConverter implements TextConverter<Long> {
    @Override
    public Long apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        return Long.valueOf(text);
    }
}
