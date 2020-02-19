package negocio;

import java.sql.SQLException;
import java.util.ArrayList;

public class GestorUsuariosBD {
    
    //atributo conector 
    private final conectorJDBC conector;
    
    //contructor por defecto
    
    public GestorUsuariosBD(){
        conector = new conectorJDBC();
    }
    
    public Vigilante IniciarSesion(String prmUsuario) throws ClassNotFoundException, SQLException{
        conector.conectarse();
        conector.crearConsulta("select * from vigilante where vigUsuario='"+prmUsuario +"'");
        Vigilante usuarios = null;
        if (conector.getResultado().next()) {      
            usuarios = new Vigilante(conector.getResultado().getInt("vigidentificacion"),conector.getResultado().getString("vignombres"),conector.getResultado().getString("vigapellidos"),conector.getResultado().getString("viggenero"),conector.getResultado().getString("vigfechanac"),conector.getResultado().getString("vigempresa"),conector.getResultado().getString("vigusuario"),conector.getResultado().getString("vigcontrasenia"));
        }
        conector.desconectarse();
        return usuarios;
    }
    
    public Persona buscarUsuario(int documento) throws ClassNotFoundException, SQLException 
    {
        conector.conectarse();
        conector.crearConsulta("select P.PERIDENTIFICACION, P.PERNOMBRE,P.PERAPELLIDO, P.PERGENERO, P.perFechaNac, r.ROLNOMBRE from persona P inner join rol r on P.PERIDENTIFICACION = r.PERIDENTIFICACION where p.peridentificacion ="+documento);
        Persona miPersona = null;
        if (conector.getResultado().next()) {
            miPersona = new Persona(conector.getResultado().getInt("perIdentificacion"),conector.getResultado().getString("perNombre"),conector.getResultado().getString("perApellido"),conector.getResultado().getString("perGenero"),conector.getResultado().getString("perFechaNac"),conector.getResultado().getString("rolNombre"));
            System.out.println(miPersona.getPerNombre());
        }
        conector.desconectarse();
        return miPersona;
    }
    
    public ArrayList<Vehiculo> buscarVehiculo(int documento) throws ClassNotFoundException, SQLException 
    {
        conector.conectarse();
        conector.crearConsulta("select * from vehiculo where PERIDENTIFICACION ="+documento);

        ArrayList<Vehiculo> vehiculos = new ArrayList();
        while (conector.getResultado().next()) {
            Vehiculo veh = new Vehiculo(conector.getResultado().getInt("perIdentificacion"), conector.getResultado().getString("vehPlaca"), conector.getResultado().getString("vehMarca"), conector.getResultado().getString("vehTipo"));
            vehiculos.add(veh);
        }
        conector.desconectarse();
        return vehiculos;
    }

    public multa buscarVehiculoPlaca(String conPlaca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}