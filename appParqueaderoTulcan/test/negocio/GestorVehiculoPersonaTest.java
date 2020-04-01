/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JUAN
 */
public class GestorVehiculoPersonaTest {
    
    public GestorVehiculoPersonaTest() {
    }
    
    /**
     * Test of BuscarPersona method, of class GestorVehiculoPersona.
     */
    @Test
    public void testBuscarPersona() {
        GestorVehiculoPersona gestor = new GestorVehiculoPersona();
        ArrayList<Vehiculo> result = gestor.BuscarVeh(2);
        assertEquals("XSA-266", result.get(0).getVehPlaca());
        assertEquals("Automovil", result.get(0).getVehTipo());
        assertEquals("KIA", result.get(0).getVehMarca());

    }

}
