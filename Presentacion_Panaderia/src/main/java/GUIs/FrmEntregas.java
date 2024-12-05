package GUIs;

import control.Control;
import dtos.EmpleadoDTO;
import dtos.EntregaDTO;
import dtos.ProductoDTO;
import dtos.TiendaDTO;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class FrmEntregas extends javax.swing.JFrame {

    private final Control control;

    // Variables para los precios de los productos
    private final double precioDonas = 60.00;
    private final double precioEmpanadas = 50.00;
    private final double precioCoricos = 30.00;

    public FrmEntregas() {
        initComponents();
        setLocationRelativeTo(null);
        TxtfMontoTotal.setEditable(false); // Asegurarse de que el campo sea solo lectura
        control = new Control();  // Correctamente inicializada
        cargarTiendas();
        cargarRepartidores();
        cargarPaquetesDonas();
        cargarPaquetesEmpanadas();
        cargarPaquetesCoricos();
    }

    private void cargarRepartidores() {
        try {
            CBXRepartidores.removeAllItems();
            List<EmpleadoDTO> repartidores = control.obtenerRepartidores();
            for (EmpleadoDTO repartidor : repartidores) {
                CBXRepartidores.addItem(repartidor.getNombre());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los repartidores: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarTiendas() {
        try {
            CBXTiendas.removeAllItems();

            // Llamada a la capa de negocio (Control) para obtener los DTOs
            List<TiendaDTO> tiendas = control.obtenerTiendas();

            // Solo manejas DTOs en la capa de presentación
            for (TiendaDTO tienda : tiendas) {
                CBXTiendas.addItem(tienda.getNombre()); // Agregar nombre al JComboBox
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las tiendas: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarPaquetesDonas() {
        try {
            String idProducto = "6744fdd889a0633b2194eb6a"; // ID del producto "Donas"
            int paquetesDisponibles = control.calcularPaquetesDisponibles(idProducto);

            // Limpiar el JComboBox antes de cargar los nuevos items
            CBXCantidadPaqueteDonas.removeAllItems();

            // Agregar el valor predeterminado (opcional)
            CBXCantidadPaqueteDonas.addItem("Ninguno");

            // Agregar los elementos del JComboBox según los paquetes disponibles
            if (paquetesDisponibles > 0) {
                for (int i = 1; i <= paquetesDisponibles; i++) {
                    CBXCantidadPaqueteDonas.addItem(String.valueOf(i));
                }

                // Establecer el valor seleccionado predeterminado si hay elementos
                CBXCantidadPaqueteDonas.setSelectedIndex(0); // Establecer el primer elemento como seleccionado
            } else {
                // Si no hay paquetes disponibles, mostrar un mensaje
                JOptionPane.showMessageDialog(this, "No hay paquetes disponibles.");
            }
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir
            JOptionPane.showMessageDialog(this, "Error al cargar los paquetes disponibles: " + e.getMessage());
        }
    }

    private void cargarPaquetesEmpanadas() {
        try {
            String idProducto = "6744fdd889a0633b2194eb6b"; // ID del producto "Empanadas"
            int paquetesDisponibles = control.calcularPaquetesDisponibles(idProducto);  // Este método debe obtener la cantidad de paquetes de Empanadas disponibles

            CBXCantidadPaqueteEmpanadas.removeAllItems();  // Limpiar el ComboBox

            CBXCantidadPaqueteEmpanadas.addItem("Ninguno");  // Agregar valor predeterminado

            if (paquetesDisponibles > 0) {
                for (int i = 1; i <= paquetesDisponibles; i++) {
                    CBXCantidadPaqueteEmpanadas.addItem(String.valueOf(i));  // Llenar ComboBox con las cantidades disponibles
                }

                CBXCantidadPaqueteEmpanadas.setSelectedIndex(0);  // Establecer el valor seleccionado
            } else {
                JOptionPane.showMessageDialog(this, "No hay paquetes disponibles de Empanadas.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los paquetes de Empanadas: " + e.getMessage());
        }
    }

    private void cargarPaquetesCoricos() {
        try {
            String idProducto = "6744fdd889a0633b2194eb6c"; // Reemplaza con el ID real de Coricos
            int paquetesDisponibles = control.calcularPaquetesDisponibles(idProducto);

            // Limpiar el JComboBox antes de cargar los nuevos items
            CBXCantidadPaqueteCoricos.removeAllItems();

            // Agregar el valor predeterminado (opcional)
            CBXCantidadPaqueteCoricos.addItem("Ninguno");

            // Agregar los elementos del JComboBox según los paquetes disponibles
            if (paquetesDisponibles > 0) {
                for (int i = 1; i <= paquetesDisponibles; i++) {
                    CBXCantidadPaqueteCoricos.addItem(String.valueOf(i));
                }

                // Establecer el valor seleccionado predeterminado si hay elementos
                CBXCantidadPaqueteCoricos.setSelectedIndex(0); // Establecer el primer elemento como seleccionado
            } else {
                // Si no hay paquetes disponibles, mostrar un mensaje
                JOptionPane.showMessageDialog(this, "No hay paquetes disponibles de Coricos.");
            }
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir
            JOptionPane.showMessageDialog(this, "Error al cargar los paquetes disponibles de Coricos: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CBXCantidadPaqueteDonas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        CBXCantidadPaqueteEmpanadas = new javax.swing.JComboBox<>();
        CBXCantidadPaqueteCoricos = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        CBXTiendas = new javax.swing.JComboBox<>();
        BtnConfirmarEntrega = new javax.swing.JButton();
        BtnRegresarMenu = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        CBXRepartidores = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        TxtfMontoTotal = new javax.swing.JTextField();

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/image11.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 0));
        jLabel1.setText("Buscar Producto:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 51, 0));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/image11.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Entregas:");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imageDona.png"))); // NOI18N

        CBXCantidadPaqueteDonas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "1 Paquete", "2 Paquetes", "3 Paquetes", "4 Paquetes", "5 Paquetes", "6 Paquetes", "7 Paquetes", "8 Paquetes", "9 Paquetes", "10 Paquetes" }));
        CBXCantidadPaqueteDonas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBXCantidadPaqueteDonasActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("Paquete Donas (Cant. 6)");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imageEmpanada.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setText("Paquete Empanadas (Cant. 18)");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imageCorico.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 0));
        jLabel10.setText("Paquete Coricos (Cant. 12)");

        CBXCantidadPaqueteEmpanadas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "1 Paquete", "2 Paquetes", "3 Paquetes", "4 Paquetes", "5 Paquetes", "6 Paquetes", "7 Paquetes", "8 Paquetes", "9 Paquetes", "10 Paquetes" }));
        CBXCantidadPaqueteEmpanadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBXCantidadPaqueteEmpanadasActionPerformed(evt);
            }
        });

        CBXCantidadPaqueteCoricos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "1 Paquete", "2 Paquetes", "3 Paquetes", "4 Paquetes", "5 Paquetes", "6 Paquetes", "7 Paquetes", "8 Paquetes", "9 Paquetes", "10 Paquetes" }));
        CBXCantidadPaqueteCoricos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBXCantidadPaqueteCoricosActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 0));
        jLabel11.setText("Seleccione tienda a surtir:");

        CBXTiendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBXTiendasActionPerformed(evt);
            }
        });

        BtnConfirmarEntrega.setText("Confirmar Entrega");
        BtnConfirmarEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConfirmarEntregaActionPerformed(evt);
            }
        });

        BtnRegresarMenu.setText("Regresar al Menú");
        BtnRegresarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegresarMenuActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 0));
        jLabel12.setText("Seleccione al repartidor:");

        CBXRepartidores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBXRepartidoresActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 0));
        jLabel13.setText("Monto Total:");

        TxtfMontoTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtfMontoTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CBXCantidadPaqueteDonas, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(CBXCantidadPaqueteEmpanadas, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(CBXCantidadPaqueteCoricos, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnRegresarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnConfirmarEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13)
                            .addComponent(CBXTiendas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CBXRepartidores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtfMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(CBXCantidadPaqueteDonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBXCantidadPaqueteEmpanadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBXCantidadPaqueteCoricos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBXTiendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBXRepartidores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(TxtfMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BtnConfirmarEntrega)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnRegresarMenu)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRegresarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarMenuActionPerformed
        // Crear una nueva instancia de FrmMenu
        FrmMenu menu = new FrmMenu();
        // Hacer visible la ventana FrmMenu
        menu.setVisible(true);
        // Cerrar la ventana actual
        this.dispose();
    }//GEN-LAST:event_BtnRegresarMenuActionPerformed

    private void CBXCantidadPaqueteDonasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBXCantidadPaqueteDonasActionPerformed
        // TODO add your handling code here:
        // Verifica si hay un valor seleccionado
        if (CBXCantidadPaqueteDonas.getSelectedItem() != null) {
            // Obtener la cantidad de paquetes seleccionados de Donas
            String donasSeleccionadas = CBXCantidadPaqueteDonas.getSelectedItem().toString();

            // Actualizar el monto total
            calcularMontoTotal();
        } else {
            // Si no hay valor seleccionado, no hacer nada o manejar el caso según sea necesario
        }
    }//GEN-LAST:event_CBXCantidadPaqueteDonasActionPerformed

    private void CBXCantidadPaqueteEmpanadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBXCantidadPaqueteEmpanadasActionPerformed
        // TODO add your handling code here:
        // Verifica si hay un valor seleccionado
        if (CBXCantidadPaqueteEmpanadas.getSelectedItem() != null) {
            // Obtener la cantidad de paquetes seleccionados de Empanadas
            String empanadasSeleccionadas = CBXCantidadPaqueteEmpanadas.getSelectedItem().toString();

            // Actualizar el monto total
            calcularMontoTotal();
        } else {
            // Si no hay valor seleccionado, no hacer nada o manejar el caso según sea necesario
        }
    }//GEN-LAST:event_CBXCantidadPaqueteEmpanadasActionPerformed

    private void CBXCantidadPaqueteCoricosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBXCantidadPaqueteCoricosActionPerformed
        // TODO add your handling code here:
        // Verifica si hay un valor seleccionado
        if (CBXCantidadPaqueteCoricos.getSelectedItem() != null) {
            // Obtener la cantidad de paquetes seleccionados de Coricos
            String coricosSeleccionados = CBXCantidadPaqueteCoricos.getSelectedItem().toString();

            // Actualizar el monto total
            calcularMontoTotal();
        } else {
            // Si no hay valor seleccionado, no hacer nada o manejar el caso según sea necesario
        }
    }//GEN-LAST:event_CBXCantidadPaqueteCoricosActionPerformed

    private void CBXTiendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBXTiendasActionPerformed
        // TODO add your handling code here:
        String tiendaSeleccionada = CBXTiendas.getSelectedItem().toString();
    }//GEN-LAST:event_CBXTiendasActionPerformed

    private void BtnConfirmarEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConfirmarEntregaActionPerformed

        try {
            // Obtener los valores seleccionados de los combo boxes
            String donasSeleccionadas = CBXCantidadPaqueteDonas.getSelectedItem().toString();
            String empanadasSeleccionadas = CBXCantidadPaqueteEmpanadas.getSelectedItem().toString();
            String coricosSeleccionados = CBXCantidadPaqueteCoricos.getSelectedItem().toString();

            // Verificar si todos los productos están en "Ninguno"
            if (donasSeleccionadas.equals("Ninguno") && empanadasSeleccionadas.equals("Ninguno") && coricosSeleccionados.equals("Ninguno")) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona al menos un producto antes de confirmar la entrega.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Obtener el nombre de la tienda seleccionada
            String nombreTiendaSeleccionada = CBXTiendas.getSelectedItem().toString().trim();

            // Buscar el ID de la tienda a partir del nombre
            String idTiendaSeleccionada = control.obtenerIdTiendaPorNombre(nombreTiendaSeleccionada);

            if (idTiendaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "No se pudo identificar la tienda seleccionada.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("No se encontró la tienda: " + nombreTiendaSeleccionada);
                return;
            }

            // Obtener el nombre del repartidor seleccionado
            String nombreRepartidorSeleccionado = CBXRepartidores.getSelectedItem() != null ? CBXRepartidores.getSelectedItem().toString().trim() : null;

            if (nombreRepartidorSeleccionado == null || nombreRepartidorSeleccionado.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe asignarse un repartidor a la entrega.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String montoTotal = TxtfMontoTotal.getText();

            // Crear un resumen del pedido
            String resumen = "Resumen del pedido:\n"
                    + "Donas: " + donasSeleccionadas + " paquetes\n"
                    + "Empanadas: " + empanadasSeleccionadas + " paquetes\n"
                    + "Coricos: " + coricosSeleccionados + " paquetes\n"
                    + "Tienda: " + nombreTiendaSeleccionada + "\n"
                    + "Repartidor: " + nombreRepartidorSeleccionado + "\n"
                    + "Monto Total: $" + montoTotal + "\n\n"
                    + "¿Deseas confirmar el pedido?";

            // Mostrar el resumen y pedir confirmación
            int respuesta = JOptionPane.showConfirmDialog(this, resumen, "Confirmar Pedido", JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                // Crear EntregaDTO
                EntregaDTO entregaDTO = new EntregaDTO();

                // Convertir la fecha UTC a hora local
                Date fechaUTC = new Date(); // Fecha actual en UTC
                ZonedDateTime utcDateTime = fechaUTC.toInstant().atZone(ZoneId.of("UTC"));
                ZonedDateTime localDateTime = utcDateTime.withZoneSameInstant(ZoneId.systemDefault()).plusHours(1);

                // Asignar la fecha local
                entregaDTO.setFechaEntrega(Date.from(localDateTime.toInstant()));
                // Imprimir la fecha local en consola
                System.out.println("La hora de entrega es: " + localDateTime);

                entregaDTO.setMontoTotal(Double.parseDouble(montoTotal));
                entregaDTO.setIdTienda(idTiendaSeleccionada.toString());

                // Obtener los repartidores desde el controlador
                List<EmpleadoDTO> repartidores = control.obtenerRepartidores();

                // Buscar el repartidor por nombre
                EmpleadoDTO repartidorDTO = null;
                for (EmpleadoDTO repartidor : repartidores) {
                    if (repartidor.getNombre().trim().equalsIgnoreCase(nombreRepartidorSeleccionado)) {
                        repartidorDTO = repartidor;
                        break;
                    }
                }

                // Validar que se haya encontrado el repartidor
                if (repartidorDTO == null) {
                    JOptionPane.showMessageDialog(this, "Repartidor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Asignar el repartidor al DTO de entrega
                entregaDTO.setRepartidor(repartidorDTO);

                // Asignar productos seleccionados al DTO
                if (!donasSeleccionadas.equals("Ninguno")) {
                    entregaDTO.getProductos().add(new ProductoDTO("Donas", Integer.parseInt(donasSeleccionadas), precioDonas, "Donas de 1 sabor de cubierta, sencillas"));
                    control.desinventariar("1", Integer.parseInt(donasSeleccionadas));
                }
                if (!empanadasSeleccionadas.equals("Ninguno")) {
                    entregaDTO.getProductos().add(new ProductoDTO("Empanadas", Integer.parseInt(empanadasSeleccionadas), precioEmpanadas, "Empanadas rellenas de diferentes sabores"));
                    control.desinventariar("2", Integer.parseInt(donasSeleccionadas));
                }
                if (!coricosSeleccionados.equals("Ninguno")) {
                    entregaDTO.getProductos().add(new ProductoDTO("Coricos", Integer.parseInt(coricosSeleccionados), precioCoricos, "Coricos tradicionales de maíz con toque de sal"));
                    control.desinventariar("3", Integer.parseInt(donasSeleccionadas));
                }

                // Registrar la entrega usando el controlador
                String idEntrega = control.registrarEntrega(entregaDTO);

                // Programar el cambio de estado en una hora
                control.programarCambioEstado(idEntrega);

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "¡Pedido registrado exitosamente!");

                // Preguntar si desea agregar otro pedido
                int agregarOtro = JOptionPane.showConfirmDialog(this, "¿Deseas agregar otro pedido?", "Nuevo Pedido", JOptionPane.YES_NO_OPTION);

                if (agregarOtro == JOptionPane.YES_OPTION) {
                    // Limpiar los campos de los productos y monto total
                    CBXCantidadPaqueteDonas.setSelectedIndex(0);  // Ninguno
                    CBXCantidadPaqueteEmpanadas.setSelectedIndex(0);  // Ninguno
                    CBXCantidadPaqueteCoricos.setSelectedIndex(0);  // Ninguno
                    TxtfMontoTotal.setText("0.00");  // Establecer monto total a 0.00
                } else {
                    // Si el usuario selecciona NO, redirigir al menú principal
                    redirigirAlMenu();
                }
            } else {
                this.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el pedido: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

// Método para redirigir al menú principal
    private void redirigirAlMenu() {
        // Crear una nueva instancia de FrmMenu
        FrmMenu menu = new FrmMenu();
        // Hacer visible la ventana FrmMenu
        menu.setVisible(true);
        // Cerrar la ventana actual
        this.dispose();
    }

    private void calcularMontoTotal() {
        double montoTotal = 0.0;

        // Obtener la cantidad seleccionada de cada ComboBox
        String donasSeleccionadas = CBXCantidadPaqueteDonas.getSelectedItem().toString();
        String empanadasSeleccionadas = CBXCantidadPaqueteEmpanadas.getSelectedItem().toString();
        String coricosSeleccionados = CBXCantidadPaqueteCoricos.getSelectedItem().toString();

        // Convertir las selecciones en números
        int cantidadDonas = obtenerCantidad(donasSeleccionadas);
        int cantidadEmpanadas = obtenerCantidad(empanadasSeleccionadas);
        int cantidadCoricos = obtenerCantidad(coricosSeleccionados);

        // Calcular el monto total
        montoTotal += cantidadDonas * precioDonas;
        montoTotal += cantidadEmpanadas * precioEmpanadas;
        montoTotal += cantidadCoricos * precioCoricos;

        // Mostrar el monto total en el TextField (solo lectura)
        TxtfMontoTotal.setText(String.format("%.2f", montoTotal));
    }

    private int obtenerCantidad(String seleccion) {
        if (seleccion.equals("Ninguno")) {
            return 0;
        } else {
            // Extraer el número de la cadena (ej: "3 Paquetes" -> 3)
            return Integer.parseInt(seleccion.split(" ")[0]);
        }

    }//GEN-LAST:event_BtnConfirmarEntregaActionPerformed

    private void CBXRepartidoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBXRepartidoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBXRepartidoresActionPerformed

    private void TxtfMontoTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtfMontoTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtfMontoTotalActionPerformed

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
            java.util.logging.Logger.getLogger(FrmEntregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEntregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEntregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEntregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEntregas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnConfirmarEntrega;
    private javax.swing.JButton BtnRegresarMenu;
    private javax.swing.JComboBox<String> CBXCantidadPaqueteCoricos;
    private javax.swing.JComboBox<String> CBXCantidadPaqueteDonas;
    private javax.swing.JComboBox<String> CBXCantidadPaqueteEmpanadas;
    private javax.swing.JComboBox<String> CBXRepartidores;
    private javax.swing.JComboBox<String> CBXTiendas;
    private javax.swing.JTextField TxtfMontoTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
