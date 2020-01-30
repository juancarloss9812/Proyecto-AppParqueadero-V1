package main;

import negocio.GestorVehiculoPersona;
import negocio.GestorUsuario;
import presentacion.GUIAutenticacion;
import presentacion.GUIAutenticacionController;
import presentacion.GUIInicio;
import presentacion.GUIBuscarVehPersona;
import presentacion.GUIBuscarVehPersonaController;

public class RunMVC {
    public RunMVC(){
        //Autenticacion
        GestorUsuario gestor = new GestorUsuario();
        
       
        //Vista Autenticacion
        GUIAutenticacion myView = new GUIAutenticacion();
        gestor.addView(myView);
        
        GUIAutenticacionController myController = new GUIAutenticacionController(gestor, myView);        
        myView.setVisible(true);
        
        gestor.notificar();
        
        myView.obtenerBoton().addActionListener(myController);
        myView.obtenerBoton().setActionCommand("iniciar");
        
        //Vista Consultar
        GestorVehiculoPersona objVehPersona = new GestorVehiculoPersona();

        GUIBuscarVehPersona myView2 = new GUIBuscarVehPersona();
        objVehPersona.addView(myView2);

        GUIBuscarVehPersonaController control = new GUIBuscarVehPersonaController(objVehPersona, myView2);

        objVehPersona.notificar();
        //obteniendo el boton el controlador escucha la accion
        myView2.getButon().addActionListener(control);        
    }
}