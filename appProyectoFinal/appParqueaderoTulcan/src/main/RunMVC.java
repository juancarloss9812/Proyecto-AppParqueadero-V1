/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import negocio.GestorVehiculoPersona;
import negocio.GestorUsuario;
import presentacion.GUIAutenticacion;
import presentacion.GUIAutenticacionController;
import presentacion.GUIInicio;
import presentacion.GUIBuscarVehPersona;
import presentacion.GUIBuscarVehPersonaController;
//import presentacion.GUIBuscarVehPersonaController;
/**
 *
 * @author jhayber
 */
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

        myView2.getButon().addActionListener(control);        
    }
}