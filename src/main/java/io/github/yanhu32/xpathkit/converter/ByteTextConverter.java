package io.github.yanhu32.xpathkit.converter;

import io.github.yanhu32.xpathkit.utils.Strings;

/**
 * @author yh
 * @since 2021/4/22
 */
public class ByteTextConverter implements TextConverter<Byte> {
    @Override
    public Byte apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        return Byte.valueOf(text);
    }
}
