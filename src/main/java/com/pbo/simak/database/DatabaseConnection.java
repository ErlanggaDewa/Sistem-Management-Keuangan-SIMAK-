package com.pbo.simak.database;

import java.sql.*;

public class DatabaseConnection {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/simak";
    private static final String USERNAME = "root";
    private static final String PASS = "root";

    public Connection databaseLink;

    public Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            databaseLink = DriverManager.getConnection(DB_URL, USERNAME, PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return databaseLink;
    }

}
