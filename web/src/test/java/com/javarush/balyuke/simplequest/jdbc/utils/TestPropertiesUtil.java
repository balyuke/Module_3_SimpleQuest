package com.javarush.balyuke.simplequest.jdbc.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class  TestPropertiesUtil {

    private PropertiesUtil propertiesUtil;

    private static final String URL_KEY="db.url";
    private static final String USERNAME_KEY="db.username";
    private static final String PASSWORD_KEY="db.password";

    @Test
    public void checkGetPropertyValues() {
        String url = propertiesUtil.get(URL_KEY);
        String userName = propertiesUtil.get(USERNAME_KEY);
        String password = propertiesUtil.get(PASSWORD_KEY);

        assertEquals("jdbc:mysql://localhost:3306/db", url);
        assertEquals("user", userName);
        assertEquals("pass", password);
    }
}