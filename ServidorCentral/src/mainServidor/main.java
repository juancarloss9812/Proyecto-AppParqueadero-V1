package mainServidor;

import java.sql.SQLException;
import negocio.Persona;
import negocio.conectorJDBC;
import servicio.servicioDB;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        servicioDB objServidor = new servicioDB();
        objServidor.iniciar();
    }
}
