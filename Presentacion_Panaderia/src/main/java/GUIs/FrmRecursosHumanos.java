/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;

import control.Control;
import dtos.EmpleadoDTO;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class FrmRecursosHumanos extends javax.swing.JFrame {

    private final Control control; // Crear el controlador
    private boolean esActualizacion = false; // Estado del botón (Agregar/Actualizar)

    public FrmRecursosHumanos() {
        initComponents();
        setLocationRelativeTo(null);

        // Inicializar el Control, que maneja la capa de negocio
        control = new Control();

        // Crear un modelo de tabla básico con los nombres de las columnas (sin "ID")
        String[] columnNames = {"Nombre", "Puesto", "Salario"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);

        // Asignar el modelo al JTable
        TableEmpleados.setModel(model);

        // Deshabilitar la edición en la tabla
        TableEmpleados.setDefaultEditor(Object.class, null);

        // Cargar los empleados en la tabla al iniciar el formulario
        cargarEmpleadosEnTabla(control);

        // Agregar un MouseListener a la tabla para seleccionar un empleado
        TableEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = TableEmpleados.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener la información del empleado seleccionado
                    String nombre = (String) TableEmpleados.getValueAt(selectedRow, 0);
                    String cargo = (String) TableEmpleados.getValueAt(selectedRow, 1);
                    double salario = (Double) TableEmpleados.getValueAt(selectedRow, 2);

                    // Mostrar la información en los TextFields
                    TxtfNombreEmpleado.setText(nombre);
                    CBXCargoEmpleado.setSelectedItem(cargo);
                    TxtfSalarioEmpleado.setText(String.valueOf(salario));

                    // Cambiar el botón a "Actualizar"
                    BtnAgregarActualizarEmpleado.setText("Actualizar");
                    esActualizacion = true; // Cambiar estado
                }
            }
        });

        // Hacer el TextField de salario no editable
        TxtfSalarioEmpleado.setEditable(false); // Esto lo hace de solo lectura

        // Acción del ComboBox para actualizar el salario según el cargo seleccionado
        CBXCargoEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el cargo seleccionado
                String cargoSeleccionado = (String) CBXCargoEmpleado.getSelectedItem();

                // Asignar el salario según el cargo seleccionado
                double salario = obtenerSalarioPorCargo(cargoSeleccionado);

                // Actualizar el salario en el TextField
                TxtfSalarioEmpleado.setText(String.valueOf(salario));
            }
        });

        // Acción del botón para agregar/actualizar empleados
        BtnAgregarActualizarEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (esActualizacion) {
                    actualizarEmpleado();
                } else {
                    agregarEmpleado();
                }
            }
        });
    }

    private double obtenerSalarioPorCargo(String cargo) {
        switch (cargo.toLowerCase()) {
            case "repartidor":
                return 6000.0;
            case "cajero":
                return 3000.0;
            case "panadero":
                return 8000.0;
            default:
                return 0.0;
        }
    }

    private void cargarEmpleadosEnTabla(Control control) {
        try {
            // Obtener los empleados desde el Control (que maneja la capa de negocio)
            List<EmpleadoDTO> empleadosDTO = control.obtenerRepartidores();

            // Obtener el modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) TableEmpleados.getModel();

            // Limpiar la tabla antes de cargar nuevos datos
            model.setRowCount(0);

            // Recorrer la lista de empleados y agregar cada uno a la tabla
            for (EmpleadoDTO empleadoDTO : empleadosDTO) {
                Object[] row = new Object[3]; // Ahora solo tenemos 3 columnas: Nombre, Puesto, Salario

                row[0] = empleadoDTO.getNombre();
                row[1] = empleadoDTO.getCargo();
                row[2] = empleadoDTO.getSalario(); // El salario ya está en el DTO

                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los empleados: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarEmpleado() {
        String nombre = TxtfNombreEmpleado.getText().trim();
        String cargo = (String) CBXCargoEmpleado.getSelectedItem();
        String salarioText = TxtfSalarioEmpleado.getText().trim();

        // Validar si los campos no están vacíos
        if (nombre.isEmpty() || salarioText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar si el salario es un número válido
        double salario;
        try {
            salario = Double.parseDouble(salarioText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El salario debe ser un valor numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Llamada al control para agregar el empleado
            control.agregarEmpleado(new EmpleadoDTO(nombre, cargo, salario));
            JOptionPane.showMessageDialog(this, "Empleado agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarEmpleadosEnTabla(control);
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   private void actualizarEmpleado() {
    int selectedRow = TableEmpleados.getSelectedRow();
    if (selectedRow != -1) {
        // Obtener los datos del empleado seleccionado
        String nombreOriginal = (String) TableEmpleados.getValueAt(selectedRow, 0);
        String nombre = TxtfNombreEmpleado.getText();
        String cargo = (String) CBXCargoEmpleado.getSelectedItem();
        double salario = Double.parseDouble(TxtfSalarioEmpleado.getText());

        // Mostrar un cuadro de confirmación antes de proceder con la actualización
        int opcion = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de que deseas actualizar este empleado?", 
                "Confirmación de actualización", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            try {
                // Llamar al método de control para actualizar el empleado
                control.actualizarEmpleado(nombreOriginal, new EmpleadoDTO(nombre, cargo, salario));
                JOptionPane.showMessageDialog(this, "Empleado actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarEmpleadosEnTabla(control);
                limpiarCampos();

                // Cambiar el botón a "Agregar" nuevamente
                BtnAgregarActualizarEmpleado.setText("Agregar");
                esActualizacion = false;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Si el usuario selecciona "No", mostrar mensaje y limpiar campos
            JOptionPane.showMessageDialog(this, "Actualización cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();  // Limpiar los campos después de cancelar
        }
    }
}



    private void limpiarCampos() {
        TxtfNombreEmpleado.setText("");
        CBXCargoEmpleado.setSelectedIndex(0);
        TxtfSalarioEmpleado.setText("");
        TableEmpleados.clearSelection();
        BtnAgregarActualizarEmpleado.setText("Agregar");
        esActualizacion = false; // Restablecer estado
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
   // Obtener la fila seleccionada en la tabla
    int selectedRow = TableEmpleados.getSelectedRow();
    
    // Verificar si se seleccionó un empleado
    if (selectedRow != -1) {
        String nombre = (String) TableEmpleados.getValueAt(selectedRow, 0);
        
        // Mostrar un cuadro de confirmación antes de eliminar
        int opcion = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de que deseas eliminar al empleado " + nombre + "?", 
                "Confirmación de eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            try {
                // Llamar al método de control para eliminar el empleado por nombre
                control.eliminarEmpleadoPorNombre(nombre);
                
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "Empleado eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                // Recargar los empleados en la tabla
                cargarEmpleadosEnTabla(control);
                
                // Limpiar los campos
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Si el usuario selecciona "No", mostrar mensaje y li-9mpiar campos
            JOptionPane.showMessageDialog(this, "Eliminación cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();  // Limpiar los campos después de cancelar
        }
    } else {
        // Si no se ha seleccionado ninguna fila
        JOptionPane.showMessageDialog(this, "Por favor, selecciona un empleado para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_BtnEliminarEmpleadoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmRecursosHumanos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRecursosHumanos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRecursosHumanos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRecursosHumanos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRecursosHumanos().setVisible(true);
            }
        });
    }

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
