package negocio;

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
        assertEquals("Seguro S.A", result.getVigEmpresa());
        assertEquals("Masculino", result.getVigGenero());
    
         
     }
}
