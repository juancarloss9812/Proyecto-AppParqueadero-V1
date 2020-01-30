/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appParqueaderoUnicauca.negocio;

import negocio.GestorVehiculoPersona;
import negocio.Persona;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jhayber
 */
public class testGestorPersona {
    
    public testGestorPersona() {
    }
    
    @Test
    public void testPersona(){
        GestorVehiculoPersona gestor = new GestorVehiculoPersona();
        Persona result = gestor.BuscarPersona(1);
        assertEquals("1", result.getPerIdentificacion());
        assertEquals("Andres", result.getPerNombre());
        assertEquals("Caicedo", result.getPerApellido());
        assertEquals("Estudiante", result.getPerRol());
    }
}
