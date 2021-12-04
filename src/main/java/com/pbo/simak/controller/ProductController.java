package com.pbo.simak.controller;

import com.pbo.simak.model.ProductModel;
import com.pbo.simak.model.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    ObservableList<ProductModel> productList = FXCollections.observableArrayList();

    @FXML
    private TableView<UserModel> producTable;
    @FXML
    private TableColumn<UserModel, String> colNumber;
    @FXML
    private TableColumn<UserModel, String> colProductName;
    @FXML
    private TableColumn<UserModel, String> colPrice;
    @FXML
    private TableColumn<UserModel, String> colCategory;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadProduk();
    }

    private void loadProduk() {
//        kolomNamaProduk.setCellValueFactory();
        colProductName.setCellValueFactory();
    }

}
