package com.annet.kits.converter;

import com.annet.kits.utils.Strings;

/**
 * @author yh
 * @since 2021/4/22
 */
public class ShortTextConverter implements TextConverter<Short> {
    @Override
    public Short apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        return Short.valueOf(text);
    }
}
