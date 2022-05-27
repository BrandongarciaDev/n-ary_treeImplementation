package com.example.medico;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserAuth {
    @FXML
    private Button registerButton;
    private Button LoginButton;
    public TextField correoElectronico;
    public TextField contrasena;

    @FXML
    private void openRegister() throws IOException {
        // get a handle to the stage
        Stage stage = (Stage) registerButton.getScene().getWindow();
        // do what you have to do
        stage.close();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registro.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    private void openLogin(){

    }

    @FXML
    private void addUser() throws SQLException {
        // add validation
        DatabaseConnection database = new DatabaseConnection();
        Connection connection = database.connectDatabase("localhost", "5432", "consulta_medica",
                "postgres", "1374021AC");


        database.insertUser(connection, correoElectronico.getText(), contrasena.getText() );


        }

}
