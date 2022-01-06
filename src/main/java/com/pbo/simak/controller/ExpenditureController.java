package com.pbo.simak.controller;

import com.pbo.simak.middleware.Validation;
import com.pbo.simak.model.ExpenditureModel;
import com.pbo.simak.utils.SceneUtils;
import javafx.collections.FXCollections;
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

public class ExpenditureController implements Initializable {
    private final HashMap<String, String> storeData = new HashMap<>();
    private final HashMap<String, String> selectedExpenditure = new HashMap<>();
    @FXML
    private TableColumn<ExpenditureModel, String> colDescription;

    @FXML
    private TableColumn<ExpenditureModel, Integer> colExpenditureId;

    @FXML
    private TableColumn<ExpenditureModel, String> colExpenditureName;

    @FXML
    private TableColumn<ExpenditureModel, String> colTime;

    @FXML
    private TableColumn<ExpenditureModel, String> colTotalPrice;

    @FXML
    private TextArea expenditureDescription;

    @FXML
    private Label expenditureMsg;

    @FXML
    private ComboBox<String> expenditureName;

    @FXML
    private TextField expenditurePrice;

    @FXML
    private TableView<ExpenditureModel> expenditureTable;

    @FXML
    private DatePicker transactionTime;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadExpenditure();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteExpenditureAction(ActionEvent actionEvent) throws SQLException {
        System.out.println(selectedExpenditure.get("selectedId"));

        int rowAffected = ExpenditureModel.destroy(selectedExpenditure.get("selectedId"));
        if (rowAffected == 0) {
            expenditureMsg.setText("Gagal Menghapus Data");
        } else if (rowAffected == 1) {
            expenditureMsg.setText("Berhasil menghapus Data");
        }

        clearScreen();
    }

    public void getSelectedExpenditure(MouseEvent event) {
        int index;
        index = expenditureTable.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }


        selectedExpenditure.put("selectedId", colExpenditureId.getCellData(index).toString());
        selectedExpenditure.put("expenditureName", colExpenditureName.getCellData(index));
        selectedExpenditure.put("totalPrice", colTotalPrice.getCellData(index));
        selectedExpenditure.put("expenditureTime", colTime.getCellData(index));
        selectedExpenditure.put("description", colDescription.getCellData(index));

        expenditureName.setValue(selectedExpenditure.get("expenditureName"));
        expenditurePrice.setText(selectedExpenditure.get("totalPrice"));
        transactionTime.setValue(LocalDate.parse(selectedExpenditure.get("expenditureTime")));
        expenditureDescription.setText(selectedExpenditure.get("description"));

    }

    public void submitExpenditureAction(ActionEvent actionEvent) throws SQLException {
        boolean isNumber = Validation.validateNumber(expenditurePrice.getText());

        if (!isNumber) {
            expenditureMsg.setText("Jumlah Pengeluaran Harus Berupa Angka!");
            return;
        }

        if (!expenditureDescription.getText().isBlank()
                && !expenditureName.getSelectionModel().isEmpty()
                && !(transactionTime.getValue() == null)) {

            storeData.put("expenditureName", expenditureName.getSelectionModel().getSelectedItem());
            storeData.put("description", expenditureDescription.getText());
            storeData.put("expenditurePrice", expenditurePrice.getText());
            storeData.put("transactionTime", transactionTime.getValue().toString());

            int rowAffected = ExpenditureModel.store(storeData);

            if (rowAffected == 1) {
                expenditureMsg.setText("Berhasil Menambah Data");
                clearScreen();
            } else {
                expenditureMsg.setText("Gagal Menambah Data");
            }
        } else {
            expenditureMsg.setText("Data Tidak Boleh Kosong");
        }
    }

    public void updateExpenditureAction(ActionEvent actionEvent) throws SQLException {
        boolean isNumber = Validation.validateNumber(expenditurePrice.getText());

        if (!isNumber) {
            expenditureMsg.setText("Jumlah Pengeluaran Harus Berupa Angka!");
            return;
        }
        storeData.put("expenditureId", selectedExpenditure.get("selectedId"));
        storeData.put("expenditureName", expenditureName.getSelectionModel().getSelectedItem());
        storeData.put("description", expenditureDescription.getText());
        storeData.put("expenditurePrice", expenditurePrice.getText());
        storeData.put("transactionTime", transactionTime.getValue().toString());

        int rowAffected = ExpenditureModel.update(storeData);

        if (rowAffected == 1) {
            expenditureMsg.setText("Berhasil Mengedit Data");
            clearScreen();
        } else {
            expenditureMsg.setText("Gagal Mengedit Data");
        }
    }


    private void clearScreen() throws SQLException {
        expenditureName.valueProperty().set(null);
        expenditurePrice.clear();
        expenditureDescription.clear();
        transactionTime.valueProperty().set(null);
        loadExpenditure();
    }

    private void loadExpenditure() throws SQLException {
        colExpenditureId.setCellValueFactory(new PropertyValueFactory<>("expenditureId"));
        colExpenditureName.setCellValueFactory(new PropertyValueFactory<>("expenditureName"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("expenditurePrice"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        ObservableList<ExpenditureModel> expenditureList = ExpenditureModel.getAllExpenditures();

        expenditureTable.setItems(expenditureList);

        ObservableList<String> list = FXCollections.observableArrayList("Operasional", "Investasi", "Pendanaan");

        expenditureName.setItems(list);
    }

    public void viewDashboard(ActionEvent actionEvent) {
    }

    public void viewProduct(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("product.fxml", actionEvent);
    }

    public void viewTransaction(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("transaction.fxml", actionEvent);
    }

    public void viewExpenditure(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("expenditure.fxml", actionEvent);

    }

    public void logoutAction(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("login.fxml", actionEvent);
    }
}
