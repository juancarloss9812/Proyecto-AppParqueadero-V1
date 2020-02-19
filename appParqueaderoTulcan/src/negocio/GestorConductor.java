/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import acceso.IConductor;
import acceso.ServicioServidor;
import mvcf.AModel;


/**
 *
 * @author JUAN
 */
public class GestorConductor extends AModel{
    
    private final IConductor iConductor;
    private Persona persona;
    private String respuesta;
   
   public GestorConductor() {
        iConductor = new ServicioServidor();
        
        persona = new Persona();
        respuesta = null;
    }

   public String registrarConductor(Persona objConductor){
        String json = iConductor.registrarConductor(objConductor);
        respuesta = json;
        return json;
    }


    public Persona getPersona() {
        return persona;
    }

    public String getRespuesta() {
        return respuesta;
    }
}
