/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.SQLException;

/**
 *
 * @author JUAN
 */
public class GestorVehiculo {
    private final conectorJDBC conector;
    
    public GestorVehiculo(){
        conector = new conectorJDBC();
    }
    public void registrarVehiculo(Vehiculo prmVehiculo) throws ClassNotFoundException, SQLException{
        String sql;
        int identificacion = prmVehiculo.getId();
        String placa = prmVehiculo.getVehPlaca();
        String marca = prmVehiculo.getVehMarca();
        String tipoVehiculo = prmVehiculo.getVehTipo();
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
    }

}
