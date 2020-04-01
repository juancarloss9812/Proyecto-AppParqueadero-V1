/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JUAN
 */
public class GestorVehiculo {
    private final conectorJDBC conector;
    
    public GestorVehiculo(){
        conector = (conectorJDBC.getInstance());
        }
    public void registrarVehiculo(Vehiculo prmVehiculo) {
        String sql;
        int identificacion = prmVehiculo.getId();
        String placa = prmVehiculo.getVehPlaca();
        String marca = prmVehiculo.getVehMarca();
        String tipoVehiculo = prmVehiculo.getVehTipo();
        try {
            conector.conectarse();
            sql = "INSERT INTO vehiculo(vehplaca, peridentificacion, vehmarca, vehtipo)"
                + " VALUES ("
                + "'" + placa + "',"
                + "'" + identificacion + "',"
                + "'" + marca + "',"
                + "'" + tipoVehiculo+ "'"     
                + ")";
            conector.actualizar(sql);
            conector.desconectarse();
        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        
    }

}
