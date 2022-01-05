package com.pbo.simak.controller;

import com.pbo.simak.model.ProductModel;
import com.pbo.simak.utils.SceneUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    private final HashMap<String, String> storeData = new HashMap<>();
    private final HashMap<String, String> selectedProduct = new HashMap<>();
    protected ObservableList<ProductModel> productList = FXCollections.observableArrayList();

    @FXML
    private TableView<ProductModel> productTable;
    @FXML
    private TableColumn<ProductModel, Integer> colProductId;
    @FXML
    private TableColumn<ProductModel, String> colProductName;
    @FXML
    private TableColumn<ProductModel, String> colPrice;
    @FXML
    private TableColumn<ProductModel, String> colCategory;
    @FXML
    private Label ProductMsg;
    @FXML
    private TextField productCategory;
    @FXML
    private TextField productName;
    @FXML
    private TextField productId;
    @FXML
    private TextField productPrice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProduct() throws SQLException {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("productCategory"));

        productList = ProductModel.getAllProduct();

        productTable.setItems(productList);

    }

    public void deleteProductAcction(ActionEvent actionEvent) throws SQLException {

        int status = ProductModel.destroy(selectedProduct.get("productId"));
        if (status == 0) {
            ProductMsg.setText("Gagal Menghapus Data");
        } else if (status == 1) {
            ProductMsg.setText("Berhasil menghapus Data");
        }

        loadProduct();

    }

    public void getSelectedProduct(MouseEvent event) {
        int index = -1;
        index = productTable.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }


        selectedProduct.put("productId", colProductId.getCellData(index).toString());
        selectedProduct.put("productName", colProductName.getCellData(index));
        selectedProduct.put("productPrice", colPrice.getCellData(index));
        selectedProduct.put("productCategory", colCategory.getCellData(index));

        productId.setText(selectedProduct.get("productId"));
        productCategory.setText(selectedProduct.get("productCategory"));
        productName.setText(selectedProduct.get("productName"));
        productPrice.setText(selectedProduct.get("productPrice"));

    }


    public void submitProductAction(ActionEvent actionEvent) throws SQLException {
        storeData.put("productName", productName.getText());
        storeData.put("productPrice", productPrice.getText());
        storeData.put("productCategory", productCategory.getText());
        if (!productName.getText().isBlank() && !productPrice.getText().isBlank() && !productCategory.getText().isBlank()) {
            int rowAffected = ProductModel.store(storeData);

            if (rowAffected == 1) {
                ProductMsg.setText("Berhasil Menambah Data");
                productCategory.clear();
                productPrice.clear();
                productName.clear();
                loadProduct();
            } else {
                ProductMsg.setText("Gagal Menambah Data");
            }
        } else {
            ProductMsg.setText("Data Tidak Boleh Kosong");
        }

    }

    public void updateProductAcction(ActionEvent actionEvent) throws SQLException {
        selectedProduct.put("productId", productId.getText());
        selectedProduct.put("productName", productName.getText());
        selectedProduct.put("productPrice", productPrice.getText());
        selectedProduct.put("productCategory", productCategory.getText());
        int status = ProductModel.update(selectedProduct);
        if (status == 0) {
            ProductMsg.setText("Gagal Mengedit Data");
        } else if (status == 1) {
            ProductMsg.setText("Berhasil Mengedit Data");
        }
        loadProduct();
    }

    public void viewDashboard(ActionEvent actionEvent) {
    }

    public void viewProduct(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("product.fxml", actionEvent);
    }

    public void viewTransaction(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("transaction.fxml", actionEvent);
    }

    public void logoutAction(ActionEvent actionEvent) throws IOException {
        SceneUtils.switchTo("login.fxml", actionEvent);
    }
}
