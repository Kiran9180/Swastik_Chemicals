package com.example.Swastik.Chemicals.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Path;

public class DBUtil {

    private static final String URL =
        "jdbc:mysql://mysql-1f4745a1-kiranshinde4747-1d66.g.aivencloud.com:15881/defaultdb";

    private static final String USER = System.getenv("DB_USER");
    private static final String PASS = System.getenv("DB_PASS");
    private static final String CA_CERT = System.getenv("AIVEN_CA_CERT");

    public static Connection getConnection() throws SQLException {
        try {
            System.out.println("urlallinsidetry");
            // Write CA cert to a temp file
            Path caPath = Files.createTempFile("aiven-ca", ".pem");
            Files.writeString(caPath, CA_CERT);
System.out.println("urlall"+caPath+CA_CERT);
            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);
            props.setProperty("sslMode", "VERIFY_CA");
            props.setProperty("useSSL", "true");
            props.setProperty("requireSSL", "true");
            props.setProperty("verifyServerCertificate", "true");
            props.setProperty("trustCertificateKeyStoreUrl", "file:" + caPath.toString());
System.out.println("urlall"+URL+ ".."+props.toString());
            return DriverManager.getConnection(URL, props);

        } catch (Exception e) {
            throw new SQLException("Failed to connect to Aiven MySQL", e);
        }
    }
}
