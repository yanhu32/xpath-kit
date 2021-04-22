package com.annet.kits.converter;

/**
 * 日期时间转换基类
 *
 * @author yh
 * @since 2021/4/22
 */
public abstract class AbstractDateTimeTextConverter<T> implements TextConverter<T> {

    /**
     * 日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    /**
     * 日期时间格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final String format;

    public AbstractDateTimeTextConverter(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
