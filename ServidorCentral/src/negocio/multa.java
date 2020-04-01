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
public class multa {
    private String placa;
    private String muldescripcion;
    private String mulfecha;
    private String ruta;
    private String perIdentificacion;
    
    

    public multa() {
    
    }

    public multa(String placa, String muldescripcion, String mulfecha, String ruta, String perIdentificacion) {
        this.placa = placa;
        this.muldescripcion = muldescripcion;
        this.mulfecha = mulfecha;
        this.ruta = ruta;
        this.perIdentificacion = perIdentificacion;
    }

    
    public String getPerIdentificacion() {
        return perIdentificacion;
    }

    public void setPerIdentificacion(String perIdentificacion) {
        this.perIdentificacion = perIdentificacion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMuldescripcion() {
        return muldescripcion;
    }

    public void setMuldescripcion(String muldescripcion) {
        this.muldescripcion = muldescripcion;
    }


    public void setMulfecha(String mulfecha) {
        this.mulfecha = mulfecha;
    }

    public String getMulfecha() {
        return mulfecha;
    }

  
}
