package presentacion;

import Utilidades.Utilidades;
import java.awt.event.ActionEvent;
import mvcf.AActionController;
import mvcf.AModel;
import mvcf.AView;
import negocio.GestorUsuario;
import negocio.Vigilante;

public class GUIAutenticacionController extends AActionController {

    private final GestorUsuario gestor;
    private final GUIAutenticacion vista;

    public GUIAutenticacionController(AModel myModel, AView myView) {
        super(myModel, myView);
        this.vista = (GUIAutenticacion) myView;
        this.gestor = (GestorUsuario) myModel;
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
        Vigilante objUsuario = gestor.Iniciar_Sesion(usuario);
        if (objUsuario != null && objUsuario.getVigUsuario().equals(usuario) && objUsuario.getVigContrasenia().equals(contraseña)) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    GUIInicio objInicio = new GUIInicio();
                    objInicio.setVisible(true);
                    
                    GUIMapaParqueadero objMapa= new GUIMapaParqueadero();
                    objMapa.setLocation(720, 0);
                    objMapa.setVisible(true);
                }
            });
        } else {
            Utilidades.mensajeAdvertencia("Contraseña incorrecta", "Atencion");
        }
    }
}
///listend