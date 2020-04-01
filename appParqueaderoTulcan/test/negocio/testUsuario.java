
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JUAN
 */
public class testUsuario {
    
    public testUsuario() {
    }
    
    @Test
    public void testBuscarPersona() {
        GestorVehiculoPersona gestor = new GestorVehiculoPersona();
        Persona result = gestor.BuscarPersona(2);
        assertEquals("2", result.getPerIdentificacion());
        assertEquals("JOSE", result.getPerNombre());
        assertEquals("LOPEZ", result.getPerApellido());
        assertEquals("ESTUDIANTE", result.getPerRol());
    }
}
