package com.pbo.simak.controller;

import com.pbo.simak.middleware.Validation;
import com.pbo.simak.model.ProductModel;
import com.pbo.simak.model.TransactionModel;
import com.pbo.simak.utils.SceneUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
    private final HashMap<String, String> storeData = new HashMap<>();
    private final HashMap<String, String> selectedTransaction = new HashMap<>();

    @FXML
    private TableColumn<TransactionModel, String> colDescription;
    @FXML
    private TableColumn<TransactionModel, String> colProductName;
    @FXML
    private TableColumn<TransactionModel, String> colTotalPrice;
    @FXML
    private TableColumn<TransactionModel, Integer> colTransactionId;
    @FXML
    private TableColumn<TransactionModel, String> colTime;
    @FXML
    private TableColumn<TransactionModel, String> colUnit;
    @FXML
    private DatePicker transactionTime;
    @FXML
    private TextArea transactionDescription;
    @FXML
    private ComboBox<String> productName;
    @FXML
    private TextField transactionCount;
    @FXML
    private Label transactionMsg;
    @FXML
    private TableView<TransactionModel> transactionTable;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadTransaction() throws SQLException {
        colTransactionId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("productCount"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        ObservableList<TransactionModel> transactionList = TransactionModel.getAllTransactions();

        transactionTable.setItems(transactionList);

        loadProductName();

    }

    public void loadProductName() throws SQLException {
        ObservableList<String> productNames = ProductModel.getAllProductNames();
        productName.setItems(productNames);
    }

    public void logoutAction(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("login.fxml", actionEvent);
    }

    public void getSelectedProduct(MouseEvent event) {
        int index;
        index = transactionTable.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }


        selectedTransaction.put("selectedId", colTransactionId.getCellData(index).toString());
        selectedTransaction.put("productName", colProductName.getCellData(index));
        selectedTransaction.put("transactionCount", colUnit.getCellData(index));
        selectedTransaction.put("totalPrice", colTotalPrice.getCellData(index));
        selectedTransaction.put("transactionTime", colTime.getCellData(index));
        selectedTransaction.put("description", colDescription.getCellData(index));

        productName.setValue(selectedTransaction.get("productName"));
        transactionCount.setText(selectedTransaction.get("transactionCount"));
        transactionTime.setValue(LocalDate.parse(selectedTransaction.get("transactionTime")));
        transactionDescription.setText(selectedTransaction.get("description"));

    }

    public void submitTransactionAction(ActionEvent actionEvent) throws SQLException {
        boolean isNumber = Validation.validateNumber(transactionCount.getText());

        if (!isNumber) {
            transactionMsg.setText("Jumlah Barang Harus Berupa Angka!");
            return;
        }

        if (!transactionCount.getText().isBlank()
                && !transactionDescription.getText().isBlank()
                && !productName.getSelectionModel().isEmpty()
                && !(transactionTime.getValue() == null)) {

            storeData.put("productName", productName.getSelectionModel().getSelectedItem());
            storeData.put("productCount", transactionCount.getText());
            storeData.put("description", transactionDescription.getText());
            storeData.put("transactionTime", transactionTime.getValue().toString());

            int rowAffected = TransactionModel.store(storeData);

            if (rowAffected == 1) {
                transactionMsg.setText("Berhasil Menambah Data");
                productName.valueProperty().set(null);
                transactionCount.clear();
                transactionDescription.clear();
                transactionTime.valueProperty().set(null);
                loadTransaction();
            } else {
                transactionMsg.setText("Gagal Menambah Data");
            }
        } else {
            transactionMsg.setText("Data Tidak Boleh Kosong");
        }
    }

    public void viewDashboard(ActionEvent actionEvent) {
    }


    public void viewProduct(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("product.fxml", actionEvent);
    }

    public void viewTransaction(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("transaction.fxml", actionEvent);
    }


    public void deleteTransactionAction(ActionEvent actionEvent) throws SQLException {
        int status = TransactionModel.destroy(selectedTransaction.get("selectedId"));
        if (status == 0) {
            transactionMsg.setText("Gagal Menghapus Data");
        } else if (status == 1) {
            transactionMsg.setText("Berhasil menghapus Data");
        }

        loadTransaction();
    }

    public void updateTransactionAction(ActionEvent actionEvent) throws SQLException {
        boolean isNumber = Validation.validateNumber(transactionCount.getText());

        if (!isNumber) {
            transactionMsg.setText("Jumlah Barang Harus Berupa Angka!");
            return;
        }

        storeData.put("transactionId", selectedTransaction.get("selectedId"));
        storeData.put("productName", productName.getSelectionModel().getSelectedItem());
        storeData.put("productCount", transactionCount.getText());
        storeData.put("description", transactionDescription.getText());
        storeData.put("transactionTime", transactionTime.getValue().toString());

        int status = TransactionModel.update(storeData);

        if (status == 0) {
            transactionMsg.setText("Gagal Mengedit Data");
        } else if (status == 1) {
            transactionMsg.setText("Berhasil Mengedit Data");
        }
        productName.valueProperty().set(null);
        transactionCount.clear();
        transactionDescription.clear();
        transactionTime.valueProperty().set(null);
        loadTransaction();
    }
}

