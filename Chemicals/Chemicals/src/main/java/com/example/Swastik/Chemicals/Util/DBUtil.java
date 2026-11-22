package com.example.Swastik.Chemicals.Util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    private static String URL;
    private static String USER;
    private static String PASS;

    static {

        try {

            Properties properties = new Properties();

            InputStream inputStream =
                    DBUtil.class
                    .getClassLoader()
                    .getResourceAsStream(
                            "application.properties");

            properties.load(inputStream);

            URL =
                    properties.getProperty(
                            "db.url");
System.out.println("url.."+URL);
            USER =
                    properties.getProperty(
                            "db.username");

            PASS =
                    properties.getProperty(
                            "db.password");

        }
        catch(Exception e) {

            e.printStackTrace();

        }
    }

    public static Connection getConnection()
            throws SQLException {

        try {

            Properties props =
                    new Properties();

            props.setProperty(
                    "user",
                    USER);

            props.setProperty(
                    "password",
                    PASS);

            return DriverManager.getConnection(
                    URL,
                    props);

        }
        catch(Exception e) {

            e.printStackTrace();

            throw new SQLException(
                    "Database Connection Failed",
                    e);

        }

    }

}