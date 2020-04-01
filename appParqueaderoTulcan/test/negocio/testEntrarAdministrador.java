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
public class testEntrarAdministrador {
    
    public testEntrarAdministrador() {
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testPersona(){
        GestorVehiculoPersona gestor = new GestorVehiculoPersona();
        usuario result = gestor.BuscarUsuario("Rramirez");
        assertEquals("Rramirez", result.getUsuusuario());
        assertEquals("9812", result.getUsucontraseña());
        assertEquals("ROBERTO", result.getUsunombre());
        assertEquals("RAMIREZ", result.getUsuapellido());
        assertEquals("98", result.getUsuidentificacion());
        assertEquals("ADMINISTRATIVO", result.getRol());
    }
}
