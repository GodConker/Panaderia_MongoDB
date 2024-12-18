package GUIs;

import control.Control;
import dtos.InventarioDTO;
import dtos.ProductoDTO;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FrmInventario extends javax.swing.JFrame {

    private Control control;

    /**
     * Creates new form FrmInventario
     */
    public FrmInventario() {
        initComponents();
        setLocationRelativeTo(null);
        control = new Control(); // Inicializamos el control
        llenarTablaInventario(); // Llamamos al método para llenar la tabla
        agregarListenerSeleccionFila();
    }

    // Método para llenar la tabla con los productos
    private void llenarTablaInventario() {
        // Obtener la lista de productos de la base de datos
        List<ProductoDTO> listaProductos = control.obtenerProductos();

        // Obtener el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) TableProductos.getModel(); // Usamos TableProductos

        // Limpiar la tabla antes de llenarla
        model.setRowCount(0);

        // Llenar la tabla con los datos de los productos
        for (ProductoDTO producto : listaProductos) {
            Object[] fila = new Object[4]; // 4 columnas: Nombre, Precio, Descripción, Cantidad
            fila[0] = producto.getNombre(); // Nombre del producto
            fila[1] = producto.getPrecio(); // Precio del producto
            fila[2] = control.obtenerCantidadDisponible(producto.getId());
            fila[3] = producto.getDescripcion();

            // Añadir la fila al modelo
            model.addRow(fila);
        }

        // Configurar la tabla para que no sea editable
        TableProductos.setDefaultEditor(Object.class, null); // Deshabilitar edición
        TableProductos.clearSelection(); // Limpiar la selección
    }

    private void limpiarCampos() {
        TxtfCantidad.setText("");
        TxtfPrecio.setText("");
        TxtfDescripcion.setText("");
    }
    
    private String idInventario;
    private String idProducto;
    
    private void agregarListenerSeleccionFila() {
        TableProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = TableProductos.getSelectedRow();

                    if (row >= 0) {
                        DefaultTableModel model = (DefaultTableModel) TableProductos.getModel();
                        String cantidad = model.getValueAt(row, 2).toString();
                        String precio = model.getValueAt(row, 1).toString();
                        String descripcion = model.getValueAt(row, 3).toString();

                        idInventario = String.valueOf(row + 1);
                        idProducto = control.obtenerProductos().get(row).getId();
                        
                        // Establecer los valores en los campos
                        TxtfCantidad.setText(cantidad);
                        TxtfPrecio.setText(precio);
                        TxtfDescripcion.setText(descripcion);
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableProductos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        TxtfPrecio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtfDescripcion = new javax.swing.JTextField();
        BtnCancelar = new javax.swing.JButton();
        BtnActualizar = new javax.swing.JButton();
        BtnRegresarMenu1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TxtfCantidad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 51, 0));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/image11.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Inventario:");

        TableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Precio", "Cantidad", "Descripción"
            }
        ));
        jScrollPane1.setViewportView(TableProductos);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 0));
        jLabel5.setText("Precio:");

        TxtfPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtfPrecioActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("Descripción:");

        TxtfDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtfDescripcionActionPerformed(evt);
            }
        });

        BtnCancelar.setText("Cancelar");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        BtnActualizar.setText("Actualizar");
        BtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarActionPerformed(evt);
            }
        });

        BtnRegresarMenu1.setText("Regresar al Menú");
        BtnRegresarMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegresarMenu1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("Cantidad:");

        TxtfCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtfCantidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(TxtfDescripcion)
                                            .addComponent(TxtfPrecio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(jLabel2)
                        .addGap(0, 333, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnRegresarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(262, 262, 262))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(TxtfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(TxtfPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(TxtfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnCancelar)
                            .addComponent(BtnActualizar))))
                .addGap(18, 18, 18)
                .addComponent(BtnRegresarMenu1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtfPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtfPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtfPrecioActionPerformed

    private void TxtfDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtfDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtfDescripcionActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
        String cantidad = TxtfCantidad.getText().trim();
        String precio = TxtfPrecio.getText().trim();
        String descripcion = TxtfDescripcion.getText().trim();
        
        if (idProducto == null || idProducto.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se ha seleccionado un producto válido para actualizar.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Crear el DTO del empleado con los datos nuevos
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(idProducto);
        productoDTO.setPrecio(Double.parseDouble(precio));
        productoDTO.setDescripcion(descripcion);
        
        InventarioDTO inventarioDTO = new InventarioDTO();
        inventarioDTO.setId(idInventario);
        inventarioDTO.setCantidadDisponible(Integer.parseInt(cantidad));

        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea actualizar el inventario?",
                "Confirmación",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
            boolean exito = control.actualizarProducto(productoDTO);
            boolean exito2 = control.actualizarInventario(inventarioDTO);
            if (exito && exito2) {
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Inventario actualizado correctamente.",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE
                );
                llenarTablaInventario(); // Actualizar la tabla después de actualizar
            } else {
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Ocurrió un error al actualizar el inventario. Intente nuevamente.",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void BtnRegresarMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarMenu1ActionPerformed
        // Crear una nueva instancia de FrmMenu
        FrmMenu menu = new FrmMenu();
        // Hacer visible la ventana FrmMenu
        menu.setVisible(true);
        // Cerrar la ventana actual
        this.dispose();
    }//GEN-LAST:event_BtnRegresarMenu1ActionPerformed

    private void TxtfCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtfCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtfCantidadActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmInventario().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnRegresarMenu1;
    private javax.swing.JTable TableProductos;
    private javax.swing.JTextField TxtfCantidad;
    private javax.swing.JTextField TxtfDescripcion;
    private javax.swing.JTextField TxtfPrecio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
