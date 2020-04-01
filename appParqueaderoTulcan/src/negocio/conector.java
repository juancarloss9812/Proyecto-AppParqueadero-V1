/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JUAN
 */
public class conector {
    
    private Connection conexion = null;
    private final String url = "jdbc:postgresql://localhost:5432/proyectoParqueadero";
    private final String usuario = "postgres";
    private final String contrasenia = "system";

    public Connection getConecion() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            conexion = (Connection) DriverManager.getConnection(url, usuario, contrasenia);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        //return conexion;
        return conexion;
    }
}
