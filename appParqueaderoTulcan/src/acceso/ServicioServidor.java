/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Persona;
import negocio.Vehiculo;
import negocio.Vigilante;
import negocio.bahia;
import negocio.multa;


public class ServicioServidor implements IServicios,IConductor,IVehiculo,Imulta,Ivigilante,Ibahia {
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
    public String Iniciar_SesionAdm(String prmusuarioo) {
        String respuesta = null;
        String accion = "Iniciar_SesionAdm";
        String informacionCliente;
        informacionCliente = prmusuarioo;
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
    public String BuscarPlaca(String prmPlaca) {
        String respuesta = null;
        String accion = "BuscarPlaca";
        String informacionVehiculo;
        informacionVehiculo = prmPlaca;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionVehiculo);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }
    
    @Override
    public String TraerBahia() {
        String respuesta = null;
        String accion = "TraerBahias";
        String informacionBahia;
        informacionBahia = "";
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionBahia);
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

    
    @Override
    public String registrarConductor(Persona Conductor) {
     String respuesta = null;
        String accion = "registrarConductor";
        String informacionConductor;
        informacionConductor = Conductor.getPerIdentificacion() + "," + Conductor.getPerNombre() + "," + Conductor.getPerApellido() + "," + Conductor.getPerGenero() + "," + Conductor.getPerFechaNac() + "," + Conductor.getPerRol();
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionConductor);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    
    @Override
    public String registrarVehiculo(Vehiculo prmVehiculo) {
        String respuesta = null;
        String accion = "registrarDatosVehiculo";
        String informacionVehiculo;
        informacionVehiculo = prmVehiculo.getPerIdentificacion() + "," + prmVehiculo.getVehPlaca() + "," + prmVehiculo.getVehMarca() + "," + prmVehiculo.getVehTipo();
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionVehiculo);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public String registrarMulta(multa prmMulta) {
         String respuesta = null;
        String accion = "registrarMulta";
        String informacionMulta;
        
        informacionMulta = prmMulta.getPlaca()+"," + prmMulta.getMuldescripcion()+ "," + prmMulta.getMulfecha()+  "," + prmMulta.getRuta();
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionMulta);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    @Override
    public String BuscarPersona(String usuario) {
        String respuesta = null;
        String accion = "BuscarLogin";
        String informacionCliente;
        informacionCliente = usuario;
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
    public String BuscarUsuario(String prmUsuario) {
        String respuesta = null;
        String accion = "BuscarUsuario";
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
    public String registrarIngreso(bahia objBah) {
        String respuesta = null;
        String accion = "registrarIngreso";
        String informacionVigilante;
        informacionVigilante = objBah.getEstadoPuesto()+","+objBah.getPuesto()+","+objBah.getFechaSalida()+","+objBah.getFechaIngreso()+","+objBah.getPerIdentificacion()+","+objBah.getZona();
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionVigilante);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }
    
    @Override
    public String registrarSalida(bahia objBah) {
        String respuesta = null;
        String accion = "registrarSalida";
        String informacionSalida;
        informacionSalida = objBah.getEstadoPuesto()+","+objBah.getPuesto()+","+objBah.getFechaSalida()+","+objBah.getFechaIngreso()+","+objBah.getPerIdentificacion()+","+objBah.getZona();
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionSalida);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public String registrarVigilante(Vigilante objVigilante) {
         String respuesta = null;
        String accion = "registrarVigilante";
        String informacionVigilante;
        informacionVigilante = objVigilante.getVigIdentificacion()+","+objVigilante.getVigNombres()+","+objVigilante.getVigApellidos()+","+objVigilante.getVigGenero()+","+objVigilante.getVigFechaNac()+","+objVigilante.getVigUsuario()+","+objVigilante.getVigContrasenia();
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionVigilante);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;

    
    }

    @Override
    public String buscarMulta(int prmDocumento) {
    
        String respuesta = null;
        String accion = "multa";
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
    public String cuantosIngresos(String prmplaca) {
        String respuesta = null;
        String accion = "BuscarIngresos";
        String informacionCliente;
        informacionCliente = prmplaca;
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
    public String reporteIngresoPP(String prmplaca) {
        String respuesta = null;
        String accion = "reporteIngresoPP";
        String informacionCliente;
        informacionCliente = prmplaca;
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
    public String reporteCongestion() {
         String respuesta = null;
        String accion = "reporteCongestion";
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
}
