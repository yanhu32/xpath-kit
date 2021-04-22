package com.annet.kits.converter;

import com.annet.kits.utils.Strings;

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
