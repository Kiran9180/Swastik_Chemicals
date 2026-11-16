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

    private static final String PEM_PATH = "/etc/secrets/ca.pem";

    public static Connection getConnection() throws SQLException {
        try {
			
			
            System.out.println("DBUtil.getConnection() starting...");
            System.out.println("DB_URL=" + URL);
            System.out.println("DB_USERNAME=" + USER);
            System.out.println("DB_PASSWORD=" + (PASS != null ? "******" : null));

            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);

        //    File pemFile = new File(PEM_PATH);
            System.out.println("PEM exists: " + pemFile.exists());

        //    if (!pemFile.exists()) {
        //        throw new SQLException("PEM file missing at " + PEM_PATH);
        //    }
		//
        //    // Aiven SSL using PEM
        //    props.setProperty("sslMode", "VERIFY_CA");
        //    props.setProperty("trustCertificateKeyStoreUrl", "file:" + PEM_PATH);
        //    props.setProperty("trustCertificateKeyStoreType", "PEM");
		//
            return DriverManager.getConnection(URL, props);

        } catch (Exception e) {
            System.out.println("Exception in DBUtil.getConnection(): " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Failed to connect to Aiven MySQL", e);
        }
    }
}
