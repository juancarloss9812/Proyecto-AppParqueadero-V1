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

/**
 *
 * @author JUAN
 */
public class GestorMulta {
      private final conectorJDBC conector;
            
    public GestorMulta(){
        
        conector = new conectorJDBC();
    }
    /* metodo que guarda una imagen JPG en la base de datos
 * input: ID : identificador unico para el registro, osea primary key
 * name: nombre de la imagen para reconocerlo mas adelante
 * ruta: direccion absoluta de la imagen JPG
 */
    public void registrarMulta(multa objMulta) throws FileNotFoundException, ClassNotFoundException, SQLException {
        FileInputStream fis = null;     
        File file = new File(objMulta.getRuta());
        fis = new FileInputStream(file);
        String placa =objMulta.getPlaca();
        String descripcion=objMulta.getMuldescripcion();
        String fecha ="";
        
        conector.conectarse();
        String sql;
        sql = "INSERT INTO multa(mulid, muldescripcion, mulfecha, mulFotos, conPlaca)"
                + " VALUES ("
                + "(SELECT max(mulid) + 1 from mulid,"
                + "'" + fecha + "',"
                + "'" + descripcion + "',"
                + "'" + fis + "'"     
                + "'" + placa + "',"
                + ")";
        conector.actualizar(sql);
        conector.desconectarse();
    
    }
   
  
}
