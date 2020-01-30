/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

/**
 *
 * @author jhayber
 */
public interface IServicios {
    public String IniciarSecion(String prmUsuario);
    public String BuscarPersona(int prmDocumento);
    public String buscarVehiculo(int prmDocumento);
}
