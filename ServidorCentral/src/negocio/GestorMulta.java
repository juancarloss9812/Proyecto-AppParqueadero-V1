/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JUAN
 */
public class GestorMulta {
      private final conectorJDBC conector;
            
    public GestorMulta(){
        
        conector = (conectorJDBC.getInstance());
    }
    /* metodo que guarda una imagen JPG en la base de datos
 * input: ID : identificador unico para el registro, osea primary key
 * name: nombre de la imagen para reconocerlo mas adelante
 * ruta: direccion absoluta de la imagen JPG
 */
    public void registrarMulta(multa objMulta) {
        //FileInputStream fis = null;     
        //File file = new File(objMulta.getRuta());
        //fis = new FileInputStream(file);
        String varRuta = objMulta.getRuta();
        String placa =objMulta.getPlaca();
        String descripcion=objMulta.getMuldescripcion();
        String fecha =objMulta.getMulfecha();
        String peridentificacion = objMulta.getPerIdentificacion();
          try {
              conector.conectarse();
              String sql;
                sql = "INSERT INTO multa(mulid , muldescripcion, mulfecha, mulruta,conplaca,peridentificacion)"
                        + " VALUES ("
                        + "(SELECT max(mulid) + 1 from multa),"
                        + "'" + descripcion + "',"
                        + "'" + fecha + "',"
                        + "'" + varRuta + "',"     
                        + "'" + placa + "',"
                        + "'" + peridentificacion + "'"
                        + ")";
                conector.actualizar(sql);
                conector.desconectarse();

        } catch (ClassNotFoundException ex) {
              Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
              Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
            
    }

    public String buscarPlacaPersona(String conPlaca) throws ClassNotFoundException {
          try {
              conector.conectarse();
            conector.crearConsulta("SELECT peridentificacion FROM vehiculo where vehplaca = '"+conPlaca+"'" );
            String id = null;
            if (conector.getResultado().next()) {
                id = conector.getResultado().getString("peridentificacion");
                System.out.println(id);
            }
            conector.desconectarse();
            return id;
       
          } catch (SQLException ex) {
              Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        return null;
    }
   
  
}
