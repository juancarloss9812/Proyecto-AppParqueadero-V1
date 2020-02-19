/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import acceso.IVehiculo;
import acceso.ServicioServidor;

/**
 *
 * @author JUAN
 */
public class GestorVehiculo {
    private final IVehiculo servicioVehiculo;
    private Vehiculo vehiculo;
    private String respuesta;
    
    public GestorVehiculo(){
        servicioVehiculo = new ServicioServidor();
        vehiculo = new Vehiculo();
        respuesta = null;
    }
    
    public String registrarVehiculo(Vehiculo objVehiculo){
        String json = servicioVehiculo.registrarVehiculo(objVehiculo);
        respuesta = json;
        return json;
    }
}
