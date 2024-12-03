/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import control.Control;
import dtos.EntregaDTO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dtos.ProductoDTO;
import java.io.FileOutputStream;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class FrmReporteVenta extends javax.swing.JFrame {

    private final Control control;
    private final List<EntregaDTO> entregas = new ArrayList<>(); // Variable global

    /**
     * Creates new form FrmReporteVenta
     */
    public FrmReporteVenta() {

        initComponents();
        control = new Control(); // Inicializa el objeto Control
        cargarEntregasEnTabla(entregas);

        // Crear el objeto DatePickerSettings para DPFecha (Fecha Desde)
        DatePickerSettings datePickerSettings = new DatePickerSettings();

        // Configurar DPFecha (Fecha Desde) para permitir solo fechas de hace 2 semanas hasta mañana
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1); // Día de mañana
        LocalDate twoWeeksAgo = today.minus(2, ChronoUnit.WEEKS); // Hace 2 semanas

        // Crear el componente DatePicker usando los ajustes configurados
        DPFecha = new DatePicker(datePickerSettings);
        DPFecha.getSettings().setDateRangeLimits(twoWeeksAgo, tomorrow); // Establecer el rango de fechas para DPFecha

        // Crear el objeto DatePickerSettings para DPFecha1 (Fecha Hasta)
        DatePickerSettings datePickerSettings1 = new DatePickerSettings();

        // Limitar la fecha hasta mañana (solo se puede seleccionar hasta mañana)
        DPFecha1 = new DatePicker(datePickerSettings1);
        DPFecha1.getSettings().setDateRangeLimits(today, tomorrow); // Establecer el límite superior para DPFecha1
    }

    // Método para cargar las entregas en la tabla
    private void cargarEntregasEnTabla(List<EntregaDTO> entregas) {
        DefaultTableModel model = (DefaultTableModel) TablaReporteVenta.getModel();
        model.setRowCount(0); // Limpiar filas existentes

        for (EntregaDTO entrega : entregas) {
            // Obtener el nombre de la tienda
            String nombreTienda = control.obtenerNombreTiendaPorId(entrega.getIdTienda());  // Ahora obtienes el nombre de la tienda

            // Mostrar los productos y cantidades en la tabla
            StringBuilder productosString = new StringBuilder();
            StringBuilder cantidadesString = new StringBuilder();

            for (int i = 0; i < entrega.getProductos().size(); i++) {
                if (i > 0) {
                    productosString.append(", ");
                    cantidadesString.append(", ");
                }
                productosString.append(entrega.getProductos().get(i).getNombre());
                cantidadesString.append(entrega.getCantidades().get(i));  // Asumiendo que las cantidades están bien asociadas
            }

            Object[] row = new Object[4]; // Ajusta las columnas según tus necesidades
            row[0] = nombreTienda;  // Mostrar el nombre de la tienda en lugar del ID
            row[1] = productosString.toString();  // Mostrar los productos como una lista separada por comas
            row[2] = cantidadesString.toString();  // Mostrar las cantidades como una lista separada por comas
            row[3] = entrega.getMontoTotal();  // El monto total de la entrega

            model.addRow(row);
        }
    }

    private void buscarEntregas() {
        try {
            // Obtener las fechas desde los DatePickers
            LocalDate fechaInicio = DPFecha.getDate();  // Fecha Desde (2 semanas atrás)
            LocalDate fechaFin = DPFecha1.getDate();    // Fecha Hasta (Hoy)

            // Calcular el día de mañana
            LocalDate tomorrow = LocalDate.now().plusDays(1); // Día de mañana

            // Si no se selecciona ninguna fecha, se asignan valores por defecto
            if (fechaInicio == null) {
                fechaInicio = LocalDate.now().minusWeeks(2);  // 2 semanas atrás
            }

            if (fechaFin == null) {
                fechaFin = tomorrow;  // Mañana
            }

            // Convertir las fechas a tipo Date
            Date fechaInicioDate = Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date fechaFinDate = Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Mostrar las fechas en consola para depuración
            System.out.println("Buscando entregas entre: " + fechaInicioDate + " y " + fechaFinDate);

            // Llamar al método en Control para obtener las entregas dentro del rango
            entregas.clear(); // Limpiar la lista global
            entregas.addAll(control.obtenerEntregasPorFecha(fechaInicioDate, fechaFinDate));

            // Mostrar las entregas obtenidas en consola (opcional)
            System.out.println("Entregas obtenidas: " + entregas.size());
            for (EntregaDTO entrega : entregas) {
                System.out.println(entrega);
            }

            // Verificar que la lista de entregas no esté vacía antes de cargarla en la tabla
            if (!entregas.isEmpty()) {
                System.out.println("Cargando " + entregas.size() + " entregas en la tabla.");
                cargarEntregasEnTabla(entregas);
            } else {
                System.out.println("No se encontraron entregas para mostrar.");
            }

        } catch (Exception e) {
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaReporteVenta = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        BtnRegresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        BtnGenerarReporte = new javax.swing.JButton();
        BtnExportarPDF = new javax.swing.JButton();
        DPFecha = new com.github.lgooddatepicker.components.DatePicker();
        DPFecha1 = new com.github.lgooddatepicker.components.DatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 51, 0));

        jLabel1.setText("Reporte de Ventas");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));

        TablaReporteVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", "", null},
                {"", "", "", null},
                {null, null, null, null}
            },
            new String [] {
                "Tienda", "Producto", "Cantidad (Paquetes)", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaReporteVenta);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/image11.png"))); // NOI18N

        BtnRegresar.setText("Regresar al menú");
        BtnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegresarActionPerformed(evt);
            }
        });

        jLabel2.setText("Filtrar por Fecha:");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));

        jLabel4.setText("Desde:");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));

        jLabel5.setText("Hasta:");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 0));

        jLabel6.setText("Tabla de Entregas:");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 0));

        BtnGenerarReporte.setText("Generar Reporte");
        BtnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerarReporteActionPerformed(evt);
            }
        });

        BtnExportarPDF.setText("Exportar PDF");
        BtnExportarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExportarPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DPFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DPFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(36, 36, 36)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(BtnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnExportarPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(38, 38, 38)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(254, 254, 254))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(DPFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(DPFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnGenerarReporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnExportarPDF)
                        .addGap(12, 12, 12)
                        .addComponent(BtnRegresar)
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
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

    private void BtnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarActionPerformed
        // Crear una nueva instancia de FrmMenu
        FrmMenu menu = new FrmMenu();
        // Hacer visible la ventana FrmMenu
        menu.setVisible(true);
        // Cerrar la ventana actual
        this.dispose();
    }//GEN-LAST:event_BtnRegresarActionPerformed

    private void BtnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarReporteActionPerformed
        // TODO add your handling code here:
        buscarEntregas();
    }//GEN-LAST:event_BtnGenerarReporteActionPerformed

    private void BtnExportarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExportarPDFActionPerformed
        // TODO add your handling code here:

        if (entregas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay entregas para exportar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("ReporteEntregas.pdf"));
            document.open();
            document.add(new Paragraph("Reporte de Entregas"));

            PdfPTable table = new PdfPTable(4); // Cuatro columnas: Tienda, Producto, Cantidad, Total
            table.addCell("Tienda");
            table.addCell("Producto");
            table.addCell("Cantidad");
            table.addCell("Total");

            for (EntregaDTO entrega : entregas) {
                String tienda = entrega.getIdTienda(); // ID o nombre de la tienda
                for (ProductoDTO producto : entrega.getProductos()) {
                    table.addCell(tienda); // Tienda
                    table.addCell(producto.getNombre()); // Producto
                    table.addCell(String.valueOf(producto.getCantidad())); // Cantidad
                    table.addCell(String.valueOf(producto.getCantidad() * producto.getPrecio())); // Total
                }
            }

            document.add(table);
            JOptionPane.showMessageDialog(this, "Reporte exportado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al exportar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            document.close();
        }
    }//GEN-LAST:event_BtnExportarPDFActionPerformed

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
            java.util.logging.Logger.getLogger(FrmReporteVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmReporteVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmReporteVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmReporteVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReporteVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnExportarPDF;
    private javax.swing.JButton BtnGenerarReporte;
    private javax.swing.JButton BtnRegresar;
    private com.github.lgooddatepicker.components.DatePicker DPFecha;
    private com.github.lgooddatepicker.components.DatePicker DPFecha1;
    private javax.swing.JTable TablaReporteVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
