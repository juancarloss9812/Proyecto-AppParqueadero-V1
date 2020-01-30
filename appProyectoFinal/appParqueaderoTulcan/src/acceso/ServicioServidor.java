/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhayber
 */
public class ServicioServidor implements IServicios {
    private final String IP_SERVIDOR = "localhost";
    private PrintStream salidaDecorada;
    private Scanner entradaDecorada;
    private Socket socket;
    private final int PUERTO = 5000;

    /**
     * Conecta al cleinte con el servidor, para que pueda empezar la
     * comunicacion por medio de flujos
     *
     * @param address
     * @param port
     * @throws IOException
     */
    public void conectar(String address, int port) throws IOException {
        socket = new Socket(address, port);
        System.out.println("Conectado");
    }

    /**
     * Se encarga de enviar la informacion de salida y nos retorna la respuesta
     * del servidor
     *
     * @param accion
     * @return
     * @throws IOException
     */
    private String leerFlujoEntradaSalida(String accion) throws IOException {
        String respuesta = "";
        entradaDecorada = new Scanner(socket.getInputStream());
        salidaDecorada = new PrintStream(socket.getOutputStream());
        salidaDecorada.flush();
        // Usando el protocolo de comunicación
        salidaDecorada.println(accion);
        if (entradaDecorada.hasNextLine()) {
            respuesta = entradaDecorada.nextLine();
        }
        return respuesta;
    }

    /**
     * Cierra los flujos de informacion entre el cliente y el servidor
     */
    private void cerrarFlujos() {
        salidaDecorada.close();
        entradaDecorada.close();
    }

    /**
     * desconecta al cleinte del servidor
     */
    private void desconectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String IniciarSecion(String prmUsuario) {
        String respuesta = null;
        String accion = "Iniciar Sesion";
        String informacionCliente;
        informacionCliente = prmUsuario;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionCliente);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public String BuscarPersona(int prmDocumento) {
        String respuesta = null;
        String accion = "buscar";
        int informacionCliente;
        informacionCliente = prmDocumento;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionCliente);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public String buscarVehiculo(int prmDocumento) {
        String respuesta = null;
        String accion = "vehiculo";
        int informacionCliente;
        informacionCliente = prmDocumento;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionCliente);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }
}