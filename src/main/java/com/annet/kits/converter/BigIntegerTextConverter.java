package com.annet.kits.converter;

import com.annet.kits.utils.Strings;

import java.math.BigInteger;

/**
 * @author yh
 * @since 2021/4/22
 */
public class BigIntegerTextConverter implements TextConverter<BigInteger> {
    @Override
    public BigInteger apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        return new BigInteger(text);
    }
}
