package com.example.medico;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class UserAuth{
    @FXML
    private Button addUser;
    private VBox boxmain;
    public Button open_login;
    public TextField nombre;
    public TextField apellidoP;
    public TextField apellidoM;
    public TextField telefono;
    public DatePicker fechaNac;
    public ComboBox peso;
    public ComboBox altura;
    public ComboBox tipoSangre;
    public Button loginButton;

    public TextField user_access;
    public PasswordField password_access;

    public TextField correoElectronico;
    public TextField contrasena;

    public Label login_error;
    public static int user_id;
    public static String user_name;

    public static DatabaseConnection database = null;
    public static Connection connection = null;


    private static void openWindow(Button button, String window) throws IOException {
        // get a handle to the stage
        Stage stage = (Stage) button.getScene().getWindow();
        // do what you have to do
        stage.close();


        FXMLLoader fxmlLoader = new FXMLLoader(UserAuth.class.getResource(window));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle(window.substring(0, window.lastIndexOf('.')));
        stage.show();
    }

    @FXML
    public void openRegister() throws IOException {
        UserAuth.openWindow(loginButton, "registro.fxml");
    }

    @FXML
    private void loginUser() throws SQLException, IOException {
        //validate if user exists

        String user = user_access.getText();
        String password = password_access.getText();
        database = new DatabaseConnection();
        connection = database.connectDatabase("localhost", "5432", "consulta_medica",
                "postgres", "1374021AC");

        int is_user = database.getUserData(connection, user, password, "id_usuario");
        if ( is_user != -1){
            System.out.println(is_user);
            user_id = is_user;
        
            user_name = database.getPatientData(connection, user_id, "nombre");
            UserAuth.openWindow(loginButton, "preguntas.fxml");
        }else{
            login_error.setText("Error usuario o contraseña incorrectos");
        }


    }

    @FXML
    private void addUser() throws SQLException, IOException {
        // add validation
        DatabaseConnection database = new DatabaseConnection();
        Connection connection = database.connectDatabase("localhost", "5432", "consulta_medica",
                "postgres", "1374021AC");
        System.out.println("usuario resgistrado");
        System.out.println( (String) tipoSangre.getValue());
        System.out.println( Float.parseFloat((String) peso.getValue()));
        System.out.println(Float.parseFloat((String) altura.getValue()));
        database.insertUser(connection, correoElectronico.getText(), contrasena.getText() );
        int is_user = database.getUserData(connection, correoElectronico.getText(), contrasena.getText(), "id_usuario");
        database.insertPatient(connection, is_user,
                nombre.getText(), apellidoP.getText(), apellidoM.getText(), fechaNac.getValue(), telefono.getText(),
                Float.parseFloat((String) peso.getValue()), Float.parseFloat((String) altura.getValue()),
                ((String) tipoSangre.getValue())
        );

        UserAuth.openWindow(addUser, "login.fxml");

    }

    @FXML
    private void openLogin() throws IOException {
        UserAuth.openWindow(open_login,"login.fxml");

    }





}
