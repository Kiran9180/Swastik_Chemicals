package com.example.Swastik.Chemicals.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class DBUtil {

    private static final String URL =
        "jdbc:mysql://mysql-1f4745a1-kiranshinde4747-1d66.g.aivencloud.com:15881/defaultdb";

    private static final String USER = System.getenv("DB_USER");
    private static final String PASS = System.getenv("DB_PASS");
    private static final String CA_CERT = System.getenv("AIVEN_CA_CERT");

    public static Connection getConnection() throws SQLException {
        try {
            // Write CA cert to a temp file
            Path caPath = Files.createTempFile("aiven-ca", ".pem");
            Files.writeString(caPath, CA_CERT, StandardCharsets.UTF_8);

            System.out.println("CA_CERT length = " + CA_CERT.length());
            System.out.println("CA file path = " + caPath);

            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);

            // Required SSL settings
            props.setProperty("sslMode", "VERIFY_CA");
            props.setProperty("trustCertificateKeyStoreUrl", "file:" + caPath.toString());
            props.setProperty("trustCertificateKeyStorePassword", "changeit"); // required dummy password

            return DriverManager.getConnection(URL, props);

        } catch (Exception e) {
            throw new SQLException("Failed to connect to Aiven MySQL", e);
        }
    }
}
