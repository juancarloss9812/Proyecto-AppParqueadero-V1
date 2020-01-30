/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        Persona result = gestor.BuscarPersona(1);
        assertEquals("1", result.getPerIdentificacion());
        assertEquals("Raul", result.getPerNombre());
        assertEquals("Rivaldo", result.getPerApellido());
        assertEquals("Estudiante", result.getPerRol());
    }
}
