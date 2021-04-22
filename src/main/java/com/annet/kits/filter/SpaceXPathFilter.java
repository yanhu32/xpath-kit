package com.annet.kits.filter;


import com.annet.kits.utils.Strings;

/**
 * 去除空格
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
