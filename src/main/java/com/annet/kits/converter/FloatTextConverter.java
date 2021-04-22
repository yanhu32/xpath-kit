package com.annet.kits.converter;

import com.annet.kits.utils.Strings;

/**
 * @author yh
 * @since 2021/4/22
 */
public class FloatTextConverter implements TextConverter<Float> {
    @Override
    public Float apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        return Float.valueOf(text);
    }
}
