package com.example.Swastik.Chemicals.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Read environment variables (Render)
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASS");

            // If environment variables are missing → use local DB
            if (url == null || user == null || pass == null) {
                url = "jdbc:mysql://localhost:3306/acid_order_management";
                user = "root";
                pass = "NewPassword";
            }

            connection = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
