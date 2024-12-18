/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;

import control.Control;
import dtos.EmpleadoDTO;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class FrmRecursosHumanos extends javax.swing.JFrame {

    private Control control;

    /**
     * Creates new form otroframe
     */
    public FrmRecursosHumanos() {
        initComponents();
        setLocationRelativeTo(null);
        control = new Control();
        llenarTablaEmpleados();
        agregarListenerSeleccionFila();
        configurarComboBoxCargo();

        // Inicializar el botón como "Agregar"
        BtnAgregarActualizarEmpleado.setText("Agregar");
    }

    private void llenarTablaEmpleados() {
        // Obtener la lista de empleados
        List<EmpleadoDTO> listaEmpleados = control.obtenerEmpleados();

        // Obtener el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) TableEmpleados.getModel();

        // Limpiar la tabla
        model.setRowCount(0);

        // Llenar la tabla con los datos de los empleados
        for (EmpleadoDTO empleado : listaEmpleados) {
            Object[] fila = new Object[3];
            fila[0] = empleado.getNombre();
            fila[1] = empleado.getCargo();
            fila[2] = empleado.getSalario();

            // Añadir la fila al modelo
            model.addRow(fila);
        }

        // Configurar la tabla para que no sea editable
        TableEmpleados.setDefaultEditor(Object.class, null); // Deshabilitar edición
        TableEmpleados.clearSelection();
    }

    private boolean modoAgregar = true;
    private boolean modoActualizar = false;

    private String empleadoSeleccionadoId;  // Variable para almacenar el nombre del empleado seleccionado

    private void agregarListenerSeleccionFila() {
        TableEmpleados.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = TableEmpleados.getSelectedRow();

                    if (row >= 0) {
                        DefaultTableModel model = (DefaultTableModel) TableEmpleados.getModel();
                        String nombre = model.getValueAt(row, 0).toString();
                        String cargo = model.getValueAt(row, 1).toString();
                        String salario = model.getValueAt(row, 2).toString();

                        // Establecer los valores en los campos
                        TxtfNombreEmpleado.setText(nombre);
                        CBXCargoEmpleado.setSelectedItem(cargo);
                        TxtfSalarioEmpleado.setText(salario);

                        // Guardar el nombre del empleado seleccionado para actualizar
                        empleadoSeleccionadoId = control.obtenerEmpleados().get(row).getId();

                        // Cambiar el texto del botón a "Actualizar"
                        BtnAgregarActualizarEmpleado.setText("Actualizar");
                        modoActualizar = true;
                        modoAgregar = false; // Cambio de modo
                    }
                }
            }
        });
    }

    // Configurar el JComboBox de Cargos
    private void configurarComboBoxCargo() {
        // Deshabilitar la edición del JTextField de salario
        TxtfSalarioEmpleado.setEditable(false);

        // Agregar un Listener para cambiar el salario según el cargo seleccionado
        CBXCargoEmpleado.addActionListener(evt -> actualizarSalarioPorCargo());
    }

    // Actualizar el salario dependiendo del cargo seleccionado
    private void actualizarSalarioPorCargo() {
        // Obtener el cargo seleccionado
        String cargo = (String) CBXCargoEmpleado.getSelectedItem();

        // Establecer el salario dependiendo del cargo con formato decimal
        DecimalFormat df = new DecimalFormat("#.00");  // Formato para 2 decimales
        switch (cargo) {
            case "Repartidor":
                TxtfSalarioEmpleado.setText(df.format(4000.00)); // 4000.00 con dos decimales
                break;
            case "Cajero":
                TxtfSalarioEmpleado.setText(df.format(2000.00)); // 2000.00 con dos decimales
                break;
            case "Panadero":
                TxtfSalarioEmpleado.setText(df.format(6000.00)); // 6000.00 con dos decimales
                break;
            default:
                TxtfSalarioEmpleado.setText("0.00");
                break;
        }
    }

    // Método para limpiar los campos de la interfaz
    private void limpiarCampos() {
        TxtfNombreEmpleado.setText("");         // Limpiar campo de nombre
        CBXCargoEmpleado.setSelectedIndex(0);   // Restablecer ComboBox a su primer valor
        TxtfSalarioEmpleado.setText("0.00");    // Restablecer salario a valor predeterminado
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
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableEmpleados = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        TxtfSalarioEmpleado = new javax.swing.JTextField();
        BtnAgregarActualizarEmpleado = new javax.swing.JButton();
        BtnRegresarMenu1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TxtfNombreEmpleado = new javax.swing.JTextField();
        BtnEliminarEmpleado = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        CBXCargoEmpleado = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 51, 0));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/image11.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Recursos Humanos:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Nombre:");

        TableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Cargo", "Salario"
            }
        ));
        jScrollPane1.setViewportView(TableEmpleados);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 0));
        jLabel5.setText("Salario:");

        TxtfSalarioEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtfSalarioEmpleadoActionPerformed(evt);
            }
        });

        BtnAgregarActualizarEmpleado.setText("Agregar");
        BtnAgregarActualizarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActualizarEmpleadoActionPerformed(evt);
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
        jLabel7.setText("Cargo:");

        TxtfNombreEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtfNombreEmpleadoActionPerformed(evt);
            }
        });

        BtnEliminarEmpleado.setText("Eliminar");
        BtnEliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarEmpleadoActionPerformed(evt);
            }
        });

        CBXCargoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Panadero", "Cajero", "Repartidor", " " }));
        CBXCargoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBXCargoEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CBXCargoEmpleado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TxtfNombreEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtfSalarioEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))
                            .addComponent(jLabel1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnAgregarActualizarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnEliminarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BtnRegresarMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TxtfNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(CBXCargoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(TxtfSalarioEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAgregarActualizarEmpleado)
                    .addComponent(BtnEliminarEmpleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnRegresarMenu1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAgregarActualizarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActualizarEmpleadoActionPerformed
        // TODO add your handling code here:
        // Obtener los datos de los campos
        String nombre = TxtfNombreEmpleado.getText().trim();
        String cargo = (String) CBXCargoEmpleado.getSelectedItem();
        String salarioText = TxtfSalarioEmpleado.getText().trim();

        // Validar si los campos están vacíos
        if (nombre.isEmpty() || cargo.isEmpty() || salarioText.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Por favor, llene todos los campos antes de agregar o actualizar el empleado.",
                    "Advertencia",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Intentar convertir el salario a tipo double
        double salario = 0;
        try {
            salario = Double.parseDouble(salarioText);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "El salario debe ser un valor numérico válido.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Modo Agregar
        if (modoAgregar) {
            EmpleadoDTO empleadoDTO = new EmpleadoDTO(nombre, cargo, salario);

            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea agregar al empleado?",
                    "Confirmación",
                    javax.swing.JOptionPane.YES_NO_OPTION,
                    javax.swing.JOptionPane.QUESTION_MESSAGE
            );

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                boolean exito = control.agregarEmpleado(empleadoDTO);
                if (exito) {
                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Empleado agregado correctamente.",
                            "Éxito",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );
                    llenarTablaEmpleados(); // Actualizar la tabla después de agregar
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Ocurrió un error al agregar al empleado. Intente nuevamente.",
                            "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } // Modo Actualizar
        else if (modoActualizar) {
            // Verificar que el ID del empleado seleccionado no sea nulo o vacío
            if (empleadoSeleccionadoId == null || empleadoSeleccionadoId.trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "No se ha seleccionado un empleado válido para actualizar.",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Crear el DTO del empleado con los datos nuevos
            EmpleadoDTO empleadoDTO = new EmpleadoDTO(empleadoSeleccionadoId, nombre, cargo, salario);

            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea actualizar al empleado?",
                    "Confirmación",
                    javax.swing.JOptionPane.YES_NO_OPTION,
                    javax.swing.JOptionPane.QUESTION_MESSAGE
            );

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                boolean exito = control.actualizarEmpleado(empleadoDTO);
                if (exito) {
                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Empleado actualizado correctamente.",
                            "Éxito",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );
                    llenarTablaEmpleados(); // Actualizar la tabla después de actualizar
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Ocurrió un error al actualizar al empleado. Intente nuevamente.",
                            "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }

        // Limpiar los campos después de la operación
        limpiarCampos();
    }//GEN-LAST:event_BtnAgregarActualizarEmpleadoActionPerformed

    private void BtnRegresarMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarMenu1ActionPerformed
        // Crear una nueva instancia de FrmMenu
        FrmMenu menu = new FrmMenu();
        // Hacer visible la ventana FrmMenu
        menu.setVisible(true);
        // Cerrar la ventana actual
        this.dispose();
    }//GEN-LAST:event_BtnRegresarMenu1ActionPerformed

    private void TxtfNombreEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtfNombreEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtfNombreEmpleadoActionPerformed

    private void TxtfSalarioEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtfSalarioEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtfSalarioEmpleadoActionPerformed

    private void CBXCargoEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBXCargoEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBXCargoEmpleadoActionPerformed

    private void BtnEliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarEmpleadoActionPerformed
        // TODO add your handling code here:

        // Verificar si los campos están llenos (empleado nuevo)
        boolean camposLlenos = !TxtfNombreEmpleado.getText().trim().isEmpty() && CBXCargoEmpleado.getSelectedIndex() != 0;

        // Verificar si hay un empleado seleccionado en la tabla
        boolean empleadoSeleccionado = TableEmpleados.getSelectedRow() != -1;

        if (camposLlenos && !empleadoSeleccionado) {
            // Caso 1: Campos llenos (empleado nuevo) y no hay selección en la tabla
            int opcion = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de querer limpiar los campos?",
                    "Confirmación de limpieza",
                    javax.swing.JOptionPane.YES_NO_OPTION
            );

            if (opcion == javax.swing.JOptionPane.YES_OPTION) {
                limpiarCampos();
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Los campos se han limpiado exitosamente.",
                        "Campos limpiados",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE
                );
            }
        } else if (empleadoSeleccionado) {
            // Caso 2: Se seleccionó un empleado de la tabla
            int opcion = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de querer eliminar al empleado seleccionado?",
                    "Confirmación de eliminación",
                    javax.swing.JOptionPane.YES_NO_OPTION
            );

            if (opcion == javax.swing.JOptionPane.YES_OPTION) {
                boolean exito = control.eliminarEmpleado(empleadoSeleccionadoId);

                if (exito) {
                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Empleado eliminado exitosamente.",
                            "Éxito",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );
                    llenarTablaEmpleados();

                    // Después de llenar la tabla, verificar si aún hay selección
                    if (TableEmpleados.getSelectedRow() == -1) {
                        // Cambiar a modo "Agregar" si no hay empleados seleccionados
                        modoAgregar = true;
                        modoActualizar = false;
                        BtnAgregarActualizarEmpleado.setText("Agregar");
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Ocurrió un error al eliminar al empleado. Intente nuevamente.",
                            "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }

                // Limpiar los campos después de la eliminación
                limpiarCampos();
            }
        } else {
            // Caso 3: No hay campos llenos ni empleado seleccionado
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No hay información para eliminar.",
                    "Aviso",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_BtnEliminarEmpleadoActionPerformed

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
//            java.util.logging.Logger.getLogger(FrmRecursosHumanos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmRecursosHumanos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmRecursosHumanos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmRecursosHumanos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmRecursosHumanos().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarActualizarEmpleado;
    private javax.swing.JButton BtnEliminarEmpleado;
    private javax.swing.JButton BtnRegresarMenu1;
    private javax.swing.JComboBox<String> CBXCargoEmpleado;
    private javax.swing.JTable TableEmpleados;
    private javax.swing.JTextField TxtfNombreEmpleado;
    private javax.swing.JTextField TxtfSalarioEmpleado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
