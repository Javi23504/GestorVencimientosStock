
package gvs.controlador;
import gvs.modelo.Categoria;
import gvs.modelo.CategoriaDAO;
import java.util.List;
/**
 *
 * @author Javi
 */
public class CategoriaControlador {

    private CategoriaDAO dao;
    public CategoriaControlador() {
        this.dao = new CategoriaDAO();
    }

    public boolean crearCategoria(String nombreCategoria) {
        if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) {
            return false;
        }
        List<Categoria> existentes = dao.buscarPorNombre(nombreCategoria.trim());
        for (Categoria cat : existentes) {
            if (cat.getNombreCategoria().equalsIgnoreCase(nombreCategoria.trim())) {
                return false; 
            }
        }
        Categoria cat = new Categoria();
        cat.setNombreCategoria(nombreCategoria.trim());
        return dao.crearCategoria(cat);
    }
    public boolean actualizarCategoria(Categoria categoria) {
        if (categoria == null) return false;
        if (categoria.getIdCategoria() <= 0) return false;
        if (categoria.getNombreCategoria() == null || categoria.getNombreCategoria().trim().isEmpty()) return false;
        return dao.actualizarCategoria(categoria);
    }
    public boolean eliminarCategoria(int idCategoria) {
        if (idCategoria <= 0) return false;
        return dao.eliminarCategoria(idCategoria);
    }
    public List<Categoria> listarTodas() {
        return dao.listarTodas();
    }
    public Categoria buscarPorId(int idCategoria) {
        if (idCategoria <= 0) return null;
        return dao.buscarPorId(idCategoria);
    }
    public List<Categoria> buscarPorNombre(String filtro) {
        return dao.buscarPorNombre(filtro);
    }
    public String getNombreCategoriaPorId(int idCategoria) {
        if (idCategoria <= 0) return null;
        return dao.getNombreCategoriaPorId(idCategoria);
    }
}

