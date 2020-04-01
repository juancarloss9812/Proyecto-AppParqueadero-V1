/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import acceso.Ibahia;
import acceso.ServicioServidor;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author JUAN
 */
public class GestorBahia {
    private final Ibahia servidorBahia;
    private ArrayList<ReporteCongestion> listaReporte;
    private ArrayList<bahia> listaBahia;
    private String respuesta;
     private Semaphore semaphore = new Semaphore(1);

    public GestorBahia (){
        
        servidorBahia = new ServicioServidor();
        listaBahia = new ArrayList();
        respuesta = null;
        
    }
    
    public ArrayList<ReporteCongestion> reporteIngreso() {
        String arrayJson = servidorBahia.reporteCongestion();
        if (!arrayJson.equals("No se encontro reportes.")) {
            ArrayList<ReporteCongestion> misReportes = deserializarReporte(arrayJson);
            return misReportes;
        } else {
            respuesta = arrayJson;
        }
        return null;
    }
    public ArrayList<bahia> TraerBahia() throws InterruptedException {
        semaphore.acquire();
        String arrayJson = servidorBahia.TraerBahia();
        semaphore.release();
        
        if (!arrayJson.equals("No se encoontro BAHIA.")) {
            ArrayList<bahia> misBahias = deserializarMisBahias(arrayJson);
            //this.notificar();
            return misBahias;
        } else {
            respuesta = arrayJson;
        }
        return null;
    }

    private ArrayList<bahia> deserializarMisBahias(String prmArrayJson) {
        bahia[] misBahia = new Gson().fromJson(prmArrayJson, bahia[].class);
        ArrayList<bahia> listBahia = new ArrayList<>();
        for (int i = 0; i < misBahia.length; i++) {
            bahia objBahia = misBahia[i];
            listBahia.add(objBahia);
        }
        listaBahia = listBahia;
        return listBahia;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public String registrarIngreso(bahia objBah) {
        String json = servidorBahia.registrarIngreso(objBah);
        respuesta = json;
        return json;
    }
    public String registrarSalida(bahia objBah) {
        String json = servidorBahia.registrarSalida(objBah);
        respuesta = json;
        return json;
    }

    private ArrayList<ReporteCongestion> deserializarReporte(String arrayJson) {
           ReporteCongestion[] misReportes = new Gson().fromJson(arrayJson, ReporteCongestion[].class);
        ArrayList<ReporteCongestion> listReportes = new ArrayList<>();
        for (int i = 0; i < misReportes.length; i++) {
            ReporteCongestion rep = misReportes[i];
            listReportes.add(rep);
        }
        listaReporte = listReportes;
        return listReportes;
    }
}
