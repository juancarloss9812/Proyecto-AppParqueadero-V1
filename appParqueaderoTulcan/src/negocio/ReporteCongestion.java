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
public class ReporteCongestion {
   private String atrCantidad;
    private String atrFecha;
    private String atrPlaca;
    private int atrNumHoras;
    private String atrHora;

    public ReporteCongestion() {
    }

    public ReporteCongestion(String atrCantidad, String atrFecha, String atrPlaca, int atrNumHoras, String atrHora) {
        this.atrCantidad = atrCantidad;
        this.atrFecha = atrFecha;
        this.atrPlaca = atrPlaca;
        this.atrNumHoras = atrNumHoras;
        this.atrHora = atrHora;
    }

    public String getAtrCantidad() {
        return atrCantidad;
    }

    public void setAtrCantidad(String atrCantidad) {
        this.atrCantidad = atrCantidad;
    }

    public String getAtrFecha() {
        return atrFecha;
    }

    public void setAtrFecha(String atrFecha) {
        this.atrFecha = atrFecha;
    }

    public String getAtrPlaca() {
        return atrPlaca;
    }

    public void setAtrPlaca(String atrPlaca) {
        this.atrPlaca = atrPlaca;
    }

    public int getAtrNumHoras() {
        return atrNumHoras;
    }

    public void setAtrNumHoras(int atrNumHoras) {
        this.atrNumHoras = atrNumHoras;
    }

    public String getAtrHora() {
        return atrHora;
    }

    public void setAtrHora(String atrHora) {
        this.atrHora = atrHora;
    }
     
}
