
import gvs.modelo.Lote;
import gvs.modelo.LoteDAO;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import java.io.FileNotFoundException;
import com.itextpdf.text.Phrase;

/**
 *
 * @author Javi
 */
public class ProductosFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProductosFrame.class.getName());

    private void exportarAPDF() {

        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                Document document = new Document();
                File file = fileChooser.getSelectedFile();
                if (!file.getName().toLowerCase().endsWith(".pdf")) {
                    file = new File(file.getParentFile(), file.getName() + ".pdf");
                }
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
                Paragraph titulo = new Paragraph("Productos Próximos a Vencer", fontTitulo);
                document.add(titulo);
                document.add(new Paragraph(" "));

                DefaultTableModel model = (DefaultTableModel) jTable_InfoProductos.getModel();
                PdfPTable pdfTable = new PdfPTable(model.getColumnCount());

                for (int i = 0; i < model.getColumnCount(); i++) {
                    PdfPCell cell = new PdfPCell(new Phrase(model.getColumnName(i)));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    pdfTable.addCell(cell);
                }

                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object value = model.getValueAt(i, j);
                        pdfTable.addCell(value == null ? "" : value.toString());
                    }
                }

                document.add(pdfTable);
                document.close();

                JOptionPane.showMessageDialog(this, "Exportación a PDF exitosa");
            } catch (DocumentException | HeadlessException | FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Error al exportar: " + e.getMessage());
            }
        }
    }

    private void imprimirTabla() {
        try {
            jTable_InfoProductos.print();
            JOptionPane.showMessageDialog(this, "Impresión enviada correctamente");
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(this, "Error al imprimir: " + e.getMessage());
        }
    }

    private void cargarAlertas() {
        LoteDAO dao = new LoteDAO();
        List<Lote> proximosVencer = dao.listarProximosAVencer(30);
        DefaultTableModel modelo = (DefaultTableModel) jTable_InfoProductos.getModel();
        modelo.setRowCount(0);
        for (Lote lote : proximosVencer) {
            modelo.addRow(new Object[]{
                lote.getNombreProducto(),
                lote.getIdLote(),
                lote.getFechaVencimiento().format(DateTimeFormatter.ofPattern("dd/MM/yy")),
                lote.getStockActual()
            });
            int cantidad = proximosVencer.size();
            String texto = "¡" + cantidad + (cantidad == 1 ? " producto está" : " productos están") + " a menos de 10 días de vencer!";
            jLabel_AdvertenciaVencimiento.setText(texto);
        }
    }

    public ProductosFrame() {
        initComponents();
        cargarAlertas();
        LocalDate hoy = LocalDate.now();
        setResizable(false);
        setMaximizedBounds(getBounds());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = hoy.format(formato);
        jLabel_FechaActual.setText("Fecha Actual: " + fechaFormateada);
        setIconImage(new ImageIcon(getClass().getResource("/gvs/vista/img/iconocaja.png")).getImage());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_Dashboard = new javax.swing.JLabel();
        jLabel_ProductosAVencer = new javax.swing.JLabel();
        jLabel_FechaActual = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_InfoProductos = new javax.swing.JTable();
        jButton_Exportar = new javax.swing.JButton();
        jLabel_AdvertenciaVencimiento = new javax.swing.JLabel();
        jButton_Volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_Dashboard.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 24)); // NOI18N
        jLabel_Dashboard.setForeground(new java.awt.Color(255, 204, 255));
        jLabel_Dashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Dashboard.setText("Dashboard de Alertas de Vencimientos");

        jLabel_ProductosAVencer.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 24)); // NOI18N
        jLabel_ProductosAVencer.setForeground(new java.awt.Color(204, 204, 255));
        jLabel_ProductosAVencer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_ProductosAVencer.setText("Productos/Lotes Próximos a Vencer");

        jLabel_FechaActual.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_FechaActual.setFont(new java.awt.Font("Rockwell Condensed", 0, 18)); // NOI18N
        jLabel_FechaActual.setForeground(new java.awt.Color(204, 204, 255));
        jLabel_FechaActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jTable_InfoProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Lote", "Vence el...", "Stock"
            }
        ));
        jScrollPane1.setViewportView(jTable_InfoProductos);

        jButton_Exportar.setFont(new java.awt.Font("Rockwell Condensed", 0, 12)); // NOI18N
        jButton_Exportar.setText("Exportar/Imprimir");
        jButton_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExportarActionPerformed(evt);
            }
        });

        jLabel_AdvertenciaVencimiento.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel_AdvertenciaVencimiento.setForeground(new java.awt.Color(255, 102, 204));

        jButton_Volver.setFont(new java.awt.Font("Rockwell Condensed", 0, 12)); // NOI18N
        jButton_Volver.setText("Volver");
        jButton_Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_VolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_ProductosAVencer, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_FechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 28, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel_AdvertenciaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton_Volver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel_Dashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_ProductosAVencer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jLabel_FechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Volver)
                    .addComponent(jButton_Exportar))
                .addGap(39, 39, 39)
                .addComponent(jLabel_AdvertenciaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExportarActionPerformed

        String[] opciones = {"Imprimir", "Descargar PDF", "Cancelar"};
        int seleccion = JOptionPane.showOptionDialog(
                this,
                "¿Qué deseas hacer con la tabla?",
                "Exportar / Imprimir",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == 0) {
            imprimirTabla();
        } else if (seleccion == 1) {
            exportarAPDF();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ExportarActionPerformed

    private void jButton_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_VolverActionPerformed
    this.dispose();
    new MainFrame().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_VolverActionPerformed

    public static void main(String args[]) {

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> new ProductosFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Exportar;
    private javax.swing.JButton jButton_Volver;
    private javax.swing.JLabel jLabel_AdvertenciaVencimiento;
    private javax.swing.JLabel jLabel_Dashboard;
    private javax.swing.JLabel jLabel_FechaActual;
    private javax.swing.JLabel jLabel_ProductosAVencer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_InfoProductos;
    // End of variables declaration//GEN-END:variables

}
