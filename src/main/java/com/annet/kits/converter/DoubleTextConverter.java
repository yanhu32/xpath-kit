package com.annet.kits.converter;

import com.annet.kits.utils.Strings;

/**
 * @author yh
 * @since 2021/4/22
 */
public class DoubleTextConverter implements TextConverter<Double> {
    @Override
    public Double apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        return Double.valueOf(text);
    }
}
