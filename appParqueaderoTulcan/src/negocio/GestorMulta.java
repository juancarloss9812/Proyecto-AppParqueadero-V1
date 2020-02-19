/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import acceso.Imulta;
import acceso.ServicioServidor;

/**
 *
 * @author JUAN
 */
public class GestorMulta {
   private final Imulta iMulta;
   private multa objmulta;
    private String respuesta;
   
   public GestorMulta() {
        iMulta = new ServicioServidor();
        objmulta = new multa();
        respuesta = null;
    }
   
   public String registrarConductor(multa objMulta){
        String json = iMulta.registrarMulta(objMulta);
        respuesta = json;
        return json;
    }
    
     
}
