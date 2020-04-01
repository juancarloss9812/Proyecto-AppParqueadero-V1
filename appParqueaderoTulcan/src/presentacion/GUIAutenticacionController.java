package presentacion;

import Utilidades.Utilidades;
import java.awt.event.ActionEvent;
import mvcf.AActionController;
import mvcf.AModel;
import mvcf.AView;
import negocio.GestorUsuario;
import negocio.GestorVehiculoPersona;
import negocio.usuario;

public class GUIAutenticacionController extends AActionController {

    private final GestorUsuario gestor;
    private final GestorVehiculoPersona gestorUsuario;
    private final GUIAutenticacion vista;

    public GUIAutenticacionController(AModel myModel, AView myView) {
        super(myModel, myView);
        this.vista = (GUIAutenticacion) myView;
        this.gestor = (GestorUsuario) myModel;
        this.gestorUsuario = new GestorVehiculoPersona();
    }

    @Override
    public void actualizar(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "iniciar":
                if (vista.getAccion().equals("IniciarSesion")) {
                    iniciarSesion();
                }
                break;
        }
    }

    private void iniciarSesion() {
        String usuario = vista.getUsuario();
        String contraseña = vista.getContraseña();
        usuario objUsuario = gestorUsuario.BuscarUsuario(usuario);
        String rol = objUsuario.getRol();
        System.out.println(rol);
        String texto;
        if (objUsuario != null && objUsuario.getUsuusuario().equals(usuario) && objUsuario.getUsucontraseña().equals(contraseña)) {
            texto =objUsuario.getUsunombre()+" "+objUsuario.getUsuapellido();
            
            if(rol.equals("VIGILANTE")){
                java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                    public void run() {
                        GUIInicio objInicio = new GUIInicio();
                        objInicio.setVisible(true);

                        vista.setVisible(false);
                       
                    }
                });
            }else 
                if(rol.equals("ADMINISTRATIVO")){
                   {
                    java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                        public void run() {
                            System.out.println(texto);
                            GUIVistaAdministrador objVistaAdm = new GUIVistaAdministrador(texto);

                            objVistaAdm.setVisible(true);
                            //GUIMapaParqueadero objMapa= new GUIMapaParqueadero();
                            vista.setVisible(false);
                            //objMapa.setLocation(720, 0);
                            //objMapa.setVisible(true);
                        }
                    });
                }
            }else {
                    java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                        public void run() {
                            System.out.println(texto);
                            GUIvistaUsuario objVista = new GUIvistaUsuario(texto);

                            objVista.setVisible(true);
                            //GUIMapaParqueadero objMapa= new GUIMapaParqueadero();
                            vista.setVisible(false);
                            //objMapa.setLocation(720, 0);
                            //objMapa.setVisible(true);
                        }
                    });
            }
    }else{
           Utilidades.mensajeError("Usuario o Contraseña incorrecta, porfavor vuelva a intentar", "Fallo en iniciar seccion");
        }
    
}
}