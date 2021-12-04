package com.pbo.simak.controller;

import com.pbo.simak.model.ProductModel;
import com.pbo.simak.utils.SceneUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    protected ObservableList<ProductModel> productList = FXCollections.observableArrayList();
    private HashMap<String, String> selectedProduct = new HashMap<>();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProduct() throws SQLException {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("productCategory"));

        productList = ProductModel.getAllProduct();

        productTable.setItems(productList);

    }

    public void deleteProductAcction(ActionEvent actionEvent) throws SQLException {
        ProductModel.destroy(selectedProduct.get("productId"));
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

    }

    public void addProductAction(ActionEvent actionEvent) throws IOException {
        SceneUtils.addView("addProduct.fxml");
    }


    public void refreshProductAcction(ActionEvent actionEvent) throws SQLException {
        loadProduct();
    }


}
