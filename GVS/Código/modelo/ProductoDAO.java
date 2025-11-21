package gvs.modelo;

import gvs.util.ConexionBaseDeDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Javi
 */
public class ProductoDAO {

    public boolean crearProducto(Producto prod) {
        String sql = "INSERT INTO productos (codigobarra, nombreproducto, marca, idcategoria) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, prod.getCodigoBarra());
            pst.setString(2, prod.getNombreProducto());
            pst.setString(3, prod.getMarca());
            pst.setInt(4, prod.getIdCategoria());
            int filas = pst.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear producto: " + e.getMessage());
            return false;
        }
    }

    public int crearProductoRetornandoId(Producto producto) {
        int idGenerado = -1;
        String sql = "INSERT INTO productos (codigobarra, nombreproducto, marca, idcategoria) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, producto.getCodigoBarra());
            pst.setString(2, producto.getNombreProducto());
            pst.setString(3, producto.getMarca());
            pst.setInt(4, producto.getIdCategoria());

            int filas = pst.executeUpdate();
            if (filas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al crear producto: " + e.getMessage());
        }
        return idGenerado;

    }

    public List<Producto> buscarPorFiltro(String filtro) {
    List<Producto> lista = new ArrayList<>();
    String sql = "SELECT * FROM productos WHERE codigobarra LIKE ? OR nombreproducto LIKE ? OR marca LIKE ?";
    try (Connection conn = ConexionBaseDeDatos.getConexion();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        String textoFiltro = "%" + filtro + "%";
        pst.setString(1, textoFiltro);
        pst.setString(2, textoFiltro);
        pst.setString(3, textoFiltro);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Producto producto = new Producto();
            producto.setIdProducto(rs.getInt("idproducto"));
            producto.setCodigoBarra(rs.getString("codigobarra"));
            producto.setNombreProducto(rs.getString("nombreproducto"));
            producto.setMarca(rs.getString("marca"));
            producto.setIdCategoria(rs.getInt("idcategoria"));
            lista.add(producto);
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar producto: " + e.getMessage());
    }
    return lista;
}
    public Producto buscarPorId(int id) {
    Producto producto = null;
    String sql = "SELECT * FROM productos WHERE idproducto = ?";
    try (Connection conn = ConexionBaseDeDatos.getConexion();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            producto = new Producto();
            producto.setIdProducto(rs.getInt("idproducto"));
            producto.setCodigoBarra(rs.getString("codigobarra"));
            producto.setNombreProducto(rs.getString("nombreproducto"));
            producto.setMarca(rs.getString("marca"));
            producto.setIdCategoria(rs.getInt("idcategoria"));
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar producto por ID: " + e.getMessage());
    }
    return producto;
}


    public List<Producto> listarTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = ConexionBaseDeDatos.getConexion(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idproducto"));
                p.setCodigoBarra(rs.getString("codigobarra"));
                p.setNombreProducto(rs.getString("nombreproducto"));
                p.setMarca(rs.getString("marca"));
                p.setIdCategoria(rs.getInt("idcategoria"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizarProducto(Producto p) {
        String sql = "UPDATE productos SET nombreproducto=?, marca=?, idcategoria=? "
                + "WHERE idproducto=?";

        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, p.getNombreProducto());
            pst.setString(2, p.getMarca());
            pst.setInt(3, p.getIdCategoria());
            pst.setInt(4, p.getIdProducto());

            int filas = pst.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProducto(int idProducto) {
        String sql = "DELETE FROM productos WHERE idproducto = ?";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idProducto);
            int filas = pst.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("foreign key constraint fails")) {
                javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Para poder eliminar un producto primero debes eliminar el lote",
                        "Error al eliminar producto",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                );
            } else {
                javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Error al eliminar producto: " + e.getMessage(),
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
            return false;
        }
    }
}
