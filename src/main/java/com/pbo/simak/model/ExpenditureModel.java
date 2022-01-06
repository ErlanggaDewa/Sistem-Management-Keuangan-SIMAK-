package com.pbo.simak.model;

import com.pbo.simak.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ExpenditureModel extends DatabaseConnection {
    int expenditureId;
    String expenditureName;
    String expenditurePrice;
    String description;
    String transactionTime;

    public ExpenditureModel(int expenditureId, String expenditureName, String expenditurePrice, String description, String transactionTime) {
        this.expenditureId = expenditureId;
        this.expenditureName = expenditureName;
        this.expenditurePrice = expenditurePrice;
        this.description = description;
        this.transactionTime = transactionTime;
    }

    public static ObservableList<ExpenditureModel> getAllExpenditures() throws SQLException {
        ObservableList<ExpenditureModel> selectedList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM expenditure";

        pst = connectDB.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int expenditureId = rs.getInt("expenditure_id");
            String expenditureName = rs.getString("expenditure_name");
            String expenditurePrice = rs.getString("expenditure_price");
            String description = rs.getString("description");
            String transactionTime = rs.getString("transaction_time");

            selectedList.add(new ExpenditureModel(expenditureId, expenditureName, expenditurePrice,
                    description, transactionTime));
        }
        return selectedList;
    }

    public static int store(HashMap<String, String> storeData) throws SQLException {
        String sql = "INSERT INTO expenditure VALUES (0, '%s', '%s', '%s', '%s')";

        sql = String.format(sql, storeData.get("expenditureName"), storeData.get("expenditurePrice"),
                storeData.get("transactionTime"), storeData.get("description"));

        pst = connectDB.prepareStatement(sql);
        return pst.executeUpdate(sql);

    }

    public static int destroy(String id) throws SQLException {
        String sql = "DELETE FROM expenditure WHERE expenditure_id = '%s'";

        sql = String.format(sql, id);
        pst = connectDB.prepareStatement(sql);
        return pst.executeUpdate(sql);
    }

    private static int selectedList(HashMap<String, String> storeData) throws SQLException {
        return selectedList(storeData);
    }

    public static int update(HashMap<String, String> storeData) throws SQLException {
        String sql = "UPDATE expenditure SET expenditure_name = '%s', expenditure_price = '%s', description = '%s', " +
                "transaction_time = '%s' WHERE expenditure_id = '%s'";

        sql = String.format(sql, storeData.get("expenditureName"), storeData.get("expenditurePrice"),
                storeData.get("description"), storeData.get("transactionTime"), storeData.get("expenditureId"));
        pst = connectDB.prepareStatement(sql);

        return pst.executeUpdate(sql);
    }

    public static int getTotalExpenditure() throws SQLException {
        String sql = "SELECT COUNT(*) FROM expenditure";
        pst = connectDB.prepareStatement(sql);
        ResultSet rs = pst.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        return count;
    }

    public static double getPriceExpenditure() throws SQLException {
        String sql = "SELECT SUM(expenditure_price) FROM expenditure";
        pst = connectDB.prepareStatement(sql);
        ResultSet rs = pst.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        return count;
    }

    public int getExpenditureId() {
        return expenditureId;
    }

    public void setExpenditureId(int expenditureId) {
        this.expenditureId = expenditureId;
    }

    public String getExpenditureName() {
        return expenditureName;
    }

    public void setExpenditureName(String expenditureName) {
        this.expenditureName = expenditureName;
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

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }


}
