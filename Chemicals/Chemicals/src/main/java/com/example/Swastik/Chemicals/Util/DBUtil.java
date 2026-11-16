package com.example.Swastik.Chemicals.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    private static final String URL =
        "jdbc:mysql://mysql-1f4745a1-kiranshinde4747-1d66.g.aivencloud.com:15881/defaultdb";

    private static final String USER = System.getenv("DB_USER");
    private static final String PASS = System.getenv("DB_PASS");

    // Render Secret Files mount here
    private static final String TRUSTSTORE_PATH = "/etc/secrets/truststore.p12";

    public static Connection getConnection() throws SQLException {
        try {

            System.out.println("inside try Truststore exists: " );
            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);
System.out.println("props: " +
              props.toString() );
            // SSL settings for Aiven
            props.setProperty("sslMode", "VERIFY_CA");
            props.setProperty("trustCertificateKeyStoreUrl", "file:" + TRUSTSTORE_PATH);
            props.setProperty("trustCertificateKeyStorePassword", "changeit"); // your p12 password
            props.setProperty("trustCertificateKeyStoreType", "PKCS12");

            // Debug
            System.out.println("Truststore exists: " +
                new java.io.File(TRUSTSTORE_PATH).exists());

            return DriverManager.getConnection(URL, props);

        } catch (Exception e) {
            throw new SQLException("Failed to connect to Aiven MySQL", e);
        }
    }
}
