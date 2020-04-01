/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import acceso.Imulta;
import acceso.ServicioServidor;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author JUAN
 */
public class GestorMulta extends Observable{
   private final Imulta iMulta;
   private multa objmulta;
    private String respuesta;
   private Observer obserbador;
    
    
   public GestorMulta() {
        iMulta = new ServicioServidor();
        objmulta = new multa();
        respuesta = null;
    }
   
   public String registrarMulta(multa objMulta){
        String json = iMulta.registrarMulta(objMulta);
        respuesta = json;
        return json;
    }
   /*
    *Metodo que permite añadir observadores de esta clase 
    */
    @Override
    public void addObserver(Observer observer ){
        this.obserbador = observer;
    }
    /*
    *Metodo que notifica los cambios a los observadores
    *de esta clase
    */
    @Override
    public void  notifyObservers (){
        if(obserbador != null){
            obserbador.update(this,"");
        }
    }

   
     
}
