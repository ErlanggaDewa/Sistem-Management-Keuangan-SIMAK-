package com.pbo.simak.model;

import com.pbo.simak.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.Statement;

public class ProductModel {

    private static final DatabaseConnection connectNow = new DatabaseConnection();
    private static final Connection connectDB = connectNow.getConnection();
    private static Statement statement;

    public ProductModel(){

    }
}
