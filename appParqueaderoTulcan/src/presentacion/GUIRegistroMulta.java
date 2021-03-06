/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import negocio.GestorMulta;
import negocio.GestorVehiculo;
import negocio.GestorVehiculoPersona;
import negocio.Vehiculo;
import negocio.multa;

/**
 *
 * @author JUAN
 */
public class GUIRegistroMulta extends javax.swing.JInternalFrame {

    /**
     * Creates new form GUIRegistroMulta
     */
    String ruta = null;
    Icon icono;
    private final GestorMulta gestorMulta = new GestorMulta();
    private final GestorVehiculoPersona gestorUsuario = new GestorVehiculoPersona();

    /**
     * Creates new form GUIMultas
     */
    public GUIRegistroMulta() {
        initComponents();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblPlaca = new javax.swing.JLabel();
        txbPlaca = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        cbxDescripcion = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnAbrir = new javax.swing.JButton();
        lblFoto = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        lblUrl = new javax.swing.JLabel();

        setBackground(new java.awt.Color(12, 61, 135));
        setClosable(true);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/unicauca.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bandera.png"))); // NOI18N

        lblPlaca.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPlaca.setForeground(new java.awt.Color(255, 255, 255));
        lblPlaca.setText("PLACA :");

        lblDescripcion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcion.setText("DESCRIPCION : ");

        cbxDescripcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CONDUCTOR SE ESTACIONO EN UNA BAIA LA CUAL NO LE CORRESPONDE", "CONDUCTOR SE ESTACIONO DE MANERA INCORRECTO" }));
        cbxDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDescripcionActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ABRIR FOTO : ");

        btnAbrir.setText("ABRIR...");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnguardar.setText("GUARDAR MULTA");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        lblUrl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblUrl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(49, 49, 49)
                        .addComponent(btnguardar))
                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(cbxDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblPlaca)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txbPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblDescripcion)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnAbrir)))
                    .addContainerGap(360, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addComponent(cbxDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnguardar)
                    .addComponent(lblUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPlaca)
                        .addComponent(txbPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(lblDescripcion)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(btnAbrir))
                    .addContainerGap(328, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed

        JFileChooser j = new JFileChooser();
        int ap = j.showOpenDialog(this);
        if(ap == JFileChooser.APPROVE_OPTION ){
            ruta = j.getSelectedFile().getAbsolutePath();
            ImageIcon imagen = new ImageIcon(ruta);
            icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
            lblFoto.setIcon(icono);
            this.repaint();
            lblUrl.setText(ruta);
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void cbxDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDescripcionActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(!txbPlaca.getText().isEmpty()){
            String varDescripcion = getDescripcion();
            String varPlaca = getPlaca();
            String fecha = getMulfecha();
            String confirmacion;
            String peridentificacion;
            Vehiculo vehiculo = gestorUsuario.BuscarPlaca(varPlaca);

         if(vehiculo == null){
             Utilidades.Utilidades.mensajeError("ERROR", "La placa no se encuentra registrado a ningun usuario");
         }else{
             //le paso el ide del conductor que tiene la multa
             peridentificacion=vehiculo.getPerIdentificacion();
             multa objMulta = new multa(varPlaca, varDescripcion, fecha, ruta,peridentificacion);
             confirmacion = gestorMulta.registrarMulta(objMulta);
             Utilidades.Utilidades.mensajeExito(confirmacion, "Registro Exitoso.");
         }
        }else{
            Utilidades.Utilidades.mensajeError("FALTA LLENAR CAMPOS PARA PODER HACER EL REGISTRO EXITOSO", "CAMPOS NULOS");
        }
    }//GEN-LAST:event_btnguardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnguardar;
    private javax.swing.JComboBox<String> cbxDescripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblUrl;
    private javax.swing.JTextField txbPlaca;
    // End of variables declaration//GEN-END:variables
        
    public String getPlaca(){
        String varPlaca= "";
        varPlaca= txbPlaca.getText();
        System.out.println(varPlaca);
        return varPlaca;
    }
    
    public String getDescripcion(){
        int i;
        String varTipoDescripcion = "";
        if (cbxDescripcion.getSelectedIndex() == 0){
            varTipoDescripcion = "CONDUCTOR SE ESTACIONO EN UNA BAIA NO INCORRECTA";
        }
        if(cbxDescripcion.getSelectedIndex() == 1){
            varTipoDescripcion = "CONDUCTOR SE ESTACIONO DE MANERA INCORRECTO";
        }
        
        return varTipoDescripcion;
    }
        public String getMulfecha() {
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
        String fechaHora= date.format(now)+" "+hour.format(now) ;
        System.out.println(fechaHora);
        return fechaHora;
    }
}
