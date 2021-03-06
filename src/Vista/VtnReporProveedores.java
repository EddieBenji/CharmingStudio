/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorProveedores;
import Controlador.ControladorReporProveedores;
import Modelo.Cliente;
import Modelo.Proveedor;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class VtnReporProveedores extends javax.swing.JFrame {

    ControladorReporProveedores ctrlRepor = new ControladorReporProveedores();
    ControladorProveedores ctrlProv= new ControladorProveedores();
        private static VtnReporProveedores instanciaDeVtnReporProveedores = new VtnReporProveedores();


    /**
     * Creates new form VtnReporProveedores
     */
    public VtnReporProveedores() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
     public static VtnReporProveedores getInstanciaDeVtnReporProveedores() {
        return instanciaDeVtnReporProveedores;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnExportar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReporteProvs = new javax.swing.JTable();
        btnRegresarVtnReportes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        panelGrafica = new javax.swing.JPanel();
        btnGrafica = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Reporte Proveedores");

        btnExportar.setText("Exportar a PDF");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        tablaReporteProvs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Servicio", "Proveedor", "Email", "Direccion", "Telefono", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaReporteProvs);

        btnRegresarVtnReportes.setText("Regresar");
        btnRegresarVtnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarVtnReportesActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Reportes de los Proveedores");

        btnGenerar.setText("Generar datos");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Banquetera", "Carpa", "Iluminacion", "Lugar", "Musica" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelGraficaLayout = new javax.swing.GroupLayout(panelGrafica);
        panelGrafica.setLayout(panelGraficaLayout);
        panelGraficaLayout.setHorizontalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );

        btnGrafica.setText("Graficar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegresarVtnReportes)
                .addGap(88, 88, 88))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(btnGrafica))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnGenerar)
                                        .addGap(80, 80, 80)
                                        .addComponent(btnExportar))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExportar)
                    .addComponent(btnGenerar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGrafica))
                .addGap(18, 18, 18)
                .addComponent(panelGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegresarVtnReportes)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarVtnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarVtnReportesActionPerformed
     // TODO add your handling code here:
     VtnReportes vtnRegreso = VtnReportes.getInstanciaDeVtnReportes();
     vtnRegreso.setVisible(true);
     cerrarEstaVentana();   
     
    }//GEN-LAST:event_btnRegresarVtnReportesActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        ctrlRepor.generaPDF();
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        
        llenarTablaDeDatos();
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed
    private void cerrarEstaVentana() {
        //borrarDatos();
        this.dispose();
    }


    private void llenarTablaDeDatos() {
        //Declaramos las columnas:
        Object columnasDeDatos[] = new Object[6];

        //obtenemos el modelo default de la tabla:
        DefaultTableModel modeloDeLaTabla = (DefaultTableModel) this.tablaReporteProvs.getModel();

        limpiarTabla();
        try {
            LinkedList<Proveedor> proveedores=ctrlProv.obtenerTodosLosProveedoresConSusServicios();
            
            Proveedor mejorBanquetera = ctrlRepor.buscaMenorPrecio(proveedores,"Banquetera");
            agregaFila(columnasDeDatos,mejorBanquetera,"Banquetera",modeloDeLaTabla);
            
            Proveedor mejorCarpa = ctrlRepor.buscaMenorPrecio(proveedores,"Carpa");
            agregaFila(columnasDeDatos,mejorCarpa,"Carpa",modeloDeLaTabla);
            
            Proveedor mejorIluminacion = ctrlRepor.buscaMenorPrecio(proveedores,"Iluminacion");
            agregaFila(columnasDeDatos,mejorIluminacion,"Iluminacion",modeloDeLaTabla);
            
            Proveedor mejorLugar = ctrlRepor.buscaMenorPrecio(proveedores,"Lugar");
            agregaFila(columnasDeDatos,mejorLugar,"Lugar",modeloDeLaTabla);
            
            Proveedor mejorMusica = ctrlRepor.buscaMenorPrecio(proveedores,"Musica");
            agregaFila(columnasDeDatos,mejorMusica,"Musica",modeloDeLaTabla);
            
            //establecemos a nuestra tabla, el modelo que tenía:
        this.tablaReporteProvs.setModel(modeloDeLaTabla);
        
        } catch (SQLException ex) {
            Logger.getLogger(VtnReporProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
                                                 
        
        

    }
    
    public void agregaFila(Object[] columnasDeDatos, Proveedor proveedor,String servicio,DefaultTableModel modeloDeLaTabla){
                columnasDeDatos[0] = servicio;
                columnasDeDatos[1] = proveedor.getNombrePersona();
                columnasDeDatos[2] = proveedor.getCorreoPersona();
                columnasDeDatos[3] = proveedor.getDireccionPersona();
                columnasDeDatos[4] = proveedor.getTelefonoPersona();
                columnasDeDatos[5] = Float.toString(ctrlRepor.devuelveServicio(proveedor, servicio).getCosto());
                
                modeloDeLaTabla.addRow(columnasDeDatos);
    }

    private void limpiarTabla() {
        DefaultTableModel modeloDeLaTabla = (DefaultTableModel) this.tablaReporteProvs.getModel();
        for (int i = 0; i < tablaReporteProvs.getRowCount(); i++) {
            modeloDeLaTabla.removeRow(0);
            i -= 1;
        }
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
            java.util.logging.Logger.getLogger(VtnReporProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VtnReporProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VtnReporProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VtnReporProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VtnReporProveedores().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnGrafica;
    private javax.swing.JButton btnRegresarVtnReportes;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelGrafica;
    private javax.swing.JTable tablaReporteProvs;
    // End of variables declaration//GEN-END:variables
}
