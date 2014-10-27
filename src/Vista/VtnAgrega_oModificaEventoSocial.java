/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorCliente;
import Controlador.ControladorEmpleado;
import Controlador.ControladorEventos;
import Controlador.ControladorMesaDeDulces;
import Controlador.ControladorProveedores;
import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.EventosSociales;
import Modelo.MesaDeDulces;
import Modelo.Paquete;
import Modelo.PaqueteBasico;
import Modelo.Proveedor;
import Modelo.Servicio;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Carlos
 */
public class VtnAgrega_oModificaEventoSocial extends javax.swing.JFrame {

    private static VtnAgrega_oModificaEventoSocial 
            instanciaVtnAgregaoModificaEventoSocial = new VtnAgrega_oModificaEventoSocial();

    private boolean seModificaraEventoSocial;
    /**
     * Creates new form VtnAgrega_oModificaEventoSocial
     */
    public VtnAgrega_oModificaEventoSocial() {
        initComponents();
        llenaComboCliente();
        llenaComboServicio("Banquetera",comboBanquetera);
        llenaComboServicio("Luces",comboLuces);
        llenaComboServicio("Lugar",comboLugar);
        llenaComboServicio("Carpa",comboCarpa);
        llenaComboServicio("Musica",comboMusica);
        llenaComboMD();
        llenaComboEmpleado();
        setLocationRelativeTo(null);
    }
    
