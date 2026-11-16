package com.example.Swastik.Chemicals.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    // Use DB_URL from Render
    private static final String URL = System.getenv("DB_URL");

    // Use DB_USERNAME and DB_PASSWORD from Render
    private static final String USER = System.getenv("DB_USERNAME");
    private static final String PASS = System.getenv("DB_PASSWORD");

    // Render Secret Files mount here
    private static final String TRUSTSTORE_PATH = "/etc/secrets/truststore.p12";

    public static Connection getConnection() throws SQLException {
        try {
            System.out.println("DBUtil.getConnection() starting...");
            System.out.println("DB_URL=" + URL);
            System.out.println("DB_USERNAME=" + USER);
            System.out.println("DB_PASSWORD=" + (PASS != null ? "******" : null));
            System.out.flush();

            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);

            System.out.println("Props after setting user/pass: " + props);
            System.out.flush();

            // SSL settings for Aiven
            props.setProperty("sslMode", "VERIFY_CA");
            props.setProperty("trustCertificateKeyStoreUrl", "file:" + TRUSTSTORE_PATH);
            props.setProperty("trustCertificateKeyStorePassword", "changeit");
            props.setProperty("trustCertificateKeyStoreType", "PKCS12");

            System.out.println("Truststore exists: " +
                new java.io.File(TRUSTSTORE_PATH).exists());
            System.out.flush();

            return DriverManager.getConnection(URL, props);

        } catch (Exception e) {
            System.out.println("Exception in DBUtil.getConnection(): " + e.getMessage());
            e.printStackTrace();
            System.out.flush();
            throw new SQLException("Failed to connect to Aiven MySQL", e);
        }
    }
}


