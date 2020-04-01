/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Bahia;
import negocio.GestorConductor;
import negocio.GestorMulta;
import negocio.GestorUsuariosBD;
import negocio.GestorVehiculo;
import negocio.GestorVigilanteDB;
import negocio.Persona;
import negocio.Vigilante;
import negocio.Vehiculo;
import negocio.multa;
import negocio.usuario;
import negocio.GestorBahiaDB;
import negocio.ReporteCongestion;



public class servicioDB implements Runnable {
    private final GestorVehiculo gestorVehiculo;
    private final GestorUsuariosBD gestorUsuarios;
    private final GestorVigilanteDB gestorVigilante;
    private final GestorConductor gestorConductor;
    private final GestorMulta gestorMulta;
    private final GestorBahiaDB gestorBahia; 
    private static ServerSocket serverSocket;
    private static Socket socket;
    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private static final int PUERTO = 5000;

    public servicioDB() {
        gestorUsuarios = new GestorUsuariosBD();
        gestorConductor = new GestorConductor();
        gestorVehiculo = new GestorVehiculo();
        gestorMulta = new GestorMulta();
        gestorVigilante = new GestorVigilanteDB();
        gestorBahia = new GestorBahiaDB();
    }

    public void iniciar() {
        abrirPuerto();
        while (true) {
            esperarCliente();
            lanzarHilo();
        }
    }

    /**
     * Lanzar Hilo
     */
    private static void lanzarHilo() {
        new Thread(new servicioDB()).start();
    }

