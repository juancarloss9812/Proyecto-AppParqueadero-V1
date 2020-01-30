/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
import negocio.GestorUsuariosBD;
import negocio.Persona;
import negocio.Vigilante;
import negocio.Vehiculo;

/**
 *
 * @author jhayber
 */
public class servicioDB implements Runnable {

    private final GestorUsuariosBD gestorUsuarios;
    private static ServerSocket serverSocket;
    private static Socket socket;
    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private static final int PUERTO = 5000;

    public servicioDB() {
        gestorUsuarios = new GestorUsuariosBD();
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
    private void leerFlujos() throws ClassNotFoundException, SQLException {
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
    private void decodificarPeticion(String peticion) throws ClassNotFoundException, SQLException
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
    private void procesarAccion(String accion, String parametros[]) throws ClassNotFoundException, SQLException {
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
        }
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