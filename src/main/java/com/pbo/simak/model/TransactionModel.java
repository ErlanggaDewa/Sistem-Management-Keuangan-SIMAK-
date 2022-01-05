package com.pbo.simak.model;

import com.pbo.simak.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionModel extends DatabaseConnection {
    int transactionId;
    String productName;
    String productCount;
    String totalPrice;
    String description;
    String transactionTime;

    public TransactionModel(int transactionId, String productName, String productCount, String totalPrice, String description, String transactionTime) {
        this.transactionId = transactionId;
        this.productName = productName;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.description = description;
        this.transactionTime = transactionTime;
    }

    public TransactionModel() {
    }

    public static ObservableList<TransactionModel> getAllTransactions() throws SQLException {
        ObservableList<TransactionModel> selectedList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM transaction_product";

        pst = connectDB.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int transactionId = rs.getInt("transaction_id");
            String productName = rs.getString("product_name");
            String productCount = rs.getString("product_count");
            String totalPrice = rs.getString("total_price");
            String description = rs.getString("description");
            String transactionTime = rs.getString("transaction_time");

            selectedList.add(new TransactionModel(transactionId, productName, productCount, totalPrice, description, transactionTime));
        }
        return selectedList;
    }

    public static int store(HashMap<String, String> storeData) throws SQLException {
        int totalPrice = selectedList(storeData);

        String sql = "INSERT INTO transaction_product VALUES (0, '%s', '%s', '%s', '%s', '%s')";

        sql = String.format(sql, storeData.get("productName"), storeData.get("productCount"), totalPrice,
                storeData.get("description"), storeData.get("transactionTime"));
        pst = connectDB.prepareStatement(sql);
        return pst.executeUpdate(sql);
    }

    public static int destroy(String id) throws SQLException {
        String sql = "DELETE FROM transaction_product WHERE transaction_id = '%s'";

        sql = String.format(sql, id);
        pst = connectDB.prepareStatement(sql);
        return pst.executeUpdate(sql);
    }

    public static int update(HashMap<String, String> storeData) throws SQLException {
        int totalPrice = selectedList(storeData);

        String sql = "UPDATE transaction_product SET product_name = '%s', product_count = '%s', total_price = '%s'," +
                " description = '%s', transaction_time = '%s' WHERE transaction_id = '%s'";

        sql = String.format(sql, storeData.get("productName"), storeData.get("productCount"), totalPrice,
                storeData.get("description"), storeData.get("transactionTime"), storeData.get("transactionId"));
        pst = connectDB.prepareStatement(sql);
        return pst.executeUpdate(sql);
    }

    private static int selectedList(HashMap<String, String> storeData) throws SQLException {
        ObservableList<ProductModel> selectedList = FXCollections.observableArrayList(ProductModel.getAllProduct());

        AtomicInteger totalPrice = new AtomicInteger();
        selectedList.forEach(item -> {
            if (item.getProductName().equals(storeData.get("productName"))) {
                int hargaBarang = Integer.parseInt(item.getProductPrice());
                int unitBarang = Integer.parseInt(storeData.get("productCount"));
                totalPrice.set(hargaBarang * unitBarang);
            }
        });
        return totalPrice.intValue();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }


}

