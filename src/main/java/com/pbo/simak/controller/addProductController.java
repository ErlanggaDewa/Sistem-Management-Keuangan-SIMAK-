package com.pbo.simak.controller;

import com.pbo.simak.model.ProductModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.HashMap;

public class addProductController {
    private final HashMap<String, String> storeData = new HashMap<>();
    @FXML
    private Label addProductMsg;
    @FXML
    private TextField productCategory;
    @FXML
    private TextField productName;
    @FXML
    private TextField productPrice;

    public void submitProductAction(ActionEvent actionEvent) throws SQLException {
        storeData.put("productName", productName.getText());
        storeData.put("productPrice", productPrice.getText());
        storeData.put("productCategory", productCategory.getText());

        int rowAffected = ProductModel.store(storeData);

        if (rowAffected == 1) {
            addProductMsg.setText("Berhasil Menambah Data");
        } else {
            addProductMsg.setText("Gagal Menambah Data");
        }

    }

}
