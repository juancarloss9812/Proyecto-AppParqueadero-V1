/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import negocio.bahia;

/**
 *
 * @author JUAN
 */
public interface Ibahia {

    public String registrarSalida(bahia objBah);
    public String registrarIngreso(bahia objBah);
    public String TraerBahia();

    public String reporteCongestion();

}
