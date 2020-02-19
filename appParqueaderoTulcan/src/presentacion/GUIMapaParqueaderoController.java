package presentacion;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import mvcf.AActionController;
import mvcf.AModel;
import mvcf.AView;
import negocio.GestorVehiculoPersona;
import negocio.Persona;
import negocio.Vehiculo;
public class GUIMapaParqueaderoController extends AActionController{
     
    private final GestorVehiculoPersona gestor;
    private final GUIBuscarVehPersona vista;


    public GUIMapaParqueaderoController(AModel myModel, AView myView) {
        super(myModel, myView);
        this.vista = (GUIBuscarVehPersona) myView;
        this.gestor = (GestorVehiculoPersona) myModel;
    }

    @Override
    public void actualizar(ActionEvent e) {

    }    
}


