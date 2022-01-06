package com.pbo.simak.controller;

import com.pbo.simak.model.DashboardModel;
import com.pbo.simak.utils.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class DashboardController {

    @FXML
    private TableView<String> dashboardTable;

    @FXML
    private TableColumn<DashboardModel, String> colDescription;

    @FXML
    private TableColumn<DashboardModel, String> colExpenditureName;

    @FXML
    private TableColumn<DashboardModel, String> colExpenditurePrice;

    @FXML
    private TableColumn<DashboardModel, String> colProductName;

    @FXML
    private TableColumn<DashboardModel, String> colTransactionPrice;

    @FXML
    private TableColumn<DashboardModel, String> colTransactionTime;


    @FXML
    private Label labelTotalExpenditure;

    @FXML
    private Label labelTotalMoney;

    @FXML
    private Label labelTotalProduct;

    @FXML
    private Label labelTotalTransaction;


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
