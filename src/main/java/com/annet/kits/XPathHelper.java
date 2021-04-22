package com.annet.kits;


import com.annet.kits.converter.*;
import com.annet.kits.filter.XPathFilter;
import com.annet.kits.parser.XPathParser;
import com.annet.kits.utils.Objects;
import com.annet.kits.utils.Strings;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author yh
 * @since 2021/4/22
 */
public class XPathHelper {

    /**
     * 日期格式
     */
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 时间格式
     */
    private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    /**
     * 日期时间格式
     */
    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * XPath 表达式过滤器链
     */
    private List<XPathFilter> filterChain;

    private final XPathParser parser;

    public XPathHelper(XPathParser parser) {
        this.parser = parser;
    }

    public static XPathHelper create(XPathParser parser) {
        return new XPathHelper(parser);
    }

    /**
     * 添加 XPath 表达式过滤器
     *
     * @param filter
     * @return
     */
    public XPathHelper addFilter(XPathFilter filter) {
        if (null == filterChain) {
            filterChain = new LinkedList<>();
        }
        filterChain.add(filter);
        return this;
    }

    /**
     * 执行过滤器链
     *
     * @param xpath
     * @return
     */
    private String doFilterChain(String xpath) {
        if (null != filterChain && !filterChain.isEmpty()) {
            String tmpXpath = xpath;
            for (XPathFilter filter : filterChain) {
                tmpXpath = filter.filter(tmpXpath);
            }
            return tmpXpath;
        }
        return xpath;
    }

    /**
     * 根据字段 XPath 表达式获取 xml 内对应值赋予对象
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> T parse(T t) {
        Objects.requireNonNull(t, "t不能为空");

        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        // 获取类上 XPath value值
        XPath classXpath = clazz.getAnnotation(XPath.class);
        String xpathPrefix = null != classXpath ? Strings.defaultIfNull(classXpath.value(), "") : "";

        for (Field field : fields) {
            // 获取字段上的 XPath value值
            XPath xpath = field.getAnnotation(XPath.class);
            if (null == xpath || Strings.isEmpty(xpath.value())) {
                continue;
            }
            String xpathValue = doFilterChain(xpathPrefix + xpath.value());
            String text = parser.getText(xpathValue);
            // null时不赋值
            if (null != text) {
                field.setAccessible(true);
                try {
                    field.set(t, converter(field, text, xpath));
                } catch (Exception e) {
                    System.err.println("字段" + field.getName() + "赋值(\"" + text + "\")异常 e: "
                            + e.getClass().getName() + " -> " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return t;
    }

    /**
     * 值转换
     *
     * @param field
     * @param text
     * @param xpath
     * @return
     */
    private static Object converter(Field field, String text, XPath xpath) throws InstantiationException, IllegalAccessException {
        Class<? extends TextConverter<?>> converter = xpath.converter();
        if (converter == DefaultTextConverter.class) {

            JavaType javaType = JavaType.get(field.getType()).orElse(JavaType.VOID);
            if (javaType == JavaType.STRING) {
                return text;
            }

            TextConverter<?> textConverter = getTextConverter(javaType, xpath.datetimeFormat());
            return textConverter.apply(text);
        } else {
            return converter.newInstance().apply(text);
        }
    }

    /**
     * 获取支持的转换器
     *
     * @param javaType
     * @param format
     * @return
     */
    private static TextConverter<?> getTextConverter(JavaType javaType, String format) {
        switch (javaType) {
            case BOOLEAN:
                return new BooleanTextConverter();
            case BYTE:
                return new ByteTextConverter();
            case SHORT:
                return new ShortTextConverter();
            case INT:
                return new IntegerTextConverter();
            case LONG:
                return new LongTextConverter();
            case FLOAT:
                return new FloatTextConverter();
            case DOUBLE:
                return new DoubleTextConverter();
            case CHAR:
                return new CharacterTextConverter();
            case BIG_DECIMAL:
                return new BigDecimalTextConverter();
            case BIG_INTEGER:
                return new BigIntegerTextConverter();
            case DATE:
                return new DateTextConverter(format);
            case LOCAL_DATE:
                return new LocalDateTextConverter(format);
            case LOCAL_TIME:
                return new LocalTimeTextConverter(format);
            case LOCAL_DATE_TIME:
                return new LocalDateTimeTextConverter(format);
            case ZONED_DATE_TIME:
                return new ZonedDateTimeTextConverter(format);
            case OFFSET_DATE_TIME:
                return new OffsetDateTimeTextConverter(format);
            default:
                return new DefaultTextConverter();
        }
    }

    /**
     * 初始化字段的类型
     */
    private enum JavaType {
        /**
         *
         */
        VOID(void.class, Void.class),
        BOOLEAN(boolean.class, Boolean.class),
        BYTE(byte.class, Byte.class),
        SHORT(short.class, Short.class),
        INT(int.class, Integer.class),
        LONG(long.class, Long.class),
        FLOAT(float.class, Float.class),
        DOUBLE(double.class, Double.class),
        CHAR(char.class, Character.class),
        STRING(String.class),
        BIG_DECIMAL(BigDecimal.class),
        BIG_INTEGER(BigInteger.class),
        DATE(java.util.Date.class),
        LOCAL_DATE(LocalDate.class),
        LOCAL_TIME(LocalTime.class),
        LOCAL_DATE_TIME(LocalDateTime.class),
        ZONED_DATE_TIME(ZonedDateTime.class),
        OFFSET_DATE_TIME(OffsetDateTime.class),
        ;

        private final Class<?>[] classes;

        JavaType(Class<?> cls) {
            this.classes = new Class[]{cls};
        }

        JavaType(Class<?> cls0, Class<?> cls1) {
            this.classes = new Class[]{cls0, cls1};
        }

        public static Optional<JavaType> get(Class<?> clazz) {
            return Arrays.stream(JavaType.values())
                    .filter(type -> type.has(clazz))
                    .findAny();
        }

        private <T> boolean has(Class<T> clazz) {
            for (Class<?> cls : classes) {
                if (cls == clazz) {
                    return true;
                }
            }
            return false;
        }

    }

}
