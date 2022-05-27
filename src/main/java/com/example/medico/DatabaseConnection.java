package com.example.medico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String INSERT_USERS = "INSERT INTO usuario" +
            "(correo, contrasena) VALUES " +
            "(?, ?);";
    public Connection connectDatabase(String host, String port, String database,
                                String user, String password){
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
        } catch (java.sql.SQLException sqle){
            System.out.println("Error al conectar con la base de datos" + sqle);
        }

        return null;
    }


    public void insertUser(Connection connection, String email, String password) throws SQLException {
            if (connection != null){
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);


                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                preparedStatement.executeUpdate();

            }


    }
}
