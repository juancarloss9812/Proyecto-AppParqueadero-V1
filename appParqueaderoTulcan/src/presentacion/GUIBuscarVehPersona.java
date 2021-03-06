package presentacion;

import Utilidades.Utilidades;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import mvcf.AModel;
import mvcf.AView;
import negocio.*;

public class GUIBuscarVehPersona extends javax.swing.JInternalFrame implements AView,Observer{
    private static ArrayList<String> atrPlaca;
    private String accion;
    private String idPersona;
    GestorVehiculoPersona gestor ;
    
    public GUIBuscarVehPersona() {
        initComponents();
        btnAsignarPuesto.setVisible(false);
        gestor = new GestorVehiculoPersona();
        
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
        lblinfPersona = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVehiculos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMultas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnregVehiculo = new javax.swing.JButton();
        btnAsignarPuesto = new java.awt.Button();

        setBackground(new java.awt.Color(0, 51, 153));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consultar usuarios");

        pnlDatos.setBackground(new java.awt.Color(0, 51, 153));
        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        pnlDatos.setLayout(new java.awt.GridLayout(0, 5));

        lblInsertarNumero.setForeground(new java.awt.Color(255, 255, 255));
        lblInsertarNumero.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblInsertarNumero.setText("Buscar por:");
        pnlDatos.add(lblInsertarNumero);

        rbtCarnet.setBackground(new java.awt.Color(0, 51, 153));
        GrupoBotones.add(rbtCarnet);
        rbtCarnet.setForeground(new java.awt.Color(255, 255, 255));
        rbtCarnet.setText("Carnet");
        rbtCarnet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlDatos.add(rbtCarnet);

        rbtCedula.setBackground(new java.awt.Color(0, 51, 153));
        GrupoBotones.add(rbtCedula);
        rbtCedula.setForeground(new java.awt.Color(255, 255, 255));
        rbtCedula.setText("Cedula");
        rbtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCedulaActionPerformed(evt);
            }
        });
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
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnlDatos.add(btnBuscar);

        getContentPane().add(pnlDatos, java.awt.BorderLayout.PAGE_START);
        pnlDatos.getAccessibleContext().setAccessibleName("Busqueda");

        pnlInfUsuario.setBackground(new java.awt.Color(0, 51, 153));
        pnlInfUsuario.setLayout(new java.awt.GridLayout(5, 0));

        lblinfPersona.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblinfPersona.setForeground(new java.awt.Color(255, 255, 255));
        lblinfPersona.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlInfUsuario.add(lblinfPersona);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(200, 50));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(450, 80));

        tblVehiculos.setBackground(new java.awt.Color(0, 102, 153));
        tblVehiculos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblVehiculos.setForeground(new java.awt.Color(255, 255, 255));
        tblVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "Marca", "Tipo"
            }
        ));
        tblVehiculos.setGridColor(new java.awt.Color(255, 255, 255));
        tblVehiculos.setShowHorizontalLines(false);
        tblVehiculos.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tblVehiculos);

        pnlInfUsuario.add(jScrollPane2);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(200, 50));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(450, 80));

        tblMultas.setBackground(new java.awt.Color(0, 102, 153));
        tblMultas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblMultas.setForeground(new java.awt.Color(255, 255, 255));
        tblMultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "Fecha y hora", "Descripcion Multa"
            }
        ));
        tblMultas.setGridColor(new java.awt.Color(255, 255, 255));
        tblMultas.setShowHorizontalLines(false);
        tblMultas.setShowVerticalLines(false);
        jScrollPane3.setViewportView(tblMultas);

        pnlInfUsuario.add(jScrollPane3);

        jPanel1.setBackground(new java.awt.Color(12, 61, 135));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setText("REGISTRAR CONDUCTOR");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        btnregVehiculo.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnregVehiculo.setForeground(new java.awt.Color(0, 51, 153));
        btnregVehiculo.setText("REGISTRAR VEHICULO");
        btnregVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregVehiculoActionPerformed(evt);
            }
        });
        jPanel1.add(btnregVehiculo);

        btnAsignarPuesto.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnAsignarPuesto.setForeground(new java.awt.Color(0, 51, 153));
        btnAsignarPuesto.setLabel("ASIGNAR PUESTO");
        btnAsignarPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarPuestoActionPerformed(evt);
            }
        });
        jPanel1.add(btnAsignarPuesto);

        pnlInfUsuario.add(jPanel1);

        getContentPane().add(pnlInfUsuario, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumeroIngresadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroIngresadoFocusLost
        // TODO add your handling code here:
        try {
            
            if (validaRadioButon() && validarFormulario()) {
           
                    String id = getDocumento();
                    Persona per = null;
                    ArrayList<Vehiculo> vehiculo = null;
                    ArrayList<multa> multa = null;
                    int Id = Integer.parseInt(id);
                    per = gestor.BuscarPersona(Id);
                    vehiculo = gestor.BuscarVeh(Id);
                    multa = gestor.BuscarMul(Id);
                    if(multa != null ){
                        accion = "buscarMulta";
                        llenarMultas(multa);
                    }    
                    if (per != null && vehiculo == null) {
                        accion = "buscarPersona";
                        llenarPersona(per);
                        //btnBuscar.setEnabled(true);
                        
                    }else if (per != null && vehiculo != null){
                        llenarPersona(per);
                        llenarVehiculo(vehiculo);
                        llenarPlaca(vehiculo);
                        if(atrPlaca.size() != 0){
                            btnAsignarPuesto.setVisible(true);
                        }
                    } else{
                        btnAsignarPuesto.setVisible(false);
                        Utilidades.mensajeAdvertencia("La consulta no arrojo datos", "Advertencia");
                    }
                }
            

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtNumeroIngresadoFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GUIRegistrarDatosConductor objRegistroConductor = new GUIRegistrarDatosConductor();
        GUIInicio.dskEscritorio.add(objRegistroConductor);
        objRegistroConductor.toFront();
        objRegistroConductor.setVisible(true);
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnregVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregVehiculoActionPerformed
        GUIRegistrarVehiculo objRegistroVehiculo = new GUIRegistrarVehiculo();
        GUIInicio.dskEscritorio.add(objRegistroVehiculo);
        objRegistroVehiculo.toFront();
        objRegistroVehiculo.setVisible(true);

    }//GEN-LAST:event_btnregVehiculoActionPerformed

    private void rbtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtCedulaActionPerformed

    private void btnAsignarPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarPuestoActionPerformed
 // TODO add your handling code here:
        
        GUIMapaParqueadero mapa = null;
        try {
            mapa = new GUIMapaParqueadero(atrPlaca,idPersona);
        } catch (InterruptedException ex) {
            Logger.getLogger(GUIBuscarVehPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        mapa.setVisible(true);

    }//GEN-LAST:event_btnAsignarPuestoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
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
                        llenarPlaca(vehiculo);
                        if(atrPlaca.size() != 0){
                            btnAsignarPuesto.setVisible(true);
                        }
                    } else{

                    }
                }
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoBotones;
    private java.awt.Button btnAsignarPuesto;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnregVehiculo;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblInsertarNumero;
    private javax.swing.JLabel lblinfPersona;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlInfUsuario;
    private javax.swing.JRadioButton rbtCarnet;
    private javax.swing.JRadioButton rbtCedula;
    private javax.swing.JTable tblMultas;
    private javax.swing.JTable tblVehiculos;
    private javax.swing.JTextField txtNumeroIngresado;
    // End of variables declaration//GEN-END:variables
    @Override
    public void actualizar(AModel amodel) {

    }

    public void llenarPersona(Persona objPersona) {
        //limpiarTabla(model);
        //elimina las filas que hay
        if (objPersona != null) {
            lblinfPersona.setText(objPersona.getPerNombre()+" "+objPersona.getPerApellido()+"  "+objPersona.getPerRol());
        }
    }
    public void llenarPlaca(ArrayList<Vehiculo> vehiculos) {
        //ciclo for que intera y recorre para ir escribiendo en la tabla
        atrPlaca = new ArrayList();
        for (int i = 0; i < vehiculos.size(); i++) {
            String aux = vehiculos.get(i).getVehPlaca();
            System.out.println(aux);
            atrPlaca.add(aux);
            }
    }
    
    //llena el arraylist con los dato entregados de gson
    public void llenarVehiculo(ArrayList<Vehiculo> vehiculos) {
        DefaultTableModel model = (DefaultTableModel) tblVehiculos.getModel();
        limpiarTabla(model);
        Object rowData[] = new Object[3];
        //ciclo for que intera y recorre para ir escribiendo en la tabla
        for (Vehiculo veh : vehiculos) {
            rowData[0] = veh.getVehPlaca();
            rowData[1] = veh.getVehMarca();
            rowData[2] = veh.getVehTipo();
            model.addRow(rowData);
        }
    }
    //llena el arraylist con los dato entregados de gson
    public void llenarMultas(ArrayList<multa> multas) {
        DefaultTableModel model = (DefaultTableModel) tblMultas.getModel();
        limpiarTabla(model);
        Object rowData[] = new Object[3];
        //ciclo for que intera y recorre para ir escribiendo en la tabla
        for (multa mul : multas) {
            rowData[0] = mul.getPlaca();
            rowData[1] = mul.getMuldescripcion();
            rowData[2] = mul.getRuta();
            
            model.addRow(rowData);
        }
    }
    
    public void  actualizar(){
        
    }
    
    //entrega el atributo documento
    public String getDocumento() {
        idPersona = txtNumeroIngresado.getText();
        return idPersona;
    }
    //modifica la accion 
    public void setAccion(String accion) {
        this.accion = accion;
    }
    //entrega la accion que se escogio
    public String getAccion() {
        return accion;
    }
    //dar el atributo del boton 
    public JButton getButon() {
        return btnBuscar;
    }
    //en esta parte se valida el formulario, ya sea campos nulos
    public boolean validarFormulario() {
        boolean band = true;
        if (txtNumeroIngresado.getText().isEmpty()) {
            Utilidades.mensajeAdvertencia("Debe Ingeesar un id", "FALTA");
            txtNumeroIngresado.requestFocus();
            band = false;
        }
        return band;
    }
    //en esta parta se valida o es un metodo set que dice el radio Buton 
    public boolean validaRadioButon() {
        boolean band = true;
        if (rbtCarnet.isSelected() == false && rbtCedula.isSelected() == false) {
            Utilidades.mensajeAdvertencia("No se selecciono ninguna opción de búsqueda", "Advertencia");
            band = false;
        }
        return band;
    }
    //en esta parte le libero o limpio la tabla que se usa para los vehiculos
    public void limpiarTabla(DefaultTableModel objTabla) {
        while (objTabla.getRowCount() > 0) {
            objTabla.removeRow(0);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        //ACTUALIZA LA PESTAÑA DE LA TABLA VEHICULOS
        actualizar();
        
    }

}
