package com.example.quiz_game.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {
    private static final Properties properties = new Properties();

    static {
        try {
            Environment.properties.load(new FileInputStream(String.format("%s/%s", System.getProperty("user.dir"), ".properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String DBNAME = properties.getProperty("DBNAME");
    public static final String DBUSER = properties.getProperty("DBUSER");
    public static final String DBPASSWORD = properties.getProperty("DBPASSWORD");
}
