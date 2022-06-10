package com.example.medico;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.w3c.dom.Node;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Diagnosis {
    @FXML
    private Label diagnosis;
    public ListView medicine_view;

    public TextArea caracteristicas;

    @FXML
    public void initialize() throws SQLException {
        diagnosis.setText(QuestionSystem.disease);
        ArrayList<ArrayList<String>> list_medicine = UserAuth.database.getMedicine(UserAuth.connection);
        for (ArrayList<String> medicine: list_medicine){
            String medicine_data = "";
            medicine_data = medicine.get(0) + " " + medicine.get(1)  + " " + medicine.get(2);
            int peso = Integer.parseInt(UserAuth.database.getPatientData(UserAuth.connection, UserAuth.user_id, "peso"));

            if ( peso > 15 && peso < 30 && Integer.parseInt(medicine.get(3)) > 9){
                medicine_data += " Gramaje " + Integer.parseInt(medicine.get(3)) / 8 + "mg dos veces al dia durante 10 dias";
            } else if (Integer.parseInt(medicine.get(3)) == 1) {
                medicine_data += medicine.get(3);
            } else{
                medicine_data += " " + Integer.parseInt(medicine.get(3)) / 2 + "mg dos veces al dia durante 15 dias";

            }

            medicine_view.getItems().add(medicine_data);
            caracteristicas.setText(medicine.get(4));
            

            
        }
    }

}
