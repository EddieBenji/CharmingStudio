/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.DAO.DAOClientes;
import Modelo.Cliente;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class VtnClientes extends javax.swing.JFrame {

    //usamos constantes, que se usará en los JOptionPane:
    private static final int SI = 0;
    private static final int MOSTRAR_DOS_OPCIONES = 0;
    
    //usamos el patrón de diseño Singleton: 
    private static VtnClientes instanciaDeVtnClientes = new VtnClientes();

    /**
     * Creates new form VtnClientes
     */
    public VtnClientes() {
        initComponents();
        //colocamos la ventana en el centro.
        setLocationRelativeTo(null);
    }

    public static VtnClientes getInstanciaDeVtnClientes() {
        return instanciaDeVtnClientes;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombreCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        btnAgregarCliente = new javax.swing.JButton();
        btnModificarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnRegresarVtnPrincipal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Escoja alguna opción para mostrar información de los clientes:");

        txtNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteActionPerformed(evt);
            }
        });

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        btnAgregarCliente.setText("Agregar");
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });

        btnModificarCliente.setText("Modificar");
        btnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setText("Eliminar");
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnRegresarVtnPrincipal.setText("Regresar");
        btnRegresarVtnPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarVtnPrincipalActionPerformed(evt);
            }
        });

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Dirección", "Teléfono", "Correo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaClientes);
        tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(0);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Clientes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(btnAgregarCliente))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnBuscarCliente)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnModificarCliente)
                                                .addGap(33, 33, 33)
                                                .addComponent(btnEliminarCliente)))))))
                        .addGap(0, 385, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRegresarVtnPrincipal))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarCliente)
                    .addComponent(btnModificarCliente)
                    .addComponent(btnEliminarCliente))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnRegresarVtnPrincipal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteActionPerformed

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        // TODO add your handling code here:
        VtnAgrega_oModificaCliente vtnAgregaClientes = VtnAgrega_oModificaCliente.getInstanciaVtnAgregaoModificaCliente();
        vtnAgregaClientes.setTitle("Agregará un cliente");
        vtnAgregaClientes.setVisible(true);

        cerrarEstaVentana();

    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        
        /*Obtenemos el cliente seleccionado de la tabla:*/
        Cliente clienteQueSeEliminara = obtenerInformacionDeRenglonSelecccionado();
        
        //checamos si se seleccionó algún cliente de la tabla,
        //es decir, si no es nulo.
        if (clienteQueSeEliminara != null) {
            //le preguntamos al cliente si de verdad, desea eliminar el 
            //cliente seleccionado:
            int opcionEliminar = JOptionPane.showConfirmDialog(null,
                    "Seguro desea eliminar el cliente seleccionado?",
                    "Eliminará el cliente. ",
                    MOSTRAR_DOS_OPCIONES
            );
            //si lo que escogió el usuario es igual a un "si"
            if (opcionEliminar == SI) {
                //creamos el controlador de clientes:
                DAOClientes controlCliente = new DAOClientes();

                
            try {
                controlCliente.eliminar(clienteQueSeEliminara.getIdPersona());
                mostrarMensajeEnPantalla("Cliente eliminado");
            } catch (SQLException ex) {
                mostrarMensajeEnPantalla("Cliente no eliminado. Error: " + ex.getLocalizedMessage());
            }
            }
        }else{
            mostrarMensajeEnPantalla("No ha seleccionado algún cliente de la tabla");
        }

    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        // TODO add your handling code here:

        /*Declaramos el controlador que busca los clientes
         en la base de datos:                              */
        DAOClientes ctrlBuscarClientes = new DAOClientes();

        try {
            /*El controlador, devuelve una lista con los clientes que coincidieron con la búsqueda:*/
            LinkedList<Cliente> listaDeClientes = ctrlBuscarClientes.buscarCoincidencias(this.txtNombreCliente.getText());
            llenarTablaDeDatos(listaDeClientes);

        } catch (SQLException ex) {

            //si hay Excepción, mostramos el mensaje en pantalla:
            mostrarMensajeEnPantalla("Hubo un error: " + ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void llenarTablaDeDatos(LinkedList<Cliente> listaDeClientes) {
        //Declaramos las columnas:
        Object columnasDeDatos[] = new Object[5];

        //obtenemos el modelo default de la tabla:
        DefaultTableModel modeloDeLaTabla = (DefaultTableModel) this.tablaClientes.getModel();

        limpiarTabla();

        if (listaDeClientes != null) {
            //agregamos a cada columna los datos que le corresponden:
            for (Cliente cliente : listaDeClientes) {
                columnasDeDatos[0] = cliente.getIdPersona();
                columnasDeDatos[1] = cliente.getNombrePersona();
                columnasDeDatos[2] = cliente.getDireccionPersona();
                columnasDeDatos[3] = cliente.getTelefonoPersona();
                columnasDeDatos[4] = cliente.getCorreoPersona();

                //agregamos los datos de cada columna en cada renglón:
                modeloDeLaTabla.addRow(columnasDeDatos);
            }
        } else {
            //se considera el else pero no es necesario                                           
        }
        //establecemos a nuestra tabla, el modelo que tenía:
        this.tablaClientes.setModel(modeloDeLaTabla);

    }

    private void limpiarTabla() {
        DefaultTableModel modeloDeLaTabla = (DefaultTableModel) this.tablaClientes.getModel();
        for (int i = 0; i < tablaClientes.getRowCount(); i++) {
            modeloDeLaTabla.removeRow(0);
            i -= 1;
        }
    }

    private void btnModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarClienteActionPerformed
        // TODO add your handling code here:

        //creamos un cliente temporal, a partir del renglón seleccionado en la tabla:
        Cliente clienteTemporal = obtenerInformacionDeRenglonSelecccionado();

        /*Si el cliente temporal fue nulo, entonces no se seleccionó
         alguno de la tabla, por eso nos interesa más cuando no
         sea nulo, es el caso más común:*/
        if (clienteTemporal != null) {
            //obtenemos la instancia de la ventana: 
            VtnAgrega_oModificaCliente vtnModificaCliente
                    = VtnAgrega_oModificaCliente.getInstanciaVtnAgregaoModificaCliente();
            
            //Obtenemos el id del cliente que se seleccionó en la tabla:
            String id = Integer.toString(clienteTemporal.getIdPersona());
            /*El id del cliente que aparece en la tabla, lo ponemos 
             en el JTextField de la siguiente Ventana: */
            vtnModificaCliente.getTxtIdCliente().setText(id);
            
            //Obtenemos el nombre del cliente que se seleccionó en la tabla:
            String nombre = clienteTemporal.getNombrePersona();
            /*El nombre del cliente que aparece en la tabla, lo ponemos 
             en el JTextField de la siguiente Ventana: */
            vtnModificaCliente.getTxtNombreCliente().setText(nombre);

            //Obtenemos la direccción del cliente que se seleccionó en la tabla:
            String direccion = clienteTemporal.getDireccionPersona();
            /*La dirección del cliente que aparece en la tabla, lo ponemos 
             en el JTextField que le corresponde, de la siguiente Ventana: */
            vtnModificaCliente.getTxtDireccionCliente().setText(direccion);

            //Obtenemos el teléfono del cliente que se seleccionó en la tabla:
            String telefono = clienteTemporal.getTelefonoPersona();
            /*El teléfono del cliente que aparece en la tabla, lo ponemos 
             en el JTextField que le corresponde, de la siguiente Ventana: */
            vtnModificaCliente.getTxtTelefonoCliente().setText(telefono);

            //Obtenemos el correo del cliente que se seleccionó en la tabla:
            String correo = clienteTemporal.getCorreoPersona();
            /*El correo del cliente que aparece en la tabla, lo ponemos 
             en el JTextField que le corresponde, de la siguiente Ventana: */
            vtnModificaCliente.getTxtCorreoCliente().setText(correo);

            //le ponemos el título a la ventana:
            vtnModificaCliente.setTitle("Modificará la información de un cliente");
            /*ponemos en verdadero un booleano, indicando que 
             se modificará un cliente: */
            vtnModificaCliente.setSeModificaraCliente(true);
            //hacemos visible la ventana:
            vtnModificaCliente.setVisible(true);

            vtnModificaCliente.setClienteDeLaTabla(clienteTemporal);
            //cerramos esta ventana:
            cerrarEstaVentana();
        } else {
            //quiere decir que el usuario no ha seleccionado algún cliente
            //la tabla:
            mostrarMensajeEnPantalla("No seleccionaste algún cliente de la tabla.");
        }

    }//GEN-LAST:event_btnModificarClienteActionPerformed

    private Cliente obtenerInformacionDeRenglonSelecccionado() {
        //obtiene el número del renglón seleccionado en la tabla.
        int numDeRenglonSeleccionado = this.tablaClientes.getSelectedRow();
        /*Si es negativo, quiere decir que ningún renglón ha sido seleccionado:*/
        if (numDeRenglonSeleccionado < 0) {
            return null;
        }
        //declaramos las constantes, de las columnas donde está la información:
        int columnaId = 0, columnaNombre = 1, columnaDireccion = 2,
                columnaTelefono = 3, columnaCorreo = 4;
        //obtenemos la información del renglón seleccionado.
        int id = (int) tablaClientes.getValueAt(numDeRenglonSeleccionado, columnaId);
        String nombre = (String) tablaClientes.getValueAt(numDeRenglonSeleccionado, columnaNombre);
        String direccion = (String) tablaClientes.getValueAt(numDeRenglonSeleccionado, columnaDireccion);
        String telefono = (String) tablaClientes.getValueAt(numDeRenglonSeleccionado, columnaTelefono);
        String correo = (String) tablaClientes.getValueAt(numDeRenglonSeleccionado, columnaCorreo);

        return new Cliente(id, nombre, direccion, telefono, correo);
    }


    private void btnRegresarVtnPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarVtnPrincipalActionPerformed
        // TODO add your handling code here:
        VtnPrincipal vtnRegreso = VtnPrincipal.getInstanciaDeVtnPrincipal();
        vtnRegreso.setVisible(true);
        cerrarEstaVentana();
    }//GEN-LAST:event_btnRegresarVtnPrincipalActionPerformed

    private void borrarCampos() {
        this.txtNombreCliente.setText("");
        limpiarTabla();

    }

    private void cerrarEstaVentana() {
        borrarCampos();
        this.dispose();
    }

    private void mostrarMensajeEnPantalla(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Cuidado", 0);
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
            java.util.logging.Logger.getLogger(VtnClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VtnClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VtnClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VtnClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VtnClientes().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnModificarCliente;
    private javax.swing.JButton btnRegresarVtnPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}
