package com.example.medico;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.w3c.dom.Node;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Diagnosis {
    @FXML
    private Label diagnosis;
    public ListView medicine_view;

    @FXML
    public void initialize() throws SQLException {
        diagnosis.setText(QuestionSystem.disease);
        ArrayList<ArrayList<String>> list_medicine = UserAuth.database.getMedicine(UserAuth.connection);
        String name, type, adminis;
        for (ArrayList<String> medicine: list_medicine){
            medicine_view.getItems().add(medicine);
        }

    }

}
