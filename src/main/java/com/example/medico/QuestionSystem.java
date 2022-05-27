package com.example.medico;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;
import java.util.List;

public class QuestionSystem {
    @FXML
    public ToggleGroup questionGroup;
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
    }

    @FXML
    public void initialize(){
        button0.setText(questions.children.get(0).val);
        button1.setText(questions.children.get(1).val);
        button2.setText(questions.children.get(2).val);

        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);


    }
    
    

    @FXML
    public void nextProcess(){
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
        System.out.println(new_data.children);
        updateQuestions(new_data);
        
    }
}
