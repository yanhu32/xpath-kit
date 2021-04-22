package io.github.yanhu32.xpathkit.filter;


import io.github.yanhu32.xpathkit.utils.Strings;

/**
 * 去除 XPath 表达式空格
 *
 * @author yh
 * @since 2021/4/22
 */
public class SpaceXPathFilter implements XPathFilter {
    @Override
    public String filter(String xpath) {
        return Strings.remove(xpath, " ");
    }
}
