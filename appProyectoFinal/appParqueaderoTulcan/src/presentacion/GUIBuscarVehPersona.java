/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import Utilidades.Utilidades;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import mvcf.AModel;
import mvcf.AView;
import negocio.*;

/**
 *
 * @author Usuario
 */
public class GUIBuscarVehPersona extends javax.swing.JInternalFrame implements AView {

    private String accion;
    private String idPersona;

    public GUIBuscarVehPersona() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoBotones = new javax.swing.ButtonGroup();
        pnlDatos = new javax.swing.JPanel();
        lblInsertarNumero = new javax.swing.JLabel();
        rbtCarnet = new javax.swing.JRadioButton();
        rbtCedula = new javax.swing.JRadioButton();
        txtNumeroIngresado = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pnlInfUsuario = new javax.swing.JPanel();
        lblinfoPersona = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblinfUsuario = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVehiculos = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta de usuarios UNICAUCA");

        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        pnlDatos.setLayout(new java.awt.GridLayout(0, 5));

        lblInsertarNumero.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblInsertarNumero.setText("Buscar por:");
        pnlDatos.add(lblInsertarNumero);

        GrupoBotones.add(rbtCarnet);
        rbtCarnet.setText("Carnet");
        rbtCarnet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlDatos.add(rbtCarnet);

        GrupoBotones.add(rbtCedula);
        rbtCedula.setText("Cedula");
        pnlDatos.add(rbtCedula);

        txtNumeroIngresado.setMargin(new java.awt.Insets(2, 2, 2, 100));
        txtNumeroIngresado.setSelectionStart(5);
        txtNumeroIngresado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroIngresadoFocusLost(evt);
            }
        });
        pnlDatos.add(txtNumeroIngresado);

        btnBuscar.setText("Buscar");
        pnlDatos.add(btnBuscar);

        getContentPane().add(pnlDatos, java.awt.BorderLayout.NORTH);

        pnlInfUsuario.setLayout(new java.awt.GridLayout(4, 0));

        lblinfoPersona.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        lblinfoPersona.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblinfoPersona.setText("Informacion de la Persona");
        lblinfoPersona.setToolTipText("");
        lblinfoPersona.setBorder(javax.swing.BorderFactory.createTitledBorder("Información Usuario"));
        lblinfoPersona.setMaximumSize(new java.awt.Dimension(1000, 1000));
        lblinfoPersona.setMinimumSize(jScrollPane1.getSize());
        lblinfoPersona.setPreferredSize(new java.awt.Dimension(200, 20));
        lblinfoPersona.setVerifyInputWhenFocusTarget(false);
        pnlInfUsuario.add(lblinfoPersona);
        lblinfoPersona.getAccessibleContext().setAccessibleName("Informacion de\n la Persona");

        jScrollPane1.setMaximumSize(new java.awt.Dimension(100, 100));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(200, 50));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(450, 50));

        tblinfUsuario.setBackground(new java.awt.Color(204, 204, 204));
        tblinfUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Rol"
            }
        ));
        tblinfUsuario.setShowHorizontalLines(false);
        tblinfUsuario.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblinfUsuario);

        pnlInfUsuario.add(jScrollPane1);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Vehiculos Asociados");
        pnlInfUsuario.add(jLabel1);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(200, 50));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(450, 80));

        tblVehiculos.setBackground(new java.awt.Color(204, 204, 204));
        tblVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "Marca", "Tipo"
            }
        ));
        tblVehiculos.setShowHorizontalLines(false);
        tblVehiculos.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tblVehiculos);

        pnlInfUsuario.add(jScrollPane2);

        getContentPane().add(pnlInfUsuario, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumeroIngresadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroIngresadoFocusLost
        // TODO add your handling code here:
        try {
            if (validaRadioButon()) {
                if (validarFormulario()) {
                    GestorVehiculoPersona gestor = new GestorVehiculoPersona();
                    String id = getDocumento();
                    Persona per = null;
                    ArrayList<Vehiculo> vehiculo = null;
                    int Id = Integer.parseInt(id);
                    per = gestor.BuscarPersona(Id);
                    vehiculo = gestor.BuscarVeh(Id);
                    if (per != null && vehiculo == null) {
                        accion = "buscarPersona";
                        llenarPersona(per);
                        //btnBuscar.setEnabled(true);
                    }else if (per != null && vehiculo != null){
                        llenarPersona(per);
                        llenarVehiculo(vehiculo);
                    } else{
                        Utilidades.mensajeAdvertencia("La consulta no arrojo datos", "Advertencia");
                    }
                }
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtNumeroIngresadoFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoBotones;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblInsertarNumero;
    private javax.swing.JLabel lblinfoPersona;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlInfUsuario;
    private javax.swing.JRadioButton rbtCarnet;
    private javax.swing.JRadioButton rbtCedula;
    private javax.swing.JTable tblVehiculos;
    private javax.swing.JTable tblinfUsuario;
    private javax.swing.JTextField txtNumeroIngresado;
    // End of variables declaration//GEN-END:variables
    @Override
    public void actualizar(AModel amodel) {

    }

    public void llenarPersona(Persona objPersona) {
        //limpiarTabla(model);
        DefaultTableModel model = (DefaultTableModel) tblinfUsuario.getModel();
        limpiarTabla(model);
        for (int i = 0; i < tblinfUsuario.getRowCount(); i++) {
            model.removeRow(i);
            i -= 1;
        }
        if (objPersona != null) {
            model.addRow(new String[]{
                objPersona.getPerNombre(), objPersona.getPerApellido(), objPersona.getPerRol()
            });
        }
    }

    public void llenarVehiculo(ArrayList<Vehiculo> vehiculos) {
        DefaultTableModel model = (DefaultTableModel) tblVehiculos.getModel();
        limpiarTabla(model);
        Object rowData[] = new Object[3];
        for (Vehiculo veh : vehiculos) {
            rowData[0] = veh.getVehPlaca();
            rowData[1] = veh.getVehMarca();
            rowData[2] = veh.getVehTipo();
            model.addRow(rowData);
        }
    }

    public String getDocumento() {
        idPersona = txtNumeroIngresado.getText();
        return idPersona;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getAccion() {
        return accion;
    }

    public JButton getButon() {
        return btnBuscar;
    }

    public boolean validarFormulario() {
        boolean band = true;
        if (this.getDocumento().equals("")) {
            Utilidades.mensajeError("Debe Ingeesar un id", "FALTA");
            txtNumeroIngresado.requestFocus();
            band = false;
        }
        return band;
    }

    public boolean validaRadioButon() {
        boolean band = true;
        if (rbtCarnet.isSelected() == false && rbtCedula.isSelected() == false) {
            Utilidades.mensajeAdvertencia("No se selecciono ninguna opción de búsqueda", "Advertencia");
            band = false;
        }
        return band;
    }

    public void limpiarTabla(DefaultTableModel objTabla) {
        while (objTabla.getRowCount() > 0) {
            objTabla.removeRow(0);
        }
    }

}