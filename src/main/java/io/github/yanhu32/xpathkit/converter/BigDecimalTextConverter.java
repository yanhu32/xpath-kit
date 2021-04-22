package io.github.yanhu32.xpathkit.converter;

import io.github.yanhu32.xpathkit.utils.Strings;

import java.math.BigDecimal;

/**
 * @author yh
 * @since 2021/4/22
 */
public class BigDecimalTextConverter implements TextConverter<BigDecimal> {
    @Override
    public BigDecimal apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        return new BigDecimal(text);
    }
}
