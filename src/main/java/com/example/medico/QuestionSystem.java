package com.example.medico;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionSystem {
    public static String disease;
    @FXML
    public ToggleGroup questionGroup;
    public Label diagnosis;
    public Button questionButton;
    public Label paciente;
    private SymptomTree questions = SymptomTree.initializeTree();
    public RadioButton button0;
    public RadioButton button1;
    public RadioButton button2;
    SymptomTree new_data = questions;
    List<RadioButton> buttons = new ArrayList<RadioButton>();

    public void updateQuestions(SymptomTree new_data){
        for (int i = 0; i < new_data.children.size(); i++){
            buttons.get(i).setText(new_data.children.get(i).val);
        }

        if (new_data.children.size() == 2){
            button2.setVisible(false);
        }

    }

    @FXML
    public void initialize(){
        button0.setText(questions.children.get(0).val);
        button1.setText(questions.children.get(1).val);
        button2.setText(questions.children.get(2).val);

        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        paciente.setText(UserAuth.user_name);

    }

    private static void openWindow(String windowName) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(QuestionSystem.class.getResource(windowName));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    public void nextProcess() throws IOException {

        RadioButton selected = (RadioButton) questionGroup.getSelectedToggle();
        System.out.println(selected.getId());

        switch (selected.getId()){
            case "button0":
                new_data = new_data.children.get(0);
                break;
            case "button1":
                new_data = new_data.children.get(1);
                break;
            case "button2":
                new_data = new_data.children.get(2);
                break;

        }

        boolean is_empty = new_data.children.get(0).children.isEmpty();
        System.out.println(is_empty);
        if (is_empty){
            disease = new_data.children.get(0).val;
            System.out.println("is empty");
            // get a handle to the stage
            Stage stage = (Stage) questionButton.getScene().getWindow();
            // do what you have to do
            stage.close();


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("diagnostico.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        }

        System.out.println(new_data.children);
        updateQuestions(new_data);

    }
}
