package com.example.medico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage app_stage;
    @Override
    public void start(Stage stage) throws IOException {
        app_stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Consulta Medica");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void setStageTitle(String title){
        app_stage.setTitle(title);
    }



    public static void main(String[] args) {
        launch();
    }
}