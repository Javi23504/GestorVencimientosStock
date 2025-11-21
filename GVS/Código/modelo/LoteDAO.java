package gvs.modelo;

import gvs.util.ConexionBaseDeDatos;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Javi
 */
public class LoteDAO {

    public Lote buscarPorId(int idLote) {
        String sql = "SELECT * FROM lotes WHERE idlote = ?";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idLote);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Lote l = new Lote();
                l.setIdLote(rs.getInt("idlote"));
                l.setIdProducto(rs.getInt("idproducto"));
                java.sql.Date venc = rs.getDate("fechavencimiento");
                l.setFechaVencimiento(venc != null ? venc.toLocalDate() : null);
                java.sql.Date prod = rs.getDate("fechaproduccion");
                l.setFechaProduccion(prod != null ? prod.toLocalDate() : null);
                l.setStockInicial(rs.getInt("stockinicial"));
                l.setStockActual(rs.getInt("stockactual"));
                return l;
            }
        } catch (SQLException e) {
            System.err.println("Error en buscarPorId lote: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarLote(Lote l) {
        String sql = "UPDATE lotes SET idproducto=?, fechavencimiento=?, fechaproduccion=?, stockinicial=?, stockactual=? WHERE idlote=?";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, l.getIdProducto());
            pst.setDate(2, java.sql.Date.valueOf(l.getFechaVencimiento()));
            pst.setDate(3, l.getFechaProduccion() != null ? java.sql.Date.valueOf(l.getFechaProduccion()) : null);
            pst.setInt(4, l.getStockInicial());
            pst.setInt(5, l.getStockActual());
            pst.setInt(6, l.getIdLote());
            int filas = pst.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar lote: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarLote(int idLote) {
        String sql = "DELETE FROM lotes WHERE idlote = ?";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idLote);
            int filas = pst.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar lote: " + e.getMessage());
            return false;
        }
    }

    public boolean crearLote(Lote lote) {
    String sql = "INSERT INTO lotes (idproducto, fechavencimiento, fechaproduccion, stockinicial, stockactual) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = ConexionBaseDeDatos.getConexion();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setInt(1, lote.getIdProducto());
        pst.setDate(2, java.sql.Date.valueOf(lote.getFechaVencimiento()));
        pst.setDate(3, java.sql.Date.valueOf(lote.getFechaProduccion()));
        pst.setInt(4, lote.getStockInicial());
        pst.setInt(5, lote.getStockActual());
        int filas = pst.executeUpdate();
        return filas > 0;
    } catch (SQLException e) {
        System.err.println("Error al crear lote: " + e.getMessage());
        return false;
    }

    }

    public List<Lote> listarLotesActivos() {
        List<Lote> lista = new ArrayList<>();
        String sql = "SELECT l.*, p.nombreproducto, p.marca "
                + "FROM lotes l "
                + "INNER JOIN productos p ON l.idproducto = p.idproducto "
                + "WHERE l.stockactual > 0 "
                + "ORDER BY l.fechavencimiento ASC";

        try (Connection conn = ConexionBaseDeDatos.getConexion(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Lote lote = new Lote();
                lote.setIdLote(rs.getInt("idlote"));
                lote.setIdProducto(rs.getInt("idproducto"));
                lote.setFechaVencimiento(rs.getDate("fechavencimiento").toLocalDate());

                Date fechaProd = rs.getDate("fechaproduccion");
                if (fechaProd != null) {
                    lote.setFechaProduccion(fechaProd.toLocalDate());
                }

                lote.setStockInicial(rs.getInt("stockinicial"));
                lote.setStockActual(rs.getInt("stockactual"));
                lote.setNombreProducto(rs.getString("nombreproducto"));
                lista.add(lote);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar lotes: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizarStock(int idLote, int nuevoStock) {
        String sql = "UPDATE lotes SET stockactual = ? WHERE idlote = ?";

        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, nuevoStock);
            pst.setInt(2, idLote);

            int filas = pst.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar stock: " + e.getMessage());
            return false;
        }
    }

    public LocalDate getFechaVtoProximaPorProducto(int idProducto) {
        LocalDate fecha = null;
        String sql = "SELECT MIN(fechavencimiento) FROM lotes WHERE idproducto = ? AND stockactual > 0";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idProducto);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                java.sql.Date sqlDate = rs.getDate(1);
                if (sqlDate != null) {
                    fecha = sqlDate.toLocalDate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener próxima fecha de vencimiento: " + e.getMessage());
        }
        return fecha;
    }
    public List<Lote> listarProximosAVencer(int dias) {
    List<Lote> lista = new ArrayList<>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = ConexionBaseDeDatos.getConexion();

        String sql = "SELECT p.nombreproducto, l.idlote, l.fechavencimiento, l.stockactual "
           + "FROM lotes l "
           + "JOIN productos p ON l.idproducto = p.idproducto "
           + "WHERE l.fechavencimiento BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL ? DAY)";
    
        ps = conn.prepareStatement(sql);
        ps.setInt(1, dias);
        rs = ps.executeQuery();

        while (rs.next()) {
            Lote lote = new Lote();
            lote.setNombreProducto(rs.getString("nombreproducto"));
            lote.setIdLote(rs.getInt("idlote"));
            lote.setFechaVencimiento(rs.getDate("fechavencimiento").toLocalDate());
            lote.setStockActual(rs.getInt("stockactual"));
            lista.add(lote);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try { if (rs != null) rs.close(); } catch (SQLException ignore) {}
        try { if (ps != null) ps.close(); } catch (SQLException ignore) {}
        try { if (conn != null) ConexionBaseDeDatos.cerrarConexion(conn); } catch (Exception ignore) {}
    }
    return lista;
}


    public List<Lote> listarTodos() {
        List<Lote> lista = new ArrayList<>();
        String sql = "SELECT * FROM lotes";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Lote lote = new Lote();
                lote.setIdLote(rs.getInt("idlote"));
                lote.setIdProducto(rs.getInt("idproducto"));
                java.sql.Date venc = rs.getDate("fechavencimiento");
                lote.setFechaVencimiento(venc != null ? venc.toLocalDate() : null);

                java.sql.Date prod = rs.getDate("fechaproduccion");
                lote.setFechaProduccion(prod != null ? prod.toLocalDate() : null);
                lote.setStockInicial(rs.getInt("stockinicial"));
                lote.setStockActual(rs.getInt("stockactual"));
                lista.add(lote);
            }
            return lista;

        } catch (SQLException ex) {
            System.out.println("Error accediendo a base de datos: " + ex.getMessage());
        }
        return null;
    }

    public List<Lote> buscarPorFiltro(String filtro) {
        List<Lote> lista = new ArrayList<>();
        String sql = "SELECT l.*, p.nombreproducto "
                + "FROM lotes l "
                + "INNER JOIN productos p ON l.idproducto = p.idproducto "
                + "WHERE CAST(l.idlote AS CHAR) LIKE ? "
                + "OR CAST(l.idproducto AS CHAR) LIKE ? "
                + "OR p.nombreproducto LIKE ? "
                + "OR p.marca LIKE ? "
                + "OR CAST(l.fechaproduccion AS CHAR) LIKE ? "
                + "OR CAST(l.fechavencimiento AS CHAR) LIKE ? "
                + "OR CAST(l.stockactual AS CHAR) LIKE ? "
                + "OR CAST(l.stockinicial AS CHAR) LIKE ?";

        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            String textoFiltro = "%" + filtro + "%";
            for (int i = 1; i <= 8; i++) {
                pst.setString(i, textoFiltro);
            }

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Lote lote = new Lote();
                lote.setIdLote(rs.getInt("idlote"));
                lote.setIdProducto(rs.getInt("idproducto"));
                lote.setNombreProducto(rs.getString("nombreproducto"));
                java.sql.Date prod = rs.getDate("fechaproduccion");
                if (prod != null) {
                    lote.setFechaProduccion(prod.toLocalDate());
                }
                java.sql.Date venc = rs.getDate("fechavencimiento");
                if (venc != null) {
                    lote.setFechaVencimiento(venc.toLocalDate());
                }
                lote.setStockInicial(rs.getInt("stockinicial"));
                lote.setStockActual(rs.getInt("stockactual"));
                lista.add(lote);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar lotes por filtro: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public int getStockActualDeProducto(int idProducto) {
        int stock = 0;
        String sql = "SELECT SUM(stockactual) FROM lotes WHERE idproducto = ?";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idProducto);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                stock = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener stock de producto: " + e.getMessage());
        }
        return stock;
    }

}
