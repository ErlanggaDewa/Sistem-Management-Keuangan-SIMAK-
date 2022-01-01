package com.pbo.simak.model;

import com.pbo.simak.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ProductModel extends DatabaseConnection {

    protected int productId;
    protected String productName, productPrice, productCategory;


    public ProductModel() {
    }

    public ProductModel(int id, String productName, String productPrice, String productCategory) {
        this.productId = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    public static ObservableList<String> getAllProductNames() throws SQLException {
        String sql = "SELECT * FROM product";

        pst = connectDB.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        ObservableList<String> productNames = FXCollections.observableArrayList();

        while (rs.next()) {
            productNames.add(rs.getString("product_name"));
        }
        return productNames;
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

    public static int update(HashMap<String, String> product) throws SQLException {
        String sql = "UPDATE product SET product_name = '%s', product_price = '%s', product_category = '%s' WHERE product_id = '%s' ";

        sql = String.format(sql, product.get("productName"), product.get("productPrice"),
                product.get("productCategory"), product.get("productId"));
        pst = connectDB.prepareStatement(sql);
        return pst.executeUpdate(sql);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
