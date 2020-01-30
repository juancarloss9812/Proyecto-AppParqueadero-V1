/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import Utilidades.Utilidades;
import java.awt.event.ActionEvent;
import mvcf.AActionController;
import mvcf.AModel;
import mvcf.AView;
import negocio.GestorUsuario;
import negocio.Vigilante;

/**
 *
 * @author jhayber
 */
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
        vista.setVisible(false);
    }

    private void iniciarSesion() {
        String usuario = vista.getUsuario();

        String contraseña = vista.getContraseña();
        try {
            Vigilante objUsuario = gestor.Iniciar_Sesion(usuario);

            if (objUsuario != null && objUsuario.getVigUsuario().equals(usuario) && objUsuario.getVigContrasenia().equals(contraseña)) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        GUIInicio objInicio = new GUIInicio();
                        objInicio.setVisible(true);
                    }
                });
            } else {
                Utilidades.mensajeAdvertencia("Contraseña incorrecta", "Atencion");
            }
        } catch (Exception e) {
            Utilidades.mensajeAdvertencia("Usuario no valido", "Atencion");
        }
    }
}