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
public class GestorConductor {
    
    
    private final conectorJDBC conector;
            
    public GestorConductor(){
        
        conector = (conectorJDBC.getInstance());
    }
    public void registrarConductor(Persona objConductor) {
        int prmIdentificacion = objConductor.getPerIdentificacion();
        String prmNombre = objConductor.getPerNombre();
        String prmApellido = objConductor.getPerApellido();
        String prmGenero = objConductor.getPerGenero();
        String prmFechaNac = objConductor.getPerFechaNac();
        String prmRol = objConductor.getPerRol();
        try {
            conector.conectarse();
            String sql, sql2;
            sql = "INSERT INTO persona (peridentificacion, pernombre, perapellido, pergenero, perfechanac)"
                    + " VALUES ("
                    + "'" + prmIdentificacion + "',"
                    + "'" + prmNombre + "',"
                    + "'" + prmApellido + "',"
                    + "'" + prmGenero + "',"
                    + "'" + prmFechaNac+ "'"      
                    + ")";
            if(prmRol.equals("AMBOS")){
                   sql2 = "INSERT INTO rol (rolid, peridentificacion, rolnombre)"
                        + " VALUES ((SELECT max(rolid) + 1 from rol)"+","
                        +  prmIdentificacion + ","
                        + "'ESTUDIANTE Y DOCENTE'"        
                        + ")";

            }else{
                sql2 = "INSERT INTO rol (rolid, peridentificacion, rolnombre)"
                        + " VALUES ((SELECT max(rolid) + 1 from rol)"+","
                        +  prmIdentificacion + ","
                        + "'" + prmRol + "'"        
                        + ")";
                        }
            conector.actualizar(sql);
            conector.actualizar(sql2);
            conector.desconectarse();
        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        }
}
