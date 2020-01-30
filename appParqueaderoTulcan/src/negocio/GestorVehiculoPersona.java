package negocio;

import acceso.IServicios;
import acceso.ServicioServidor;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Properties;
import mvcf.AModel;

public class GestorVehiculoPersona extends AModel{

    private final IServicios servidorParqueadero;
    private Persona persona;
    private ArrayList<Vehiculo> listaVehiculos;
    private String respuesta;

    public GestorVehiculoPersona() {
        servidorParqueadero = new ServicioServidor();
        persona = new Persona();
        listaVehiculos = new ArrayList<>();
        respuesta = null;
    }

    public Persona BuscarPersona(int prmUsuario) {
        String json = servidorParqueadero.BuscarPersona(prmUsuario);
        if (!json.equals("No se encoontro Persona.")) {
            Persona miPersona = parseToPersona(persona, json);
            persona = miPersona;
            return miPersona;
        } else {
            respuesta = json;
        }
        return null;
    }

    public ArrayList<Vehiculo> BuscarVeh(int prmUsuario) {
        String arrayJson = servidorParqueadero.buscarVehiculo(prmUsuario);
        if (!arrayJson.equals("No se encoontro Vehiculos.")) {
            ArrayList<Vehiculo> misVehiculos = deserializarMisVehiculos(arrayJson);
            //this.notificar();
            return misVehiculos;
        } else {
            respuesta = arrayJson;
        }
        return null;
    }

    private ArrayList<Vehiculo> deserializarMisVehiculos(String prmArrayJson) {
        Vehiculo[] misVehiculos = new Gson().fromJson(prmArrayJson, Vehiculo[].class);
        ArrayList<Vehiculo> listVehiculos = new ArrayList<>();
        for (int i = 0; i < misVehiculos.length; i++) {
            Vehiculo veh = misVehiculos[i];
            listVehiculos.add(veh);
        }
        listaVehiculos = listVehiculos;
        return listVehiculos;
    }

    private Persona parseToPersona(Persona objPersona, String json) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(json, Properties.class);
        persona.setPerIdentificacion(properties.getProperty("perIdentificacion"));
        persona.setPerNombre(properties.getProperty("perNombre"));
        persona.setPerApellido(properties.getProperty("perApellido"));
        persona.setPerGenero(properties.getProperty("perGenero"));
        persona.setPerFechaNac(properties.getProperty("perFechaNac"));
        persona.setPerRol(properties.getProperty("rolNombre"));
        return persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getRespuesta() {
        return respuesta;
    }
}
