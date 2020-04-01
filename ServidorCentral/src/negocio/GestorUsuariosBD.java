package negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorUsuariosBD {
    
    //atributo conector 
    private final conectorJDBC conector;
    
    //contructor por defecto
    
    public GestorUsuariosBD(){
        conector = (conectorJDBC.getInstance());
        }
    
    public ArrayList<ReporteCongestion> reporteCongestion() {
        try {
            String sql = "SELECT CONCAT( EXTRACT(HOUR FROM ingfechaingreso), ' to ', CONCAT( extract (HOUR from ingfechaingreso), ':59:59' ) ) as hora,COUNT(*) as numIngresos" 
                + " FROM ingreso GROUP BY DATE(ingfechaingreso), extract (HOUR from ingfechaingreso)"
                + " ORDER BY DATE (ingfechaingreso), extract (HOUR from ingfechaingreso)";
            conector.conectarse();
            conector.crearConsulta(sql);
            ArrayList<ReporteCongestion> reporte = new ArrayList();
            while (conector.getResultado().next()) {
                ReporteCongestion rep = new ReporteCongestion(conector.getResultado().getInt("numIngresos"), conector.getResultado().getString("hora"));
                reporte.add(rep);
            }
            conector.desconectarse();
            return reporte;

        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        return null;
    }
    public Vigilante IniciarSesion(String prmUsuario) {
        try {
            conector.conectarse();
            conector.crearConsulta("select V.PERIDENTIFICACION,V.ACCUSUARIO, V.PERNOMBRE,V.PERAPELLIDO, V.PERGENERO, V.perFechaNac, V.VIGEMPRESA,a.ACCCONTRASENIA\n" +
            "from vigilante V inner join acceso a on V.PERIDENTIFICACION = a.PERIDENTIFICACION where a.accusuario ='"+prmUsuario +"'");
            Vigilante usuarios = null;
            if (conector.getResultado().next()) {      
                usuarios = new Vigilante(conector.getResultado().getInt("peridentificacion"),conector.getResultado().getString("pernombre"),conector.getResultado().getString("perapellido"),conector.getResultado().getString("pergenero"),conector.getResultado().getString("perfechanac"),conector.getResultado().getString("vigempresa"),conector.getResultado().getString("accusuario"),conector.getResultado().getString("acccontrasenia"));
            }
            conector.desconectarse();
            return usuarios;
        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        return null;
    }
    public String BuscarLogin(String login){
        try {
            conector.conectarse();
            conector.crearConsulta("SELECT accusuario FROM acceso where accusuario = '"+login+"'" );
            String log = null;
            if (conector.getResultado().next()) {
                log = conector.getResultado().getString("accusuario");
                System.out.println(log);
            }
            conector.desconectarse();
            return log;
        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        return null;
    }
    
    public usuario buscarUsuario(String documento) 
    {
        try {
            conector.conectarse();
            
            conector.crearConsulta("select P.PERIDENTIFICACION, P.PERNOMBRE,P.PERAPELLIDO, P.PERGENERO, P.perFechaNac, r.ROLNOMBRE, a.accusuario, a.acccontrasenia from persona P inner join rol r on P.PERIDENTIFICACION = r.PERIDENTIFICACION inner join acceso A on A.PERIDENTIFICACION = r.PERIDENTIFICACION  where a.accusuario= '"+documento+"'");
            usuario miPersona = null;
            if (conector.getResultado().next()) {
                miPersona = new usuario(conector.getResultado().getString("perIdentificacion"),conector.getResultado().getString("perNombre"),conector.getResultado().getString("perApellido"),conector.getResultado().getString("perGenero"),conector.getResultado().getString("perFechaNac"),conector.getResultado().getString("accusuario"),conector.getResultado().getString("acccontrasenia"),conector.getResultado().getString("rolNombre"));
                System.out.println(miPersona.getUsunombre());
            }
            conector.desconectarse();
            return miPersona;

        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        return null;
    }
    
    //BuscarIngresos
    public String BuscarIngresos(String documento) 
    {
        String num = null;
        try {
            conector.conectarse();
            
            conector.crearConsulta("select count(*) as cantidad from ingreso where extract (week from ingfechaingreso) between extract (week from current_date)-3 and extract (week from current_date) and vehplaca= '"+documento+"'");
            if (conector.getResultado().next()) {
                num = conector.getResultado().getString("cantidad");
                System.out.println(num);
            }
            conector.desconectarse();
            return num;
        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        return null;
    }
    
    public Persona buscarUsuario(int documento)  
    {
        try {
            conector.conectarse();
            conector.crearConsulta("select P.PERIDENTIFICACION, P.PERNOMBRE,P.PERAPELLIDO, P.PERGENERO, P.perFechaNac, r.ROLNOMBRE from persona P inner join rol r on P.PERIDENTIFICACION = r.PERIDENTIFICACION where p.peridentificacion ="+documento);
            Persona miPersona = null;
            if (conector.getResultado().next()) {
                miPersona = new Persona(conector.getResultado().getInt("perIdentificacion"),conector.getResultado().getString("perNombre"),conector.getResultado().getString("perApellido"),conector.getResultado().getString("perGenero"),conector.getResultado().getString("perFechaNac"),conector.getResultado().getString("rolNombre"));
                System.out.println(miPersona.getPerNombre());
            }
            conector.desconectarse();
            return miPersona;
        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        return null;
    }
    public Vehiculo BuscarPlaca(String documento) 
    {
        try {
            conector.conectarse();
            conector.crearConsulta("select vehplaca, peridentificacion, vehmarca, vehtipo FROM vehiculo where vehplaca = '"+documento+"'");
            Vehiculo objVehiculo = null;
            if (conector.getResultado().next()) {
                objVehiculo = new Vehiculo(conector.getResultado().getInt("peridentificacion"),conector.getResultado().getString("vehplaca"),conector.getResultado().getString("vehmarca"),conector.getResultado().getString("vehtipo"));
                System.out.println(objVehiculo.getId());
            }
            conector.desconectarse();
            return objVehiculo;

        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        return null;
    }
    //reporteIngresoPP
    public ArrayList<Bahia> reporteIngresoPP(String documento)  
    {
        try {
            conector.conectarse();
            
            //TODO HACER CONSULTA PARA SACAR EL REPORTE
            conector.crearConsulta("select *\n" +
            "from ingreso where EXTRACT( WEEK FROM ingfechaingreso) BETWEEN EXTRACT(WEEK FROM current_Date)-3 and  \n" +
            "EXTRACT(WEEK FROM current_Date)\n" +
            "and vehplaca = '"+documento+"'");

            ArrayList<Bahia> bahias = new ArrayList();
            while (conector.getResultado().next()) {
                Bahia bah = new Bahia(00,conector.getResultado().getString("bahid"),"",conector.getResultado().getString("ingfechaingreso"),conector.getResultado().getString("ingfechasalida"),conector.getResultado().getString("vehplaca"));
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

    
    public ArrayList<Vehiculo> buscarVehiculo(int documento) 
    {
        try {
            conector.conectarse();
            conector.crearConsulta("select * from vehiculo where PERIDENTIFICACION = "+documento);
            ArrayList<Vehiculo> vehiculos = new ArrayList();
            while (conector.getResultado().next()) {
                Vehiculo veh = new Vehiculo(conector.getResultado().getInt("perIdentificacion"), conector.getResultado().getString("vehPlaca"), conector.getResultado().getString("vehMarca"), conector.getResultado().getString("vehTipo"));
                vehiculos.add(veh);
            }
            conector.desconectarse();
            return vehiculos;
        } catch (ClassNotFoundException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
            Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        return null;
    }

    public ArrayList<multa> buscarMulta(int documento) 
    {
        try {
            conector.conectarse();
            conector.crearConsulta("select * from multa where PERIDENTIFICACION = "+documento);

            ArrayList<multa> multa = new ArrayList();
            while (conector.getResultado().next()) {
                multa mul = new multa(conector.getResultado().getString("conplaca"),conector.getResultado().getString("mulfecha"),conector.getResultado().getString("mulruta"),conector.getResultado().getString("muldescripcion"),conector.getResultado().getString("peridentificacion"));
                multa.add(mul);
            }
            conector.desconectarse();
            return multa;
        } catch (ClassNotFoundException ex) {
                Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        } catch (SQLException ex) {
                Utilidades.Utilidades.mensajeError("AH OCURRIDO UN ERROR INESPERADO. ", "ERROR");
        }
        return null;
    }
}