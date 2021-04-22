package com.annet.kits.converter;

/**
 * 默认转换器
 *
 * @author yh
 * @since 2021/4/22
 */
public class DefaultTextConverter implements TextConverter<String> {
    @Override
    public String apply(String text) {
        return text;
    }
}
