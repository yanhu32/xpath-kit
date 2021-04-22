package com.annet.kits.parser;


import com.annet.kits.utils.Strings;

/**
 * XPath解析基类
 *
 * @author yh
 * @since 2021/4/22
 */
public abstract class AbstractXPathParser implements XPathParser {

    private final String xml;

    public AbstractXPathParser(String xml) {
        if (Strings.isEmpty(xml)) {
            throw new IllegalArgumentException("xml不能为空");
        }
        this.xml = xml;
    }

    public String getXml() {
        return xml;
    }

    private interface XPathFilter {

        String filter(String xpath);

    }

}
