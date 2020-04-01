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
public class GestorVigilanteDB {
     
    private final conectorJDBC conector;
            
    public GestorVigilanteDB(){
        
        conector = (conectorJDBC.getInstance());
        }
    public void registrarVigilante(Vigilante obVigilante) {
        int  prmIdentificacion = obVigilante.getVigIdentificacion();
        String prmNombre = obVigilante.getVigNombres();
        String prmApellido = obVigilante.getVigApellidos();
        String prmGenero = obVigilante.getVigGenero();
        String prmFechaNac = obVigilante.getVigFechaNac();
        String prmUsuario = obVigilante.getVigUsuario();
        String prmcontrasenia = obVigilante.getContrasenia();
        String prmEmpresa = obVigilante.getVigEmpresa();
        
        try {
            conector.conectarse();
            String sql, sql2,sql3,sql4;
            sql = "INSERT INTO public.persona(\n" +
            "	peridentificacion, pernombre, perapellido, pergenero, perfechanac)\n" +
            "	VALUES ("
                    + "" + prmIdentificacion + ","
                    + "'" + prmNombre + "',"
                    + "'" + prmApellido+ "',"      
                    + "'" + prmGenero + "',"
                    + "'" + prmFechaNac + "'"
                    + ")";

            sql2 = "INSERT INTO vigilante (\n" +
            "	peridentificacion, accusuario, pernombre, perapellido, pergenero, perfechanac, vigempresa)\n" +
            "	VALUES ("
                    + "" + prmIdentificacion + ","
                    + "'" + prmUsuario + "',"
                    + "'" + prmNombre + "',"
                    + "'" + prmApellido + "',"
                    + "'" + prmGenero + "',"
                    + "'" + prmFechaNac + "',"
                    + "'" + prmEmpresa + "'"
                    + ")";
            sql3 = "INSERT INTO acceso ( \n" +
            "	accusuario, peridentificacion, acccontrasenia)\n" +
            "	VALUES ( "
                    + "'" +  prmUsuario + "'"
                    + "," + prmIdentificacion + ","
                    + "'" + prmcontrasenia + "'"
                    + ")";
            sql4 = "INSERT INTO rol (rolid, peridentificacion, rolnombre)"
                        + " VALUES ((SELECT max(rolid) + 1 from rol)"+","
                        +  prmIdentificacion + ","
                        + "'VIGILANTE'"        
                        + ")";
            conector.actualizar(sql);
            conector.actualizar(sql2);
            conector.actualizar(sql3);
            conector.actualizar(sql4);
            conector.desconectarse();
        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
    }
}
