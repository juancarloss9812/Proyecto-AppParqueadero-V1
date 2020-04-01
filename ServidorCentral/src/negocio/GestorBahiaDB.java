/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JUAN
 */
public class GestorBahiaDB {
 
    private final conectorJDBC conector;
    
        //contructor por defecto
    
        public GestorBahiaDB(){
            conector = (conectorJDBC.getInstance());
        }

    public ArrayList<Bahia> TraerBahias(String documento)  
    {
        
        
        try {
            conector.conectarse();
            conector.crearConsulta("select * from bahia b inner join ingreso i on i.bahid = b.bahid and bahestado = '1'");

            ArrayList<Bahia> bahias = new ArrayList();
            while (conector.getResultado().next()) {
                Bahia bah = new Bahia(conector.getResultado().getInt("bahestado"),conector.getResultado().getString("bahid"),conector.getResultado().getString("zonid"),conector.getResultado().getString("ingfechaingreso"),conector.getResultado().getString("ingfechasalida"),conector.getResultado().getString("vehplaca"));
                bahias.add(bah);
            }
            conector.desconectarse();
            return bahias;

        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        return null;
    }
    public void registrarIngreso(Bahia objBahia) {
        String varBahid = objBahia.getPuesto();
        String varplaca = objBahia.getPerIdentificacion();
        String varfechaIngreso = objBahia.getFechaIngreso();
        String varferchaSalida = objBahia.getFechaSalida();
        try {
            conector.conectarse();
            String sql, sql2;
            sql = "INSERT INTO INGRESO (VEHPLACA,BAHID,INGFECHAINGRESO,INGFECHASALIDA) VALUES ('" + varplaca + "','"+varBahid+"','"+varfechaIngreso+"','01-01-2019 00:00')"; 

            sql2 = "UPDATE BAHIA set BAHESTADO= 1 where BAHID= '"+varBahid+"'";

            conector.actualizar(sql);
            conector.actualizar(sql2);
            conector.desconectarse();

        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } 
    }
   
    public void registrarSalida(Bahia objBahia) {
        String varBahid = objBahia.getPuesto();
        String varferchaSalida = objBahia.getFechaSalida();
        try {
            conector.conectarse();
            String sql, sql2;
        sql = "UPDATE INGRESO set INGFECHASALIDA= '"+varferchaSalida+"' where BAHID=  '"+varBahid+"'"; 
        
        sql2 = "UPDATE BAHIA set BAHESTADO = 0 where BAHID= '"+varBahid+"'";
        
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
