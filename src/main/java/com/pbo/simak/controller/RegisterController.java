package com.pbo.simak.controller;

import com.pbo.simak.model.UserModel;
import com.pbo.simak.utils.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;


public class RegisterController {
    private final HashMap<String, String> storeData = new HashMap<>();
    private final String TOKEN_REGISTER = "simak_java";

    @FXML
    private PasswordField tokenRegister;
    @FXML
    private Label registerMessage;
    @FXML
    private TextField email;
    @FXML
    private TextField fullName;
    @FXML
    private PasswordField password;


    public void registerAction(ActionEvent actionEvent) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (!email.getText().isBlank() && !fullName.getText().isBlank() && !password.getText().isBlank()) {
            if (tokenRegister.getText().equals(TOKEN_REGISTER)) {

                storeData.put("fullName", fullName.getText());
                storeData.put("email", email.getText());
                storeData.put("password", password.getText());

                String msgRegister = UserModel.store(storeData);
                registerMessage.setText(msgRegister);

            } else {
                registerMessage.setText("TOKEN REGISTER IS WRONG !!");
            }

        } else {
            registerMessage.setText("Please Enter Data User");
        }

    }


    @FXML
    private void loginPageRedirect(MouseEvent mouseEvent) throws IOException {
        SceneUtils.switchTo("login.fxml", mouseEvent);
    }
}
