package com.example.Swastik.Chemicals.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.File;

public class DBUtil {

    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USERNAME");
    private static final String PASS = System.getenv("DB_PASSWORD");

    // Secret file paths
    private static final String PEM_PATH = "/etc/secrets/ca.pem";
    private static final String P12_PATH = "/etc/secrets/truststore.p12";

    public static Connection getConnection() throws SQLException {
        try {
            System.out.println("DBUtil.getConnection() starting...");
            System.out.println("DB_URL=" + URL);
            System.out.println("DB_USERNAME=" + USER);
            System.out.println("DB_PASSWORD=" + (PASS != null ? "******" : null));

            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);

            File pemFile = new File(PEM_PATH);
            File p12File = new File(P12_PATH);

            System.out.println("PEM exists: " + pemFile.exists());
            System.out.println("P12 exists: " + p12File.exists());

            // Prefer PEM (Aiven default)
            if (pemFile.exists()) {
                System.out.println("Using PEM certificate for SSL...");
                props.setProperty("sslMode", "VERIFY_CA");
                props.setProperty("trustCertificateKeyStoreUrl", "file:" + PEM_PATH);
                props.setProperty("trustCertificateKeyStoreType", "PEM");
            }
            // Fallback to PKCS12 only if PEM is missing
            else if (p12File.exists()) {
                System.out.println("Using PKCS12 truststore for SSL...");
                props.setProperty("sslMode", "VERIFY_CA");
                props.setProperty("trustCertificateKeyStoreUrl", "file:" + P12_PATH);
                props.setProperty("trustCertificateKeyStorePassword", "changeit");
                props.setProperty("trustCertificateKeyStoreType", "PKCS12");
            } else {
                System.out.println("No SSL certificate found. Connection will fail.");
            }

            return DriverManager.getConnection(URL, props);

        } catch (Exception e) {
            System.out.println("Exception in DBUtil.getConnection(): " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Failed to connect to Aiven MySQL", e);
        }
    }
}
