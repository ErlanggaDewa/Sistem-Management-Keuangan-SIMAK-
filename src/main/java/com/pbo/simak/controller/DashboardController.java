package com.pbo.simak.controller;

import com.pbo.simak.utils.SceneUtils;
import javafx.event.ActionEvent;

import java.io.IOException;

public class DashboardController {

    public void viewDashboard(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("dashboard.fxml", actionEvent);
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
