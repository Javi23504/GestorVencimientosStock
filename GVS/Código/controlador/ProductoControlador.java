
package gvs.controlador;
import gvs.modelo.Producto;
import gvs.modelo.ProductoDAO;
import java.util.List;

/**
 *
 * @author Javi
 */
public class ProductoControlador {
    private ProductoDAO dao;

    public ProductoControlador() {
        this.dao = new ProductoDAO();
    }

    public boolean crearProducto(String codigoBarra, String nombre, String marca, int idCategoria) {
        if (codigoBarra == null || codigoBarra.trim().isEmpty()) return false;
        if (nombre == null || nombre.trim().isEmpty()) return false;

        List<Producto> existentes = dao.buscarPorFiltro(codigoBarra);
        for (Producto p : existentes) {
            if (p.getCodigoBarra().equalsIgnoreCase(codigoBarra)) {
                return false; 
            }
        }

        Producto prod = new Producto();
        prod.setCodigoBarra(codigoBarra);
        prod.setNombreProducto(nombre);
        prod.setMarca(marca);
        prod.setIdCategoria(idCategoria);

        return dao.crearProducto(prod);
    }

    public List<Producto> listarTodos() {
        return dao.listarTodos();
    }

    public Producto buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public boolean actualizarProducto(Producto p) {
        return dao.actualizarProducto(p);
    }

    public boolean eliminarProducto(int id) {
        return dao.eliminarProducto(id);
    }
}

