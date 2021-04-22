package io.github.yanhu32.xpathkit.parser;

import io.github.yanhu32.xpathkit.utils.Strings;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Jaxp XPath解析
 *
 * @author yh
 * @since 2021/4/22
 */
public class JaxpXPathParser extends AbstractXPathParser {

    private final Document document;

    private final XPath xpath;

    public JaxpXPathParser(String xml) {
        super(xml);
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(getXml().getBytes(StandardCharsets.UTF_8))) {
            // 根据xml构建Document
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(inputStream);
            xpath = XPathFactory.newInstance().newXPath();
        } catch (Exception e) {
            System.err.println("读取xml异常 e: " + e.getClass().getName() + " -> " + e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String getText(String xpath) {
        if (Strings.isNotEmpty(xpath)) {
            try {
                return this.xpath.evaluate(xpath, document);
            } catch (XPathExpressionException e) {
                System.err.println("XPath表达式(" + xpath + ")解析异常 e: " + e.getClass().getName() + " -> " + e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    public static JaxpXPathParser of(String xml) {
        return new JaxpXPathParser(xml);
    }

    public Document getDocument() {
        return document;
    }

    public XPath getXpath() {
        return xpath;
    }
}
