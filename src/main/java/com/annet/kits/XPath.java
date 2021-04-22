package com.annet.kits;

import com.annet.kits.converter.DefaultTextConverter;
import com.annet.kits.converter.TextConverter;

import java.lang.annotation.*;

/**
 * XPath注解类
 *
 * @author yh
 * @since 2021/4/22
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XPath {

    /**
     * XPath表达式
     *
     * @return XPath表达式
     */
    String value();

    /**
     * 值转换器
     *
     * @return 转换器
     */
    Class<? extends TextConverter<?>> converter() default DefaultTextConverter.class;

    /**
     * 日期时间格式化
     *
     * @return
     */
    String datetimeFormat() default "";

}
