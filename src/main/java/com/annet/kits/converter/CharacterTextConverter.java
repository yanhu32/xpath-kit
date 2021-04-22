package com.annet.kits.converter;


import com.annet.kits.utils.Strings;

/**
 * @author yh
 * @since 2021/4/22
 */
public class CharacterTextConverter implements TextConverter<Character> {
    @Override
    public Character apply(String text) {
        if (Strings.isEmpty(text)) {
            return null;
        }
        return text.charAt(0);
    }
}
