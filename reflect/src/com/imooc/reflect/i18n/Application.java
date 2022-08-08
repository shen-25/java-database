package com.imooc.reflect.i18n;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

public class Application {
    public static void say() throws Exception {
        Properties property = new Properties();
        String configPath = Application.class.getResource("/config.properties").getPath();
        configPath = URLDecoder.decode(configPath, "UTF-8");
        property.load(new FileInputStream(configPath));
        String language = property.getProperty("language");
        I18N i18N = (I18N) Class.forName(language).newInstance();
        System.out.println(i18N.say());
    }

    public static void main(String[] args) throws Exception {
        say();
    }
}
