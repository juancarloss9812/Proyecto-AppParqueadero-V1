/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import mvcf.AActionController;
import mvcf.AModel;
import mvcf.AView;
import negocio.GestorVehiculoPersona;
import negocio.Persona;
import negocio.Vehiculo;

/**
 *
 * @author jhayber
 */
public class GUIBuscarVehPersonaController extends AActionController {

    private final GestorVehiculoPersona gestor;
    private final GUIBuscarVehPersona vista;

    public GUIBuscarVehPersonaController(AModel myModel, AView myView) {
        super(myModel, myView);
        this.vista = (GUIBuscarVehPersona) myView;
        this.gestor = (GestorVehiculoPersona) myModel;
    }

    @Override
    public void actualizar(ActionEvent e) {

    }    
}
