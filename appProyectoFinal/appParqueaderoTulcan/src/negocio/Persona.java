/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Usuario
 */
public class Persona {
    private String perIdentificacion;
    private String perNombre;
    private String perApellido;
    private String perGenero;
    private String perFechaNac;
    private String perRol;
    
    public Persona(){
        
    }
    public Persona(String perIdentificacion, String perNombre, String perApellido, String perGenero, String perFechaNac, String perRol) {
        this.perIdentificacion = perIdentificacion;
        this.perNombre = perNombre;
        this.perApellido = perApellido;
        this.perGenero = perGenero;
        this.perFechaNac = perFechaNac;
        this.perRol = perRol;
    }

    public String getPerIdentificacion() {
        return perIdentificacion;
    }

    public void setPerIdentificacion(String perIdentificacion) {
        this.perIdentificacion = perIdentificacion;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public String getPerApellido() {
        return perApellido;
    }

    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    public String getPerGenero() {
        return perGenero;
    }

    public void setPerGenero(String perGenero) {
        this.perGenero = perGenero;
    }

    public String getPerFechaNac() {
        return perFechaNac;
    }

    public void setPerFechaNac(String perFechaNac) {
        this.perFechaNac = perFechaNac;
    }

    public String getPerRol() {
        return perRol;
    }

    public void setPerRol(String perRol) {
        this.perRol = perRol;
    }
    
}
