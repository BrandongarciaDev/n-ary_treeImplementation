package com.example.medico;

import java.util.LinkedList;
import java.util.List;

public class SymptomTree {
    String val;
    List<SymptomTree> children = new LinkedList<>();

    SymptomTree(String data){
        val = data;
    }



    SymptomTree(String data, List<SymptomTree> child){
        val = data;
        children = child;
    }

    public static SymptomTree get_nodes(SymptomTree root){

        return root;
    }

    public static SymptomTree initializeTree(){

        SymptomTree root = new SymptomTree("");
        root.children.add(new SymptomTree("Fiebre, temperatura alta"));
        root.children.add(new SymptomTree("Enrojesimiento de piel, piel roja más de lo normal"));
        root.children.add(new SymptomTree("Dolor en una parte especifica del cuerpo"));
        root.children.get(0).children.add(new SymptomTree("Tos con o sin mucosidad"));
        root.children.get(0).children.add(new SymptomTree("Dolor de estomago agudo o leve"));
        root.children.get(0).children.add(new SymptomTree("Dolor de oído e irritabilidad"));
        root.children.get(0).children.get(0).children.add(new SymptomTree("Estornudos, lagrimeo y congestión nasal"));
        root.children.get(0).children.get(0).children.add(new SymptomTree("Dolor en el pecho y dificultad para respirar"));
        root.children.get(0).children.get(1).children.add(new SymptomTree("Deshidratación, dolor abdominal vomito y mareos"));
        root.children.get(0).children.get(1).children.add(new SymptomTree("Orina oscura, nauseas, piel amarilla y cansancio"));
        root.children.get(0).children.get(2).children.add(new SymptomTree("Infección en el oído"));
        root.children.get(0).children.get(0).children.get(0).children.add(new SymptomTree("Resfriado común"));
        root.children.get(0).children.get(0).children.get(1).children.add(new SymptomTree("Bronquitis Aguda"));
        root.children.get(0).children.get(1).children.get(0).children.add(new SymptomTree("Infección estomacal"));
        root.children.get(0).children.get(1).children.get(1).children.add(new SymptomTree("Hepatitis A"));
        // second root branch
        root.children.get(1).children.add(new SymptomTree("Inflamación en el area afectada, o en una parte en especifico"));
        root.children.get(1).children.add(new SymptomTree("Picazón en todo el cuerpo y dificultad para respirar"));
        root.children.get(1).children.get(0).children.add(new SymptomTree("Infección en la piel"));
        root.children.get(1).children.get(1).children.add(new SymptomTree("Alergia"));
        // third root branch
        root.children.get(2).children.add(new SymptomTree("Cansancio muscular ó dolor en las articulaciones"));
        root.children.get(2).children.add(new SymptomTree("hematomas o incapacidad de mover la articulación"));
        root.children.get(2).children.get(0).children.add(new SymptomTree("Disofia muscular"));
        root.children.get(2).children.get(1).children.add(new SymptomTree("Torcedura"));

        return root;
    }



}


