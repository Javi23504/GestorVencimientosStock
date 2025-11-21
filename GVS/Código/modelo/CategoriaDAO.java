package gvs.modelo;

import gvs.util.ConexionBaseDeDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Javi
 */
public class CategoriaDAO {

    public boolean actualizarCategoria(Categoria c) {
        String sql = "UPDATE categorias SET nombrecategoria=? WHERE idcategoria=?";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, c.getNombreCategoria());
            pst.setInt(2, c.getIdCategoria());
            int filas = pst.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar categoría: " + e.getMessage());
            return false;
        }
    }

    public Categoria buscarPorId(int idCategoria) {
        String sql = "SELECT * FROM categorias WHERE idcategoria = ?";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idCategoria);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("idcategoria"));
                c.setNombreCategoria(rs.getString("nombrecategoria"));
                return c;
            }
        } catch (SQLException e) {
            System.err.println("Error en buscarPorId categoría: " + e.getMessage());
        }
        return null;
    }

    public boolean eliminarCategoria(int idCategoria) {
        String sql = "DELETE FROM categorias WHERE idcategoria = ?";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idCategoria);
            int filas = pst.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar categoría: " + e.getMessage());
            return false;
        }
    }

    public List<Categoria> listarTodas() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try (Connection conn = ConexionBaseDeDatos.getConexion(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("idcategoria"));
                cat.setNombreCategoria(rs.getString("nombrecategoria"));
                lista.add(cat);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar categorías: " + e.getMessage());
        }
        return lista;
    }

    public boolean crearCategoria (Categoria cat) {
        String sql = "INSERT INTO categorias (nombrecategoria) VALUES (?)";

        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, cat.getNombreCategoria());
            int filas = pst.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear categoría: " + e.getMessage());
            return false;
        }
    }

    public List<Categoria> buscarPorNombre(String filtro) {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias WHERE CAST(idcategoria AS CHAR) LIKE ? OR nombrecategoria LIKE ?";
        try {
            Connection conn = ConexionBaseDeDatos.getConexion();
            PreparedStatement pst = conn.prepareStatement(sql);
            String textoFiltro = "%" + filtro + "%";
            pst.setString(1, textoFiltro);
            pst.setString(2, textoFiltro);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("idcategoria"));
                cat.setNombreCategoria(rs.getString("nombrecategoria"));
                lista.add(cat);
            }
            ConexionBaseDeDatos.cerrarConexion(conn);
        } catch (SQLException e) {
            System.err.println("Error al buscar categoría por nombre o id: " + e.getMessage());
        }
        return lista;
    }

    public String getNombreCategoriaPorId(int idCategoria) {
        String nombreCategoria = null;
        String sql = "SELECT nombrecategoria FROM categorias WHERE idcategoria = ?";
        try (Connection conn = ConexionBaseDeDatos.getConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idCategoria);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nombreCategoria = rs.getString("nombrecategoria");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener nombre de la categoría: " + e.getMessage());
        }
        return nombreCategoria;
    }
}
