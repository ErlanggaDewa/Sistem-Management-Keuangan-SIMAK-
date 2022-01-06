package com.pbo.simak.model;

import com.pbo.simak.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardModel extends DatabaseConnection {
    String transactionTime;
    String productName;
    String expenditureName;
    String transactionPrice;
    String expenditurePrice;
    String description;

    public DashboardModel(String transactionTime, String productName, String expenditureName, String transactionPrice, String expenditurePrice, String description) {
        this.transactionTime = transactionTime;
        this.productName = productName;
        this.expenditureName = expenditureName;
        this.transactionPrice = transactionPrice;
        this.expenditurePrice = expenditurePrice;
        this.description = description;
    }

    public ObservableList<DashboardModel> getAllDashboards() throws SQLException {
        ObservableList<DashboardModel> allData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM dashboard";

        pst = connectDB.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String transactionTime = rs.getString("transaction_time");
            String productName = rs.getString("product_name");
            String expenditureName = rs.getString("expenditure_name");
            String transactionPrice = rs.getString("transaction_price");
            String expenditurePrice = rs.getString("expenditure_price");
            String description = rs.getString("description");

            allData.add(new DashboardModel(transactionTime, productName, expenditureName, transactionPrice,
                    expenditurePrice, description));
        }
        return allData;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getExpenditureName() {
        return expenditureName;
    }

    public void setExpenditureName(String expenditureName) {
        this.expenditureName = expenditureName;
    }

    public String getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(String transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public String getExpenditurePrice() {
        return expenditurePrice;
    }

    public void setExpenditurePrice(String expenditurePrice) {
        this.expenditurePrice = expenditurePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
