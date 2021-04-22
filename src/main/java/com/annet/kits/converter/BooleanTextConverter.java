package com.annet.kits.converter;

import com.annet.kits.utils.Strings;

/**
 * @author yh
 * @since 2021/4/22
 */
public class BooleanTextConverter implements TextConverter<Boolean> {
    @Override
    public Boolean apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        return Boolean.valueOf(text);
    }
}
