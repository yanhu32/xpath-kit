package com.annet.kits.parser;

import com.annet.kits.utils.Strings;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * dom4j XPath解析
 *
 * @author yh
 * @since 2021/4/22
 */
public class Dom4jXPathParser extends AbstractXPathParser {

    private final Document document;

    public Dom4jXPathParser(String xml) {
        super(xml);
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(getXml().getBytes(StandardCharsets.UTF_8))) {
            // 根据xml构建Document
            document = new SAXReader().read(inputStream);
        } catch (Exception e) {
            System.err.println("读取xml异常 e: " + e.getClass().getName() + " -> " + e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String getText(String xpath) {
        if (Strings.isEmpty(xpath)) {
            Node node = document.selectSingleNode(xpath);
            if (null != node) {
                return node.getText();
            }
        }
        return null;
    }

    /**
     * 创建 Dom4jXPathParser
     *
     * @param xml
     * @return
     */
    public static Dom4jXPathParser of(String xml) {
        return new Dom4jXPathParser(xml);
    }

    public Document getDocument() {
        return document;
    }
}
