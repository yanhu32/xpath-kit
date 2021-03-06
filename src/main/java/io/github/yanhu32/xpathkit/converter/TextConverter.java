package io.github.yanhu32.xpathkit.converter;

/**
 * 将 String 转换为指定对象
 *
 * @author yh
 * @since 2021/4/22
 */
public interface TextConverter<T> {

    T apply(String text);

}
