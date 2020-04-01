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
public class Bahia {
     private int estadoPuesto;
    private String puesto;
    private String zona;
    private String fechaIngreso;
    private String fechaSalida;
    private String perIdentificacion;

    public Bahia() {
    }

    public Bahia(int estadoPuesto, String puesto, String zona, String fechaIngreso, String fechaSalida, String perIdentificacion) {
        this.estadoPuesto = estadoPuesto;
        this.puesto = puesto;
        this.zona = zona;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.perIdentificacion = perIdentificacion;
    }

    public String getPerIdentificacion() {
        return perIdentificacion;
    }

    public void setPerIdentificacion(String perIdentificacion) {
        this.perIdentificacion = perIdentificacion;
    }


    public int getEstadoPuesto() {
        return estadoPuesto;
    }

    public void setEstadoPuesto(int estadoPuesto) {
        this.estadoPuesto = estadoPuesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
   
}
