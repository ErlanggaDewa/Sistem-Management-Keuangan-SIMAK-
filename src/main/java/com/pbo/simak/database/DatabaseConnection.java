package com.pbo.simak.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    protected static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    protected static final String DB_URL = "jdbc:mysql://localhost/simak";
    protected static final String USERNAME = "root";
    protected static final String PASS = "root";

    protected static final DatabaseConnection connectNow = new DatabaseConnection();
    protected static final Connection connectDB = connectNow.getConnection();
    protected static PreparedStatement pst;

    protected Connection databaseLink;

    protected Connection getConnection() {
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
