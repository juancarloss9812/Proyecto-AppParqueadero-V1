package negocio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JUAN
 */
public class testAutentificacion {
    
    public testAutentificacion() {
    }
    

    
     @Test
     public void testIniciarSesion() {
        GestorUsuario gestor = new GestorUsuario();
        Vigilante result = gestor.Iniciar_Sesion("cRomero");
        assertEquals("123", result.getVigContrasenia());
        assertEquals("Cesar", result.getVigNombres());
        assertEquals("Romero", result.getVigApellidos());
        assertEquals("Seguros S.A", result.getVigEmpresa());
        assertEquals("MASCULINO", result.getVigGenero());
    
         
     }
}
