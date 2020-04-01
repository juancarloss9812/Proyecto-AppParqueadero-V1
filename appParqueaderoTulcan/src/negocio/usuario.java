/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author JUAN
 */
public class usuario {
    

    private String usuidentificacion;
    private String usunombre;
    private String usuapellido;
    private String usugenero;
    private String usufechanac;
    private String usuusuario;
    private String usucontraseña;
    private String rol;

    public usuario() {
    }

    public usuario(String usuidentificacion, String usunombre, String usuapellido, String usugenero, String usufechanac, String usuusuario, String usucontraseña, String rol) {
        this.usuidentificacion = usuidentificacion;
        this.usunombre = usunombre;
        this.usuapellido = usuapellido;
        this.usugenero = usugenero;
        this.usufechanac = usufechanac;
        this.usuusuario = usuusuario;
        this.usucontraseña = usucontraseña;
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    public String getUsuidentificacion() {
        return usuidentificacion;
    }

    public void setUsuidentificacion(String usuidentificacion) {
        this.usuidentificacion = usuidentificacion;
    }

    public String getUsunombre() {
        return usunombre;
    }

    public void setUsunombre(String usunombre) {
        this.usunombre = usunombre;
    }

    public String getUsuapellido() {
        return usuapellido;
    }

    public void setUsuapellido(String usuapellido) {
        this.usuapellido = usuapellido;
    }

    public String getUsugenero() {
        return usugenero;
    }

    public void setUsugenero(String usugenero) {
        this.usugenero = usugenero;
    }

    public String getUsufechanac() {
        return usufechanac;
    }

    public void setUsufechanac(String usufechanac) {
        this.usufechanac = usufechanac;
    }

    public String getUsuusuario() {
        return usuusuario;
    }

    public void setUsuusuario(String usuusuario) {
        this.usuusuario = usuusuario;
    }

    public String getUsucontraseña() {
        return usucontraseña;
    }

    public void setUsucontraseña(String usucontraseña) {
        this.usucontraseña = usucontraseña;
    }
}
