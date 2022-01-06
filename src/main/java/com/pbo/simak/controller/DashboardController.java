package com.pbo.simak.controller;

import com.pbo.simak.model.ExpenditureModel;
import com.pbo.simak.model.ProductModel;
import com.pbo.simak.model.TransactionModel;
import com.pbo.simak.utils.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Label labelTotalExpenditure;

    @FXML
    private Label labelTotalMoney;

    @FXML
    private Label labelTotalProduct;

    @FXML
    private Label labelTotalTransaction;


    private void loadDashboard() throws SQLException {
        labelTotalExpenditure.setText(ExpenditureModel.getTotalExpenditure() + " operasional");
        labelTotalProduct.setText(ProductModel.getTotalProduct() + " produk");
        labelTotalTransaction.setText(TransactionModel.getTotalTransaction() + " transaksi");

        double totalPriceTransaction = TransactionModel.getPriceTransaction();
        double totalPriceExpenditure = ExpenditureModel.getPriceExpenditure();
        labelTotalMoney.setText(String.valueOf(printCurrency(totalPriceTransaction - totalPriceExpenditure)));
    }

    private String printCurrency(double money) {
// Create a new Locale
        Locale indo = new Locale("id", "ID");
// Create a Currency instance for the Locale
        Currency idr = Currency.getInstance(indo);
// Create a formatter given the Locale
        NumberFormat idrFormat = NumberFormat.getCurrencyInstance(indo);

// Format the Number into a Currency String
        return idrFormat.format(money);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadDashboard();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void viewDashboard(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("dashboard.fxml", actionEvent);
    }

    @FXML
    private void viewProduct(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("product.fxml", actionEvent);
    }

    @FXML
    private void viewTransaction(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("transaction.fxml", actionEvent);
    }

    @FXML
    private void viewExpenditure(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("expenditure.fxml", actionEvent);

    }

    @FXML
    private void logoutAction(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("login.fxml", actionEvent);
    }
}