    private static void abrirPuerto() {
        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando por el puerto " + PUERTO);
        } catch (Exception ex) {
            Logger.getLogger(servicioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Esperar que el cliente se conecta y le devuelve un SOCKET
     */
    private static void esperarCliente() {
        try {
            socket = serverSocket.accept();
            System.out.println("Cliente Conectado");;
        } catch (IOException ex) {
            Logger.getLogger(servicioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cuerpo del hilo
     */
    @Override
    public void run() {
        try {
            crearFlujos();
            leerFlujos();
            cerrarFlujos();
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(servicioDB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
     /** 
     * Crea los flujos con el Socket
     * @throws IOException
     */
    public void crearFlujos() throws IOException {
        salidaDecorada = new PrintStream(socket.getOutputStream());
        entradaDecorada = new Scanner(socket.getInputStream());
    }
    
    /**
     * Lee los flujos del Socket
     */
    private void leerFlujos() throws ClassNotFoundException, SQLException, FileNotFoundException {
        if(entradaDecorada.hasNextLine()) 
        {
            //Extrae el flujo que envia el cliente
            String peticion = entradaDecorada.nextLine();
            decodificarPeticion(peticion);
        }
        else
        {
            salidaDecorada.flush();
            salidaDecorada.println("No_Encontrado");
        }
    }
    
    /**
     * Cierra los flujos de entrada y salida.
     * @throws IOException
     */
    private void cerrarFlujos() throws IOException {
        salidaDecorada.close();
        entradaDecorada.close();
        socket.close();
    }
    
    /**
     * Decodifica la petición, extrayendo la acción y los parámetros
     * @param peticion petición completa al estilo
     * "accion, informacion"
     */
    private void decodificarPeticion(String peticion) throws ClassNotFoundException, SQLException, FileNotFoundException
    {
        StringTokenizer tokens = new StringTokenizer(peticion, ",");
        String parametros[] = new String[15];
        
        int i=0;
        while(tokens.hasMoreTokens()) {
            parametros[i++] = tokens.nextToken();
        }
        String accion = parametros[0];
        procesarAccion(accion, parametros);
    }
    private void procesarAccion(String accion, String parametros[]) throws ClassNotFoundException, SQLException, FileNotFoundException {
        /**
         * Atributos para usuario
         */
        String usuario;
        int cod;
        usuario objusuario;
        Vigilante miUsuario;
        Persona miPersona;
        usuario objus;
        Vehiculo objVehiculoo;
        switch(accion){
            case "Iniciar Sesion":
                usuario = parametros[1];
                miUsuario = gestorUsuarios.IniciarSesion(usuario);
                
                if(miUsuario == null) {
                    salidaDecorada.println("No se encoontro a algun usuario con ese usuario.");
                }
                else {
                    JsonObject objJson = parseToJSONUsuario(miUsuario);
                    salidaDecorada.println(objJson.toString());
                }
                break;
            
            case "registrarIngreso":
                int vehEstado = Integer.parseInt(parametros[1]);
                String varPuesto = parametros[2];
                String varFEntrada = parametros[3];
                String varPlacaa = parametros[4];
                String varFSalida = parametros[5];
                String varZona = parametros[6];
                Bahia bah = new Bahia(vehEstado,varPuesto,varZona,varFEntrada,varFSalida,varPlacaa);
                gestorBahia.registrarIngreso(bah);
                salidaDecorada.println("Ingreso Registrado con exito");
                break;
                
            case "registrarSalida":
                int vehEstado1 = Integer.parseInt(parametros[1]);
                String varPuesto1 = parametros[2];
                String varFSalida1 = parametros[3];
                String varPlacaa1 = parametros[4];
                String varFEntrada1 = parametros[5];
                String varZona1 = parametros[6];
                Bahia bah2 = new Bahia(vehEstado1,varPuesto1,varZona1,varFEntrada1,varFSalida1,varPlacaa1);
                gestorBahia.registrarSalida(bah2);
                salidaDecorada.println("Salida Registrado con exito");
                break;
            
            case "reporteCongestion":
                try {
                    ArrayList<ReporteCongestion> objReporte = gestorUsuarios.reporteCongestion();
                    if (objReporte == null) {
                        salidaDecorada.println("No se encontro reportes.");
                    } else {
                        salidaDecorada.println(serializarReporteCongestion(objReporte));
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }
                break;
            case "BuscarLogin":
                String log = parametros[1];
                String esta ;
                esta = gestorUsuarios.BuscarLogin(log);
                if(esta == null){
                    salidaDecorada.println("No se encoontro Persona.");
                }
                else{
                    
                    JsonObject jsonobj = new JsonObject();
                    jsonobj.addProperty("perIdentificacion", esta);
                    JsonObject objJson = jsonobj;
                    salidaDecorada.println(objJson.toString());
                }
                break;
                
            
            case "registrarVigilante":
                Vigilante objVigilante;
                Persona objperson;
                cod = Integer.parseInt(parametros[1]);
                String nombre1 = parametros[2];
                String apellido1 = parametros[3];
                String genero1 = parametros[4];
                String fechaNac1 = parametros[5];
                String usuario1 = parametros[6];
                String contrasenia = parametros[7];
                objperson = gestorUsuarios.buscarUsuario(cod);
                if (objperson == null){
                    objVigilante = new Vigilante(cod, nombre1, apellido1, genero1, fechaNac1, genero1, usuario1, contrasenia);
                    gestorVigilante.registrarVigilante(objVigilante);
                    salidaDecorada.println("Vigilante agregado con exito");
                }else{
                    salidaDecorada.println("Vigilante ya registrado");
                }
            case "buscar":
                cod = Integer.parseInt(parametros[1]);
                miPersona = gestorUsuarios.buscarUsuario(cod);
                if(miPersona == null){
                    salidaDecorada.println("No se encoontro Persona.");
                }
                else{
                    JsonObject objJson = parseToJSONPersona(miPersona);
                    salidaDecorada.println(objJson.toString());
                }
                break;
            //reporteIngresoPP
            case "reporteIngresoPP":
                String pl = parametros[1];
                ArrayList<Bahia> objBahias = gestorUsuarios.reporteIngresoPP(pl);                
                if(objBahias == null){
                    salidaDecorada.println("No se encoontro Reportes.");
                }
                else{
                    salidaDecorada.println(serializarBahia(objBahias));
                }
                break;
            
            case "BuscarIngresos":
                String pla = parametros[1];
                String num = gestorUsuarios.BuscarIngresos(pla);
                if(num == null){
                    salidaDecorada.println("No se encoontro Ingreso.");
                }
                else{
                    JsonObject jsonobj = new JsonObject();
                    jsonobj.addProperty("cantidad", num);
                    JsonObject objJson = jsonobj;
                    salidaDecorada.println(objJson.toString());
                }
                break;
            
                
            case "BuscarUsuario":
                String usuarioo = parametros[1];
                objus = gestorUsuarios.buscarUsuario(usuarioo);
                if(objus == null){
                    salidaDecorada.println("No se encoontro Persona.");
                }
                else{
                    JsonObject objJson = parseToJSONusuario(objus);
                    salidaDecorada.println(objJson.toString());
                }
                break;
            case "registrarConductor":
                Persona persona;
                cod = Integer.parseInt(parametros[1]);
                String nombre = parametros[2];
                String apellido = parametros[3];
                String genero = parametros[4];
                String fechaNac = parametros[5];
                String rol = parametros[6];
                persona = gestorUsuarios.buscarUsuario(cod);
                if (persona == null){
                    persona = new Persona(cod, nombre, apellido, genero, fechaNac, rol);
                    gestorConductor.registrarConductor(persona);
                    salidaDecorada.println("Conductor agregado con exito");
                }else{
                    salidaDecorada.println("Conductor ya registrado");
                }

            case "BuscarPlaca":
                String varPlaca = parametros[1];
                System.out.println("entro en el case Buscar Placas");
                objVehiculoo = gestorUsuarios.BuscarPlaca(varPlaca);
                if(objVehiculoo == null){
                    salidaDecorada.println("No se encoontro Vehiculo.");
                }
                else{
                    JsonObject objJson = parseToJSONVehiculo(objVehiculoo);
                    salidaDecorada.println(objJson.toString());
                }
                break;
            

            case "registrarDatosVehiculo":
                int idConductor = Integer.parseInt(parametros[1]);
                String placa = parametros[2];
                String marca = parametros[3];
                String tipoVehiculo = parametros[4];
                Vehiculo objVehiculos = gestorUsuarios.BuscarPlaca(placa);
                System.out.println("Id: " + idConductor);
                //cuantos vehiculos hay en el usuario
                if (objVehiculos == null){
                    Vehiculo objVehi = new Vehiculo(idConductor,placa , marca, tipoVehiculo);
                    gestorVehiculo.registrarVehiculo(objVehi);
                    salidaDecorada.println("Vehiculo agregado con exito");
                }else{
                    salidaDecorada.println("Vehiculo ya registrado");
                }

            case "vehiculo":
                cod = Integer.parseInt(parametros[1]);
                ArrayList<Vehiculo> objVehiculo = gestorUsuarios.buscarVehiculo(cod);                
                if(objVehiculo == null){
                    salidaDecorada.println("No se encoontro Vehiculos.");
                }
                else{
                    salidaDecorada.println(serializarVehiculos(objVehiculo));
                }
                break;

            case "multa":
                cod = Integer.parseInt(parametros[1]);
                ArrayList<multa> objMulta = gestorUsuarios.buscarMulta(cod);                
                if(objMulta == null){
                    salidaDecorada.println("No se encoontro Multa.");
                }
                else{
                    salidaDecorada.println(serializarMultas(objMulta));
                }
                break;

                
            case "TraerBahias":
                String cod1 = parametros[1];
                ArrayList<Bahia> objBahia = gestorBahia.TraerBahias(cod1);                
                if(objBahia == null){
                    salidaDecorada.println("No se encoontro Bahias.");
                }
                else{
                    salidaDecorada.println(serializarBahia(objBahia));
                }
                break;

            case "registrarMulta":
                multa objmulta;
                String conPlaca =parametros[1];
                String muldescripcion=parametros[2];
                String mulfecha = parametros[3];
                String ruta = parametros[4];
                String peridentificacion = gestorMulta.buscarPlacaPersona(conPlaca);
                if(peridentificacion != null){
                    objmulta = new multa(conPlaca, muldescripcion, mulfecha, ruta,peridentificacion);
                    gestorMulta.registrarMulta(objmulta);
                    salidaDecorada.println("Multa agregado con exito");
                }
                break;
        }
    }
    
    private JsonObject parseToJSONMulta(multa objMulta)
    {   
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("placa", objMulta.getPlaca());
        jsonobj.addProperty("muldescripcion", objMulta.getMuldescripcion());
        jsonobj.addProperty("mulfecha", objMulta.getMulfecha());
        jsonobj.addProperty("ruta", objMulta.getRuta());
        return jsonobj;
    }
    
    private JsonObject parseToJSONUsuario(Vigilante objUsuario)
    {   
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("idUsuario", objUsuario.getVigUsuario());
        jsonobj.addProperty("vigNombres", objUsuario.getVigNombres());
        jsonobj.addProperty("vigApellidos", objUsuario.getVigApellidos());
        jsonobj.addProperty("vigGenero", objUsuario.getVigGenero());
        jsonobj.addProperty("vigFechaNac", objUsuario.getVigFechaNac());
        jsonobj.addProperty("vigEmpresa", objUsuario.getVigEmpresa());
        jsonobj.addProperty("vigUsuario", objUsuario.getVigUsuario());
        jsonobj.addProperty("vigContrasenia", objUsuario.getContrasenia());
        return jsonobj;
    }
    
    private JsonObject parseToJSONPersona(Persona objPersona)
    {   
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("perIdentificacion", objPersona.getPerIdentificacion());
        jsonobj.addProperty("perNombre", objPersona.getPerNombre());
        jsonobj.addProperty("perApellido", objPersona.getPerApellido());
        jsonobj.addProperty("perGenero", objPersona.getPerGenero());
        jsonobj.addProperty("perFechaNac", objPersona.getPerFechaNac());
        jsonobj.addProperty("rolNombre", objPersona.getPerRol());
        return jsonobj;
    }
    
    
    private JsonObject parseToJSONVehiculo(Vehiculo objVehiculo)
    {   
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("perIdentificacion", objVehiculo.getId());
        jsonobj.addProperty("vehPlaca", objVehiculo.getVehPlaca());
        jsonobj.addProperty("vehMarca", objVehiculo.getVehMarca());
        jsonobj.addProperty("vehTipo", objVehiculo.getVehTipo());
        return jsonobj;
    }
    
    private JsonObject parseToJSONusuario(usuario objusuario) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("perIdentificacion", objusuario.getUsuidentificacion());
        jsonobj.addProperty("pernombre", objusuario.getUsunombre());
        jsonobj.addProperty("perapellido",objusuario.getUsuapellido());
        jsonobj.addProperty("pergenero", objusuario.getUsugenero());
        jsonobj.addProperty("perfechanac", objusuario.getUsufechanac());
        jsonobj.addProperty("accusuario", objusuario.getUsuusuario());
        jsonobj.addProperty("acccontrasenia", objusuario.getUsucontraseña());
        jsonobj.addProperty("rolid", objusuario.getRol());
        
        return jsonobj;

    }

    private JsonObject parseToJSONBahia(Bahia objBahia) {
       JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("perIdentificacion", objBahia.getPerIdentificacion());
        jsonobj.addProperty("puesto",objBahia.getPuesto());
        jsonobj.addProperty("zona", objBahia.getZona());
        jsonobj.addProperty("estadoPuesto", objBahia.getEstadoPuesto());
        jsonobj.addProperty("fechaIngreso", objBahia.getFechaIngreso());
        jsonobj.addProperty("fechaSalida", objBahia.getFechaSalida());
        
        return jsonobj;

    }
    
    private JsonObject parseToJsonReporteCongestion(ReporteCongestion objReporte) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("atrNumHoras", objReporte.getAtrNumHoras());
        jsonobj.addProperty("atrHora", objReporte.getAtrHora());
        return jsonobj;
    }
    
    private JsonObject parseToJSONplaca(String prmNum) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("cont", prmNum);
        return jsonobj;
    }

    private String serializarVehiculos(ArrayList<Vehiculo> prmVehiculos){
        JsonArray array = new JsonArray();
        JsonObject gsonObj;
        for (Vehiculo veh : prmVehiculos) {
            gsonObj = parseToJSONVehiculo(veh);
            array.add(gsonObj);
        }
        System.out.println("Vehiculos json serializado: " + array.toString());
        return array.toString();
    }
    
    private String serializarMultas(ArrayList<multa> prmmultas){
        JsonArray array = new JsonArray();
        JsonObject gsonObj;
        for (multa mul : prmmultas) {
            gsonObj = parseToJSONMulta(mul);
            array.add(gsonObj);
        }
        System.out.println("Multa json serializado: " + array.toString());
        return array.toString();
    }
    

    private String serializarBahia(ArrayList<Bahia> objBahia) {
        
        JsonArray array = new JsonArray();
        JsonObject gsonObj;
        for (Bahia bah : objBahia) {
            gsonObj = parseToJSONBahia(bah);
            array.add(gsonObj);
        }
        System.out.println("Bahias json serializado: " + array.toString());
        return array.toString();
    }

    private String serializarReporteCongestion(ArrayList<ReporteCongestion> prmReporte) {
        JsonArray array = new JsonArray();
        JsonObject gsonObj;
        for (ReporteCongestion rep : prmReporte) {
            gsonObj = parseToJsonReporteCongestion(rep);
            array.add(gsonObj);
        }
        return array.toString();
    }

    
}
