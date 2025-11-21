
import gvs.modelo.Categoria;
import gvs.modelo.CategoriaDAO;
import gvs.modelo.Lote;
import gvs.modelo.ProductoDAO;
import gvs.modelo.LoteDAO;
import gvs.modelo.Producto;
import java.awt.HeadlessException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Javi
 */
public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();
        setResizable(false);
        setMaximizedBounds(getBounds());

    }

    private int entidadSeleccionada;

    private DefaultTableModel modeloProductoFiltrado(String filtro) {

        String[] columnas = {"Código", "ID Producto", "Nombre", "Marca", "Stock", "Fecha Vencimiento", "Categoría"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        ProductoDAO productoDAO = new ProductoDAO();
        LoteDAO loteDAO = new LoteDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Producto> lista = productoDAO.buscarPorFiltro(filtro);

        for (Producto p : lista) {
            int stockTotal = loteDAO.getStockActualDeProducto(p.getIdProducto());
            LocalDate vencimientoProx = loteDAO.getFechaVtoProximaPorProducto(p.getIdProducto());
            String nombreCategoria = categoriaDAO.getNombreCategoriaPorId(p.getIdCategoria());

            Object[] fila = {
                p.getCodigoBarra(),
                p.getIdProducto(),
                p.getNombreProducto(),
                p.getMarca(),
                stockTotal,
                vencimientoProx != null ? vencimientoProx.toString() : "",
                nombreCategoria != null ? nombreCategoria : ""
            };
            modelo.addRow(fila);
        }
        return modelo;
    }

    private DefaultTableModel modeloLoteFiltrado(String filtro) {
        String[] columnas = {"ID Lote", "ID Producto", "Fecha Producción", "Fecha Vencimiento", "Stock Inicial", "Stock Actual"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        LoteDAO dao = new LoteDAO();
        List<Lote> lista = dao.buscarPorFiltro(filtro);

        for (Lote l : lista) {
            Object[] fila = {
                l.getIdLote(),
                l.getIdProducto(),
                l.getFechaProduccion(),
                l.getFechaVencimiento(),
                l.getStockInicial(),
                l.getStockActual()
            };
            modelo.addRow(fila);
        }
        return modelo;
    }

    private DefaultTableModel modeloCategoriaFiltrado(String filtro) {
        String[] columnas = {"ID Categoría", "Nombre Categoría"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        CategoriaDAO dao = new CategoriaDAO();
        List<Categoria> lista = dao.buscarPorNombre(filtro);

        for (Categoria c : lista) {

            Object[] fila = {
                c.getIdCategoria(),
                c.getNombreCategoria()
            };
            modelo.addRow(fila);
        }
        return modelo;
    }

    private DefaultTableModel modeloProducto() {
        String[] columnas = {"Código", "ID Producto", "Nombre", "Marca", "Stock", "Fecha Vencimiento", "Categoría"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        ProductoDAO dao = new ProductoDAO();
        LoteDAO loteDAO = new LoteDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Producto> lista = dao.listarTodos();

        for (Producto p : lista) {

            int stockTotal = loteDAO.getStockActualDeProducto(p.getIdProducto());
            LocalDate vencimientoProx = loteDAO.getFechaVtoProximaPorProducto(p.getIdProducto());
            String nombreCategoria = categoriaDAO.getNombreCategoriaPorId(p.getIdCategoria());

            Object[] fila = {
                p.getCodigoBarra(),
                p.getIdProducto(),
                p.getNombreProducto(),
                p.getMarca(),
                stockTotal,
                vencimientoProx != null ? vencimientoProx.toString() : "",
                nombreCategoria != null ? nombreCategoria : ""
            };
            modelo.addRow(fila);
        }
        return modelo;
    }

    private DefaultTableModel modeloLote() {
        String[] columnas = {"ID Lote", "ID Producto", "Fecha Vencimiento", "Fecha Producción", "Stock Inicial", "Stock Actual"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        LoteDAO dao = new LoteDAO();
        List<Lote> lista = dao.listarTodos();
        for (Lote l : lista) {
            Object[] fila = {
                l.getIdLote(),
                l.getIdProducto(),
                l.getFechaVencimiento(),
                l.getFechaProduccion(),
                l.getStockInicial(),
                l.getStockActual()
            };
            modelo.addRow(fila);
        }
        return modelo;
    }

    private DefaultTableModel modeloCategoria() {
        String[] columnas = {"ID Categoría", "Nombre Categoría"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        CategoriaDAO dao = new CategoriaDAO();
        List<Categoria> lista = dao.listarTodas();

        for (Categoria c : lista) {
            Object[] fila = {
                c.getIdCategoria(),
                c.getNombreCategoria()
            };
            modelo.addRow(fila);
        }
        return modelo;
    }

    {
        setIconImage(new ImageIcon(getClass().getResource("/gvs/vista/img/iconocaja.png")).getImage());

        setLocationRelativeTo(null);
        setTitle("GVS - Gestor de Vencimientos y Stock");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_GestorInventario = new javax.swing.JLabel();
        jFTF_BarraBusqueda = new javax.swing.JFormattedTextField();
        jLabel_Buscar = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButton_Productos = new javax.swing.JButton();
        jButton_Lotes = new javax.swing.JButton();
        jButton_Categorias = new javax.swing.JButton();
        jButton_Editar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();
        jButton_Dasboard = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArchivo_NuevoProducto = new javax.swing.JMenu();
        jMenuItem_NuevaCategoria = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_NuevoLote = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_Salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(253, 222, 253), 5));

        jLabel_GestorInventario.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 24)); // NOI18N
        jLabel_GestorInventario.setForeground(new java.awt.Color(255, 204, 255));
        jLabel_GestorInventario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_GestorInventario.setText("Gestor de Inventario");

        jFTF_BarraBusqueda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 255)));
        jFTF_BarraBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFTF_BarraBusquedaActionPerformed(evt);
            }
        });

        jLabel_Buscar.setText("Buscar:");

        jScrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 255)));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Marca", "Stock"
            }
        ));
        jScrollPane.setViewportView(jTable);

        jButton_Productos.setText("Productos");
        jButton_Productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ProductosActionPerformed(evt);
            }
        });

        jButton_Lotes.setText("Lotes");
        jButton_Lotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LotesActionPerformed(evt);
            }
        });

        jButton_Categorias.setText("Categorías");
        jButton_Categorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CategoriasActionPerformed(evt);
            }
        });

        jButton_Editar.setText("Editar");
        jButton_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditarActionPerformed(evt);
            }
        });

        jButton_Eliminar.setText("Eliminar");
        jButton_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EliminarActionPerformed(evt);
            }
        });

        jButton_Dasboard.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 24)); // NOI18N
        jButton_Dasboard.setForeground(new java.awt.Color(255, 153, 204));
        jButton_Dasboard.setText("Abrir Dashboard");
        jButton_Dasboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DasboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel_Buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTF_BarraBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_Editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Eliminar)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_Productos)
                                .addGap(32, 32, 32)
                                .addComponent(jButton_Lotes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_Categorias))
                            .addComponent(jLabel_GestorInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(390, 390, 390))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 905, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Dasboard)
                .addGap(386, 386, 386))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel_GestorInventario)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTF_BarraBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Buscar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Editar)
                            .addComponent(jButton_Eliminar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Lotes)
                            .addComponent(jButton_Categorias)
                            .addComponent(jButton_Productos))))
                .addGap(60, 60, 60)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton_Dasboard)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jMenuArchivo_NuevoProducto.setText("Archivo");

        jMenuItem_NuevaCategoria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_NuevaCategoria.setText("Nueva Categoría");
        jMenuItem_NuevaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_NuevaCategoriaActionPerformed(evt);
            }
        });
        jMenuArchivo_NuevoProducto.add(jMenuItem_NuevaCategoria);
        jMenuArchivo_NuevoProducto.add(jSeparator1);

        jMenuItem_NuevoLote.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_NuevoLote.setText("Nuevo Lote");
        jMenuItem_NuevoLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_NuevoLoteActionPerformed(evt);
            }
        });
        jMenuArchivo_NuevoProducto.add(jMenuItem_NuevoLote);
        jMenuArchivo_NuevoProducto.add(jSeparator3);

        jMenuItem_Salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem_Salir.setText("Salir");
        jMenuItem_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_SalirActionPerformed(evt);
            }
        });
        jMenuArchivo_NuevoProducto.add(jMenuItem_Salir);

        jMenuBar1.add(jMenuArchivo_NuevoProducto);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_NuevoLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_NuevoLoteActionPerformed


    String codigoBarra = JOptionPane.showInputDialog(this, "Ingrese el código de barra del producto:");
    String nombreProd = JOptionPane.showInputDialog(this, "Ingrese el nombre del producto:");
    String marcaProd = JOptionPane.showInputDialog(this, "Ingrese la marca del producto:");
    String idCatStr = JOptionPane.showInputDialog(this, "Ingrese el ID de categoría:");
    String fechaVenc = JOptionPane.showInputDialog(this, "Ingrese la fecha de vencimiento (yyyy-MM-dd):");
    String fechaProd = JOptionPane.showInputDialog(this, "Ingrese la fecha de producción (yyyy-MM-dd):");
    String stockIniStr = JOptionPane.showInputDialog(this, "Ingrese el stock inicial:");
    String stockActStr = JOptionPane.showInputDialog(this, "Ingrese el stock actual:");

    if (codigoBarra == null || nombreProd == null || marcaProd == null || idCatStr == null ||
        fechaVenc == null || fechaProd == null || stockIniStr == null || stockActStr == null ||
        codigoBarra.trim().isEmpty() || nombreProd.trim().isEmpty() || idCatStr.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
        return;
    }

    try {
        int idCategoria = Integer.parseInt(idCatStr.trim());
        LocalDate fechaVencimiento = LocalDate.parse(fechaVenc.trim());
        LocalDate fechaProduccion = LocalDate.parse(fechaProd.trim());
        int stockInicial = Integer.parseInt(stockIniStr.trim());
        int stockActual = Integer.parseInt(stockActStr.trim());

        Producto producto = new Producto();
        producto.setCodigoBarra(codigoBarra.trim());
        producto.setNombreProducto(nombreProd.trim());
        producto.setMarca(marcaProd != null ? marcaProd.trim() : "");
        producto.setIdCategoria(idCategoria);

        ProductoDAO prodDao = new ProductoDAO();
        int idProducto = prodDao.crearProductoRetornandoId(producto);

        if (idProducto > 0) {
            JOptionPane.showMessageDialog(this, "Producto creado exitosamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear producto");
            return;
        }
        Lote lote = new Lote();
        lote.setIdProducto(idProducto);
        lote.setFechaVencimiento(fechaVencimiento);
        lote.setFechaProduccion(fechaProduccion);
        lote.setStockInicial(stockInicial);
        lote.setStockActual(stockActual);

        LoteDAO loteDao = new LoteDAO();
        boolean ok = loteDao.crearLote(lote);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Lote creado exitosamente y asociado al producto");
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear lote para el producto nuevo");
            return;
        }

        jTable.setModel(modeloLote());
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Verifica que todos los campos numéricos estén completos y correctos");
    } catch (HeadlessException ex) {
        JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage());
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem_NuevoLoteActionPerformed

    private void jButton_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditarActionPerformed
    int fila = jTable.getSelectedRow();
    if (fila != -1) {
        switch (entidadSeleccionada) {
            case 0:
                int idProd = (int) jTable.getValueAt(fila, 1);

                ProductoDAO prodDAO = new ProductoDAO();
                Producto prod = prodDAO.buscarPorId(idProd);

                if (prod != null) {
                    String nuevoNombre = JOptionPane.showInputDialog(this, "Editar nombre:", prod.getNombreProducto());
                    String nuevaMarca = JOptionPane.showInputDialog(this, "Editar marca:", prod.getMarca());
                    String nuevaCategoriaStr = JOptionPane.showInputDialog(this, "Editar ID Categoría:", prod.getIdCategoria());

                    if (nuevoNombre != null && nuevaMarca != null && nuevaCategoriaStr != null) {
                        prod.setNombreProducto(nuevoNombre.trim());
                        prod.setMarca(nuevaMarca.trim());
                        try {
                            prod.setIdCategoria(Integer.parseInt(nuevaCategoriaStr.trim()));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "ID de categoría inválido.");
                            return;
                        }

                        if (prodDAO.actualizarProducto(prod)) {
                            JOptionPane.showMessageDialog(this, "Producto actualizado.");
                            jTable.setModel(modeloProducto());
                        } else {
                            JOptionPane.showMessageDialog(this, "Error al actualizar.");
                        }
                    }
                }
                break;

            case 1:
                int idLote = (int) jTable.getValueAt(fila, 0);
                LoteDAO loteDAO = new LoteDAO();
                Lote lote = loteDAO.buscarPorId(idLote);

                if (lote != null) {
                    String nuevoStockStr = JOptionPane.showInputDialog(this, "Editar stock actual:", lote.getStockActual());
                    String nuevaFechaVenc = JOptionPane.showInputDialog(this, "Editar fecha vencimiento (yyyy-MM-dd):", lote.getFechaVencimiento());
                    if (nuevoStockStr != null && nuevaFechaVenc != null) {
                        try {
                            lote.setStockActual(Integer.parseInt(nuevoStockStr.trim()));
                            lote.setFechaVencimiento(LocalDate.parse(nuevaFechaVenc.trim()));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Datos inválidos de lote.");
                            return;
                        }

                        if (loteDAO.actualizarLote(lote)) {
                            JOptionPane.showMessageDialog(this, "Lote actualizado.");
                            jTable.setModel(modeloLote());
                        } else {
                            JOptionPane.showMessageDialog(this, "Error al actualizar lote.");
                        }
                    }
                }
                break;

            case 2: // Editar Categoría
                int idCat = (int) jTable.getValueAt(fila, 0);
                CategoriaDAO catDAO = new CategoriaDAO();
                Categoria cat = catDAO.buscarPorId(idCat);

                if (cat != null) {
                    String nuevoNombre = JOptionPane.showInputDialog(this, "Editar nombre categoría:", cat.getNombreCategoria());
                    if (nuevoNombre != null) {
                        cat.setNombreCategoria(nuevoNombre.trim());
                        if (catDAO.actualizarCategoria(cat)) {
                            JOptionPane.showMessageDialog(this, "Categoría actualizada.");
                            jTable.setModel(modeloCategoria());
                        } else {
                            JOptionPane.showMessageDialog(this, "Error al actualizar categoría.");
                        }
                    }
                }
                break;
        }
    }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_EditarActionPerformed

    private void jButton_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EliminarActionPerformed

        int fila = jTable.getSelectedRow();
        if (fila != -1) {
            switch (entidadSeleccionada) {
                case 0:
                    int idProd = (int) jTable.getValueAt(fila, 1);
                    ProductoDAO prodDAO = new ProductoDAO();
                    if (prodDAO.eliminarProducto(idProd)) {
                        jTable.setModel(modeloProducto());
                    }
                    break;
                case 1:
                    int idLote = (int) jTable.getValueAt(fila, 0);
                    LoteDAO loteDAO = new LoteDAO();
                    if (loteDAO.eliminarLote(idLote)) {
                        jTable.setModel(modeloLote());
                    }
                    break;
                case 2:
                    int idCat = (int) jTable.getValueAt(fila, 0);
                    CategoriaDAO catDAO = new CategoriaDAO();
                    if (catDAO.eliminarCategoria(idCat)) {
                        jTable.setModel(modeloCategoria());
                    }
                    break;
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_EliminarActionPerformed

    private void jButton_ProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ProductosActionPerformed
        jTable.setModel(modeloProducto());
        entidadSeleccionada = 0;
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ProductosActionPerformed

    private void jButton_LotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LotesActionPerformed
        jTable.setModel(modeloLote());
        entidadSeleccionada = 1;
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_LotesActionPerformed

    private void jButton_CategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CategoriasActionPerformed
        jTable.setModel(modeloCategoria());
        entidadSeleccionada = 2;
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_CategoriasActionPerformed

    private void jFTF_BarraBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFTF_BarraBusquedaActionPerformed
        String filtro = jFTF_BarraBusqueda.getText().trim();

        switch (entidadSeleccionada) {
            case 0:
                jTable.setModel(filtro.isEmpty() ? modeloProducto() : modeloProductoFiltrado(filtro));
                break;
            case 1:
                jTable.setModel(filtro.isEmpty() ? modeloLote() : modeloLoteFiltrado(filtro));
                break;
            case 2:
                jTable.setModel(filtro.isEmpty() ? modeloCategoria() : modeloCategoriaFiltrado(filtro));
                break;
    }//GEN-LAST:event_jFTF_BarraBusquedaActionPerformed
    }
    private void jMenuItem_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_SalirActionPerformed

        System.exit(0);

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem_SalirActionPerformed

    private void jButton_DasboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DasboardActionPerformed

        ProductosFrame dashboard = new ProductosFrame();
        dashboard.setLocationRelativeTo(this);
        dashboard.setVisible(true);
        this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_DasboardActionPerformed

    private void jMenuItem_NuevaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_NuevaCategoriaActionPerformed

        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva categoría:");

        if (nombre != null && !nombre.trim().isEmpty()) {
            Categoria cat = new Categoria();
            cat.setNombreCategoria(nombre.trim());
            CategoriaDAO dao = new CategoriaDAO();
            boolean ok = dao.crearCategoria(cat);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Categoría creada exitosamente");
                modeloCategoria();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear categoría");
            }
        } else if (nombre != null) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem_NuevaCategoriaActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {

            ex.printStackTrace();

        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Categorias;
    private javax.swing.JButton jButton_Dasboard;
    private javax.swing.JButton jButton_Editar;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JButton jButton_Lotes;
    private javax.swing.JButton jButton_Productos;
    private javax.swing.JFormattedTextField jFTF_BarraBusqueda;
    private javax.swing.JLabel jLabel_Buscar;
    private javax.swing.JLabel jLabel_GestorInventario;
    private javax.swing.JMenu jMenuArchivo_NuevoProducto;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem_NuevaCategoria;
    private javax.swing.JMenuItem jMenuItem_NuevoLote;
    private javax.swing.JMenuItem jMenuItem_Salir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
