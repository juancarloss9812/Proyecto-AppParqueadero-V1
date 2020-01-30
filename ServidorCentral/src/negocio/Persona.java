package negocio;
/*
**Clase Persona que guarda la informacion que trae la base de datos, la tabla persona
*/
public class Persona {
    private Integer perIdentificacion;
    private String perNombre;
    private String perApellido;
    private String perGenero;
    private String perFechaNac;
    private String perRol;
    //Contructores
    
    public Persona(){
        
    }
    
    public Persona(Integer perIdentificacion, String perNombre, String perApellido, String perGenero, String perFechaNac, String perRol) {
        this.perIdentificacion = perIdentificacion;
        this.perNombre = perNombre;
        this.perApellido = perApellido;
        this.perGenero = perGenero;
        this.perFechaNac = perFechaNac;
        this.perRol = perRol;
    }

    public Integer getPerIdentificacion() {
        return perIdentificacion;
    }

    public void setPerIdentificacion(Integer perIdentificacion) {
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
