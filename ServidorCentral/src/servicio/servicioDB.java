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
import negocio.GestorConductor;
import negocio.GestorMulta;
import negocio.GestorUsuariosBD;
import negocio.GestorVehiculo;
import negocio.Persona;
import negocio.Vigilante;
import negocio.Vehiculo;
import negocio.multa;



public class servicioDB implements Runnable {
    private final GestorVehiculo gestorVehiculo;
    private final GestorUsuariosBD gestorUsuarios;
    private final GestorConductor gestorConductor;
    private final GestorMulta gestorMulta;
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
        Vigilante miUsuario;
        Persona miPersona;
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
            case "registrarDatosVehiculo":
                int idConductor = Integer.parseInt(parametros[1]);
                String placa = parametros[2];
                String marca = parametros[3];
                String tipoVehiculo = parametros[4];
                ArrayList<Vehiculo> objVehiculos = gestorUsuarios.buscarVehiculo(idConductor);
                System.out.println("Id: " + idConductor);
                //cuantos vehiculos hay en el usuario
                System.out.println("Tamaño: " + objVehiculos.size());
                if (objVehiculos.size() == 0){
                    Vehiculo objVehi = new Vehiculo(idConductor,placa , marca, tipoVehiculo);
                    gestorVehiculo.registrarVehiculo(objVehi);
                    salidaDecorada.println("Vehiculo agregado con exito");
                }else{
                    salidaDecorada.println("Vehiculo ya registrado");
                }
            case "registrarMulta":
                multa objmulta;
                int mulid=Integer.parseInt(parametros[1]);
                String conPlaca =parametros[2];
                String muldescripcion=parametros[3];
                String mulfecha = parametros[4];
                String ruta = parametros[5];
                
                objmulta = gestorUsuarios.buscarVehiculoPlaca(conPlaca);
                if (objmulta == null){
                    objmulta = new multa(mulid, conPlaca, muldescripcion, mulfecha, ruta);
                    gestorMulta.registrarMulta(objmulta);
                    salidaDecorada.println("Multa agregado con exito");
                }else{
                    salidaDecorada.println("Multa ya registrado");
                }
             
        }
    }
    
    private JsonObject parseToJSONMulta(multa objMulta)
    {   
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("mulid", objMulta.getMulid());
        jsonobj.addProperty("conPlaca", objMulta.getPlaca());
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
    private JsonObject parseToJSONVehiculo(Vehiculo objVehiculo)
    {   
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("perIdentificacion", objVehiculo.getId());
        jsonobj.addProperty("vehPlaca", objVehiculo.getVehPlaca());
        jsonobj.addProperty("vehMarca", objVehiculo.getVehMarca());
        jsonobj.addProperty("vehTipo", objVehiculo.getVehTipo());
        return jsonobj;
    }
}