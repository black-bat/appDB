package org.app.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private static String dbDriver = "org.postgresql.Driver";
    private static String dbURl = "jdbc:postgresql://localhost:5432/people_test";
    private static String dbUsername = "postgres";
    private static String dbPassword = "root";

    public static Connection getConnection() {

        Connection connection = null;
        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURl, dbUsername, dbPassword);
            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Nok");
            }
        } catch ( SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
        return connection;
    }
}