        public static VtnAgrega_oModificaEventoSocial getInstanciaDeVtnAgrega_oModificaEventoSocial() {
        return instanciaVtnAgregaoModificaEventoSocial;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardarEvento = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnRegresarVtnEventosSociales = new javax.swing.JButton();
        txtFechaEvento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbPaqBasico = new javax.swing.JCheckBox();
        cbPaqIntermedio = new javax.swing.JCheckBox();
        cbPaqCompleto = new javax.swing.JCheckBox();
        comboBanquetera = new javax.swing.JComboBox();
        comboLuces = new javax.swing.JComboBox();
        comboCarpa = new javax.swing.JComboBox();
        comboMusica = new javax.swing.JComboBox();
        comboLugar = new javax.swing.JComboBox();
        lblBanquetera = new javax.swing.JLabel();
        lblLuces = new javax.swing.JLabel();
        lblCarpa = new javax.swing.JLabel();
        lblMusica = new javax.swing.JLabel();
        lblLugar = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox();
        comboMesaDulces = new javax.swing.JComboBox();
        lblMesa = new javax.swing.JLabel();
        lblEmpleado = new javax.swing.JLabel();
        comboEmpleado = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Evento");

        btnGuardarEvento.setText("Guardar");
        btnGuardarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEventoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Evento");

        btnRegresarVtnEventosSociales.setText("Regresar");
        btnRegresarVtnEventosSociales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarVtnEventosSocialesActionPerformed(evt);
            }
        });

        txtFechaEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaEventoActionPerformed(evt);
            }
        });

        jLabel2.setText("Cliente:");

        jLabel3.setText("Fecha:");

        cbPaqBasico.setText("Paquete básico");
        cbPaqBasico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPaqBasicoActionPerformed(evt);
            }
        });

        cbPaqIntermedio.setText("Paquete intermedio");
        cbPaqIntermedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPaqIntermedioActionPerformed(evt);
            }
        });

        cbPaqCompleto.setText("Paquete completo");
        cbPaqCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPaqCompletoActionPerformed(evt);
            }
        });

        comboBanquetera.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBanquetera.setEnabled(false);

        comboLuces.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboLuces.setEnabled(false);

        comboCarpa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboCarpa.setEnabled(false);

        comboMusica.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboMusica.setEnabled(false);

        comboLugar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboLugar.setEnabled(false);

        lblBanquetera.setText("Escoja el servicio de banquetes:");

        lblLuces.setText("Escoja el servicio de iluminacion:");

        lblCarpa.setText("Escoja el servicio de carpa:");

        lblMusica.setText("Escoja la música:");

        lblLugar.setText("Escoja el lugar:");

        comboCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboMesaDulces.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblMesa.setText("Escoja la mesa de dulces:");

        lblEmpleado.setText("Empleado: ");

        comboEmpleado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGuardarEvento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresarVtnEventosSociales))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(cbPaqBasico))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(txtFechaEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(comboCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(cbPaqIntermedio)
                                .addGap(20, 20, 20))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblBanquetera)
                                    .addComponent(lblLuces))))
                        .addGap(178, 183, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(comboBanquetera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboCarpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(118, 118, 118)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMusica)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(comboMusica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(comboLuces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLugar)
                                    .addComponent(comboLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(61, 61, 61))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCarpa)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmpleado)
                                    .addComponent(lblMesa))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbPaqCompleto)
                                    .addComponent(comboEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(comboMesaDulces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(44, 44, 44))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMesaDulces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMesa))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(lblEmpleado)
                    .addComponent(comboEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPaqBasico)
                    .addComponent(cbPaqIntermedio)
                    .addComponent(cbPaqCompleto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBanquetera)
                    .addComponent(lblCarpa)
                    .addComponent(lblMusica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBanquetera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMusica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCarpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblLuces)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLugar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboLuces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarEvento)
                    .addComponent(btnRegresarVtnEventosSociales)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaEventoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaEventoActionPerformed

    private void btnRegresarVtnEventosSocialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarVtnEventosSocialesActionPerformed
        // TODO add your handling code here:
        VtnEventosSociales vtnRegreso = VtnEventosSociales.getInstanciaDeVtnEventosSociales();
        vtnRegreso.setVisible(true);
        cerrarEstaVentana();
        
        
    }//GEN-LAST:event_btnRegresarVtnEventosSocialesActionPerformed

    private void cbPaqBasicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPaqBasicoActionPerformed
        cbPaqIntermedio.setSelected(false);
        cbPaqCompleto.setSelected(false);
        comboBanquetera.setEnabled(true);
        comboLuces.setEnabled(true);
        comboCarpa.setEnabled(false);
        comboMusica.setEnabled(false);
        comboLugar.setEnabled(false);
    }//GEN-LAST:event_cbPaqBasicoActionPerformed

    private void cbPaqIntermedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPaqIntermedioActionPerformed
        cbPaqBasico.setSelected(false);
        cbPaqCompleto.setSelected(false);
        comboBanquetera.setEnabled(true);
        comboLuces.setEnabled(true);
        comboCarpa.setEnabled(true);
        
        comboMusica.setEnabled(false);
        comboLugar.setEnabled(false);
        
    }//GEN-LAST:event_cbPaqIntermedioActionPerformed

    private void cbPaqCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPaqCompletoActionPerformed
        cbPaqBasico.setSelected(false);
        cbPaqIntermedio.setSelected(false);
        comboBanquetera.setEnabled(true);
        comboLuces.setEnabled(true);
        comboCarpa.setEnabled(true);
        comboMusica.setEnabled(true);
        comboLugar.setEnabled(true);
        
    }//GEN-LAST:event_cbPaqCompletoActionPerformed

    private void btnGuardarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEventoActionPerformed
        Paquete paquete=null;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        String strFecha = txtFechaEvento.getText();
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        //System.out.println(fecha);
        
        Cliente cliente=new Cliente(0,(String)comboCliente.getSelectedItem(),"","","");
        Empleado empleado=new Empleado(0,(String)comboEmpleado.getSelectedItem(),"","","",0,0);
        MesaDeDulces mesa=new MesaDeDulces(0,(String)comboMesaDulces.getSelectedItem(),0);
        Servicio servBanquetera=new Servicio("Banquetera",0);
        Servicio servCarpa=new Servicio("Carpa",0);
        Servicio servLuces=new Servicio("Luces",0);
        Servicio servLugar=new Servicio("Lugar",0);
        Servicio servMusica=new Servicio("Musica",0);
        
        
        
        if (cbPaqBasico.isSelected()){
            Proveedor prov=new Proveedor(0,(String)comboBanquetera.getSelectedItem(),"","","");
                       
            Proveedor provLuces=new Proveedor(0,(String)comboLuces.getSelectedItem(),"","","");
            
            
            paquete=new PaqueteBasico(servBanquetera,prov,servLuces,provLuces);
            
        }//fin if        
       
       
        ControladorEventos ctrlEvento=new ControladorEventos();
        EventosSociales eventoSocial=new EventosSociales(cliente,fecha,mesa,paquete,0,0,empleado);
        if(!seModificaraEventoSocial){
            try {
                ctrlEvento.agregarEvento(eventoSocial);
                
            } catch (SQLException ex) {
                Logger.getLogger(VtnAgrega_oModificaEventoSocial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            
        }
        
    }//GEN-LAST:event_btnGuardarEventoActionPerformed

    
    private void llenaComboCliente(){
        try {
            ControladorCliente ctrlCliente=new ControladorCliente();
            LinkedList<Cliente> clientes=ctrlCliente.buscarTodosLosClientes();
            
              DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
        for (Cliente cliente : clientes) {
            modeloCombo.addElement(cliente.getNombrePersona());
        }
        comboCliente.setModel(modeloCombo);
            
        } catch (SQLException ex) {
            Logger.getLogger(VtnAgrega_oModificaEventoSocial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void llenaComboEmpleado(){
        try {
            ControladorEmpleado ctrlEmpleado=new ControladorEmpleado();
            LinkedList<Empleado> empleados=ctrlEmpleado.buscarTodosLosEmpleados();
            
              DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
        for (Empleado empleado : empleados) {
            modeloCombo.addElement(empleado.getNombrePersona());
        }
        comboEmpleado.setModel(modeloCombo);
            
        } catch (SQLException ex) {
            Logger.getLogger(VtnAgrega_oModificaEventoSocial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void llenaComboServicio(String servicio,javax.swing.JComboBox comboBox){
        ControladorProveedores ctrlProv=new ControladorProveedores();
        try {
            LinkedList<Proveedor> listaServicios=ctrlProv.proveedoresDelServicio(servicio);
            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
        for (Proveedor prov : listaServicios) {
            modeloCombo.addElement(prov.getNombrePersona());
        }
        comboBox.setModel(modeloCombo);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VtnAgrega_oModificaEventoSocial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    private void llenaComboMD(){
        ControladorMesaDeDulces ctrlMesas=new ControladorMesaDeDulces();
        try {
            LinkedList<MesaDeDulces> listaMesas=ctrlMesas.buscarTodasMD();
            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
        for (MesaDeDulces mesa : listaMesas) {
            modeloCombo.addElement(mesa.getmdNombreDeMesa());
        }
        comboMesaDulces.setModel(modeloCombo);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VtnAgrega_oModificaEventoSocial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void borrarDatosDeCampos() {
        this.txtFechaEvento.setText("");
        this.cbPaqBasico.setSelected(false);
        this.cbPaqIntermedio.setSelected(false);
        this.cbPaqCompleto.setSelected(false);
    }

    
        private void cerrarEstaVentana() {
        borrarDatosDeCampos();
        this.dispose();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VtnAgrega_oModificaEventoSocial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VtnAgrega_oModificaEventoSocial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VtnAgrega_oModificaEventoSocial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VtnAgrega_oModificaEventoSocial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VtnAgrega_oModificaEventoSocial().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarEvento;
    private javax.swing.JButton btnRegresarVtnEventosSociales;
    private javax.swing.JCheckBox cbPaqBasico;
    private javax.swing.JCheckBox cbPaqCompleto;
    private javax.swing.JCheckBox cbPaqIntermedio;
    private javax.swing.JComboBox comboBanquetera;
    private javax.swing.JComboBox comboCarpa;
    private javax.swing.JComboBox comboCliente;
    private javax.swing.JComboBox comboEmpleado;
    private javax.swing.JComboBox comboLuces;
    private javax.swing.JComboBox comboLugar;
    private javax.swing.JComboBox comboMesaDulces;
    private javax.swing.JComboBox comboMusica;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblBanquetera;
    private javax.swing.JLabel lblCarpa;
    private javax.swing.JLabel lblEmpleado;
    private javax.swing.JLabel lblLuces;
    private javax.swing.JLabel lblLugar;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JLabel lblMusica;
    private javax.swing.JTextField txtFechaEvento;
    // End of variables declaration//GEN-END:variables
}
