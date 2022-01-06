package com.pbo.simak;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SimakApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                SimakApplication.class.getResource("dashboard.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sistem Management Keuangan");
        stage.setScene(scene);
        stage.show();
    }
}
