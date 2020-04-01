/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import acceso.IVehiculo;
import acceso.ServicioServidor;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author JUAN
 */
public class GestorVehiculo extends Observable{
    private final IVehiculo servicioVehiculo;
    private Vehiculo vehiculo;
    private String respuesta;
    /*
    *  @observador = se crea para poder añadir mas observadores de esta clase
    */
    private Observer obserbador;
    
    public GestorVehiculo(){
        servicioVehiculo = new ServicioServidor();
        vehiculo = new Vehiculo();
        respuesta = null;
        
    }
    
    public String registrarVehiculo(Vehiculo objVehiculo){
        String json = servicioVehiculo.registrarVehiculo(objVehiculo);
        respuesta = json;
        notifyObservers ();
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
