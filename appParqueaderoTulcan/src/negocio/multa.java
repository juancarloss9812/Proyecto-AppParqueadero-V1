/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.awt.Image;
import javax.swing.Icon;

/**
 *
 * @author JUAN
 */
public class multa {
    private int mulid;
    private String placa;
    private String muldescripcion;
    private String mulfecha;
    private Image mulFotos;
    private String ruta;

    public multa() {
    }

    public multa(int mulid, String placa, String muldescripcion, String mulfecha, Image mulFotos, String ruta) {
        this.mulid = mulid;
        this.placa = placa;
        this.muldescripcion = muldescripcion;
        this.mulfecha = mulfecha;
        this.mulFotos = mulFotos;
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }


    public int getMulid() {
        return mulid;
    }

    public void setMulid(int mulid) {
        this.mulid = mulid;
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

    public String getMulfecha() {
        return mulfecha;
    }

    public void setMulfecha(String mulfecha) {
        this.mulfecha = mulfecha;
    }

    public Image getMulFotos() {
        return mulFotos;
    }

    public void setMulFotos(Image mulFotos) {
        this.mulFotos = mulFotos;
    }

    
}
