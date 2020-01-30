package negocio;

import Utilidades.Utilidades;
import acceso.IServicios;
import acceso.ServicioServidor;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Properties;
import mvcf.AModel;

public class GestorUsuario extends AModel{

    private final IServicios servidorParqueadero;
    private Vigilante usuario;
    private String respuesta;

    public GestorUsuario() {
        servidorParqueadero = new ServicioServidor();
        usuario = new Vigilante();
        respuesta = null;
    }

    public Vigilante Iniciar_Sesion(String prmUsuario) {
        String json = servidorParqueadero.IniciarSecion(prmUsuario);
        if (!json.equals("No se encoontro a algun usuario con ese usuario.")) {
            Vigilante miUsuario = parseToUsuario(usuario, json);
            usuario = miUsuario;
            this.notificar();
            return miUsuario;
        } else {
            respuesta = json;
        }
        return null;
    }

    private Vigilante parseToUsuario(Vigilante objUsuario, String json) {
        try {
            Gson gson = new Gson();
            Properties properties = gson.fromJson(json, Properties.class);
            usuario.setVigIdentificacion((properties.getProperty("vigIdentificacion")));
            usuario.setVigNombres(properties.getProperty("vigNombres"));
            usuario.setVigApellidos(properties.getProperty("vigApellidos"));
            usuario.setVigGenero(properties.getProperty("vigGenero"));
            usuario.setVigFechaNac(properties.getProperty("vigFechaNac"));
            usuario.setVigEmpresa(properties.getProperty("vigEmpresa"));
            usuario.setVigUsuario(properties.getProperty("vigUsuario"));
            usuario.setVigContrasenia(properties.getProperty("vigContrasenia"));
            return usuario;

        } catch (Exception e) {
            //Utilidades.mensajeAdvertencia("usuario o contraseña incorrectos o posible campos nulos.", "Atencion");
            return null;
        }
        
                }

    public Vigilante getUsuario() {
        return usuario;
    }

    public String getRespuesta() {
        return respuesta;
    }
}