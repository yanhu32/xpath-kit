package com.annet.kits.converter;

/**
 * 将 xml 值转换为指定对象
 *
 * @author yh
 * @since 2021/4/22
 */
public interface TextConverter<T> {

    T apply(String text);

}
