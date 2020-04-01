/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;


/**
 *
 * @author Juan
 */
public interface IServicios {
    public String IniciarSecion(String prmUsuario);
    public String BuscarPersona(int prmDocumento);
    public String buscarVehiculo(int prmDocumento);
    public String BuscarPlaca(String prmPlaca);
    public String BuscarUsuario(String prmUsuario);
    public String Iniciar_SesionAdm(String prmusuarioo);
    public String buscarMulta(int prmDocumento);
    public String BuscarPersona(String usuario);

    public String cuantosIngresos(String prmplaca);

    public String reporteIngresoPP(String prmplaca);

    
}
