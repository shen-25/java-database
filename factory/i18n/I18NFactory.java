package com.imooc.factory.i18n;

public class I18NFactory {
    public static I18N getI18NObject(String area) {
        if ("China".equals(area)) {
            return new Chinese();
        } else if ("Italian".equals(area)) {
            return new Italian();
        } else {
            return null;
        }
    }
}
