/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.event.ActionEvent;
import mvcf.AActionController;
import mvcf.AModel;
import mvcf.AView;
import negocio.GestorConductor;
import negocio.GestorVehiculoPersona;

/**
 *
 * @author JUAN
 */
public class GUIRegistroDatosConductorController extends AActionController{
    private final GestorConductor gestor;
    private final GUIRegistrarDatosConductor vista;

    public GUIRegistroDatosConductorController(AModel myModel, AView myView) {
        super(myModel, myView);
         this.vista = (GUIRegistrarDatosConductor) myView;
        this.gestor = (GestorConductor) myModel;   
    
    }


    @Override
    public void actualizar(ActionEvent e) {
      
            }
}
