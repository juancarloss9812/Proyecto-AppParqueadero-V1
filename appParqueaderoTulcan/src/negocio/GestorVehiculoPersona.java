package negocio;

import acceso.IServicios;
import acceso.ServicioServidor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Properties;
import mvcf.AModel;

public class GestorVehiculoPersona extends AModel{

    private final IServicios servidorParqueadero;
    private Persona persona;
    private ArrayList<Vehiculo> listaVehiculos;
    private ArrayList<multa> listaMultas;
    private ArrayList<bahia> listaBahia;
    private String respuesta;
    private Vehiculo objVehiculo;
    private usuario objusu;

    public GestorVehiculoPersona() {
        servidorParqueadero = new ServicioServidor();
        persona = new Persona();
        objusu= new usuario();
        listaVehiculos = new ArrayList<>();
        respuesta = null;
        objVehiculo = new Vehiculo();
    }
    public usuario BuscarUsuario (String prmUsuario){
        String json = servidorParqueadero.BuscarUsuario(prmUsuario);
        if (!json.equals("No se encoontro Persona.")) {
            usuario varUsuario  = parseToUsuario(objusu, json);
            objusu = varUsuario;
            return varUsuario;
        } else {
            respuesta = json;
        }
        return null;
    }
    public String BuscarLogin(String usuario) {
        String json = servidorParqueadero.BuscarPersona(usuario);
        String varUsuario;
        if (!json.equals("No se encoontro Persona.")) {
            Gson gson = new Gson();
            Properties properties = gson.fromJson(json, Properties.class);
            varUsuario = properties.getProperty("perIdentificacion");
            return varUsuario;
        } else {
            respuesta = json;
        }
        return null;
    
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

    public Vehiculo BuscarPlaca(String prmPlaca) {
        
        String json = servidorParqueadero.BuscarPlaca(prmPlaca);
        if (!json.equals("No se encoontro vehiculo.")) {
            Vehiculo  vehiculo = parseToVehiculo(objVehiculo, json);
            objVehiculo = vehiculo;
            return vehiculo;
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

    public String ingresos(String prmplaca) {
       String json = servidorParqueadero.cuantosIngresos(prmplaca);
        String cantidad;
        if (!json.equals("No se encoontro Persona.")) {
            Gson gson = new Gson();
            Properties properties = gson.fromJson(json, Properties.class);
            cantidad= properties.getProperty("cantidad");
            return cantidad;
        } else {
            respuesta = json;
        }
        return null;
    
    }

    public ArrayList<bahia> reporteIngresoPP(String prmplaca) {
            String arrayJson = servidorParqueadero.reporteIngresoPP(prmplaca);
        if (!arrayJson.equals("No se encoontro multas.")) {
            ArrayList<bahia> misBah = deserializarMisBahia(arrayJson);
            //this.notificar();
            return misBah;
        } else {
            respuesta = arrayJson;
        }
        return null;
    }

    public ArrayList<multa> BuscarMul(int prmUsuario) {
        String arrayJson = servidorParqueadero.buscarMulta(prmUsuario);
        if (!arrayJson.equals("No se encoontro multas.")) {
            ArrayList<multa> misMul = deserializarMisMultas(arrayJson);
            //this.notificar();
            return misMul;
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
    private ArrayList<multa> deserializarMisMultas(String prmArrayJson) {
        multa[] mimul  = new Gson().fromJson(prmArrayJson, multa[].class);
        ArrayList<multa> listMulta = new ArrayList<>();
        for (int i = 0; i < mimul.length; i++) {
            multa mul = mimul[i];
            listMulta.add(mul);
        }
        listaMultas = listMulta;
        return listMulta;
    }

    private Vehiculo parseToVehiculo(Vehiculo prmVehiculo, String json)
    {   
        Gson gson = new Gson();
        Properties properties = gson.fromJson(json, Properties.class);
        objVehiculo.setPerIdentificacion(properties.getProperty("perIdentificacion"));
        objVehiculo.setVehPlaca(properties.getProperty("vehPlaca"));
        objVehiculo.setVehMarca(properties.getProperty("vehMarca"));
        objVehiculo.setVehTipo(properties.getProperty("vehTipo"));
        return objVehiculo;
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
    private usuario parseToUsuario(usuario usuario, String json) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(json, Properties.class);
        objusu.setUsuidentificacion(properties.getProperty("perIdentificacion"));
        objusu.setUsunombre(properties.getProperty("pernombre"));
        objusu.setUsuapellido(properties.getProperty("perapellido"));
        objusu.setUsugenero(properties.getProperty("pergenero"));
        objusu.setUsufechanac(properties.getProperty("perfeahcanac"));
        objusu.setRol(properties.getProperty("rolid"));
        objusu.setUsuusuario(properties.getProperty("accusuario"));
        objusu.setUsucontraseña(properties.getProperty("acccontrasenia"));
           
        
        return objusu;
    }



    public Persona getPersona() {
        return persona;
    }

    public String getRespuesta() {
        return respuesta;
    }

    private ArrayList<bahia> deserializarMisBahia(String arrayJson) {
        bahia[] mibah  = new Gson().fromJson(arrayJson, bahia[].class);
        ArrayList<bahia> listBahia = new ArrayList<>();
        for (int i = 0; i < mibah.length; i++) {
            bahia bah = mibah[i];
            listBahia.add(bah);
        }
        listaBahia = listBahia;
        return listBahia;
    }

    
}
