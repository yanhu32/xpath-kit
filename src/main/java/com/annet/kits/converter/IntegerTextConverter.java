package com.annet.kits.converter;

import com.annet.kits.utils.Strings;

/**
 * @author yh
 * @since 2021/4/22
 */
public class IntegerTextConverter implements TextConverter<Integer> {
    @Override
    public Integer apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        return Integer.valueOf(text);
    }
}
