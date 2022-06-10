package com.example.medico;

import com.almasb.fxgl.quest.Quest;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String INSERT_USERS = "INSERT INTO usuario" + "(correo, contrasena) VALUES" + "(?, ?);";

    private static final String INSERT_PATIENT = "INSERT INTO paciente" + "(paciente_id, nombre, apellido_paterno, apellido_materno, fecha_nacimiento, telefono," + "peso, estatura, tipo_sangre) VALUES" + "(?, ?, ?, ?, ?, ?, ?, ?, ?);";


    private static final String GET_USER = "SELECT * FROM usuario" + " WHERE correo = ? AND contrasena = ? ;";

    private static final String GET_PATIENT = "SELECT * FROM paciente" + " WHERE paciente_id = ?;";

    private static final String GET_MEDICINE = "SELECT medicamentos.nombre, medicamentos.tipo, medicamentos.administracion, medicamentos.gramaje, enfermedades.caracteristicas FROM medicamentos, recetas, enfermedades " +
            "where medicamentos.id = recetas.id_medicamento AND enfermedades.id = recetas.id_enfermedad AND " +
            "enfermedades.nombre = ?;";




    public Connection connectDatabase(String host, String port, String database, String user, String password) {
        String url = "";
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }

            Connection connection = null;
            url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("the database connection has been established");
            return connection;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error al conectar con la base de datos" + sqle);
        }

        return null;
    }


    public void insertUser(Connection connection, String email, String password) throws SQLException {
        if (connection != null) {
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

            //we insert the patient


        } else {
            System.out.println("Error database connection");
        }


    }

    public void insertPatient(Connection connection, int id, String name, String lastName, String moLastName, LocalDate date, String phoneNumber, float weight, float height, String bloodType) throws SQLException {

        if (connection != null) {
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, moLastName);
            preparedStatement.setObject(5, date);
            preparedStatement.setString(6, phoneNumber);
            preparedStatement.setFloat(7, weight);
            preparedStatement.setFloat(8, height);
            preparedStatement.setString(9, bloodType);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        }
    }

    public int getUserData(Connection connection, String email, String password, String valueAttribute) throws SQLException {
        if (connection != null) {
            int user_id;
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            //System.out.println(preparedStatement);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                return Integer.parseInt(result.getString("id_usuario"));
            }
            result.close();
            preparedStatement.close();

        } else {
            System.out.println("Error database connection");

        }
        return -1;
    }


    public String getPatientData(Connection connection, int id, String attributeName) throws SQLException {
        if (connection != null) {
            int user_id;
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PATIENT);
            preparedStatement.setInt(1, id);

            //System.out.println(preparedStatement);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                return result.getString(attributeName);
            }
            result.close();
            preparedStatement.close();

        } else {
            System.out.println("Error database connection");

        }
        return "";
    }



    public ArrayList<ArrayList<String>> getMedicine(Connection connection) throws SQLException {
        if (connection != null) {
            System.out.println("enfermedad: " + QuestionSystem.disease);
            PreparedStatement preparedStatement = connection.prepareStatement(GET_MEDICINE);
            preparedStatement.setString(1, QuestionSystem.disease);
            ResultSet result = preparedStatement.executeQuery();
            System.out.println("resultados" + result);
            ArrayList <ArrayList<String>> list_medicine = new ArrayList<ArrayList<String>>();

            while (result.next()) {
                System.out.println(result.getString("nombre"));
                System.out.println(result.getString("tipo"));
                System.out.println(result.getString("administracion"));
                ArrayList<String> medicine = new ArrayList<String>();
                medicine.add(result.getString("nombre"));
                medicine.add(result.getString("tipo"));
                medicine.add(result.getString("administracion"));
                medicine.add(result.getString("gramaje"));
                medicine.add(result.getString("caracteristicas"));
                list_medicine.add(medicine);

            }

            result.close();
            preparedStatement.close();
            System.out.println(list_medicine);
            return list_medicine;

        }{
            System.out.println("Error database connection");
        }
        return null;
    }

}

