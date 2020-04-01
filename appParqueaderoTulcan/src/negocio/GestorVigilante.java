/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import acceso.ServicioServidor;
import mvcf.AModel;

import acceso.Ivigilante;
/**
 *
 * @author JUAN
 */
public class GestorVigilante extends AModel{
    
    private final Ivigilante iVigilante;
    private Persona persona;
    private String respuesta;
   
   public GestorVigilante() {
       
        iVigilante = new ServicioServidor();
        persona = new Persona();
        respuesta = null;
    }

   public String registrarVigilante(Vigilante objVigilante){
        String json = iVigilante.registrarVigilante(objVigilante);
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
