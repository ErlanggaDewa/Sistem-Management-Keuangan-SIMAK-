package com.pbo.simak.model;

import com.pbo.simak.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ProductModel extends DatabaseConnection {

    private static final DatabaseConnection connectNow = new DatabaseConnection();
    private static final Connection connectDB = connectNow.getConnection();
    private static PreparedStatement pst;


    protected int id;
    protected String productName, productPrice, productCategory;


    public ProductModel() {
    }


    public ProductModel(int id, String productName, String productPrice, String productCategory) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    public static ObservableList<ProductModel> getAllProduct() throws SQLException {
        ObservableList<ProductModel> selectedList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM product";

        pst = connectDB.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int productId = rs.getInt("product_id");
            String productName = rs.getString("product_name");
            String productPrice = rs.getString("product_price");
            String productCategory = rs.getString("product_category");

            selectedList.add(new ProductModel(productId, productName, productPrice, productCategory));
        }

        return selectedList;
    }

    public static int store(HashMap<String, String> storeData) throws SQLException {
        String sql = "INSERT INTO product VALUES (0, '%s', '%s', '%s')";

        sql = String.format(sql,
                storeData.get("productName"),
                storeData.get("productPrice"),
                storeData.get("productCategory"));

        pst = connectDB.prepareStatement(sql);
        return pst.executeUpdate(sql);
    }

    public static int destroy(String id) throws SQLException {
        String sql = "DELETE FROM product WHERE product_id = '%s'";

        sql = String.format(sql, id);
        pst = connectDB.prepareStatement(sql);
        return pst.executeUpdate(sql);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
