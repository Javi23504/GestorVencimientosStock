
package gvs.controlador;
import gvs.modelo.Lote;
import gvs.modelo.LoteDAO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Javi
 */
public class LoteControlador {

public class LoteController {
    private LoteDAO dao;
    public LoteController() {
        this.dao = new LoteDAO();
    }
    public boolean crearLote(Lote lote) {
        if (lote == null) return false;
        if (lote.getFechaVencimiento() == null) return false;
        if (lote.getIdProducto() <= 0) return false;

        return dao.crearLote(lote);
    }
    public Lote buscarPorId(int idLote) {
        return dao.buscarPorId(idLote);
    }
    public boolean actualizarLote(Lote lote) {
        if (lote == null || lote.getIdLote() <= 0) return false;
        return dao.actualizarLote(lote);
    }
    public boolean eliminarLote(int idLote) {
        return dao.eliminarLote(idLote);
    }
    public List<Lote> listarLotesActivos() {
        return dao.listarLotesActivos();
    }
    public boolean actualizarStock(int idLote, int nuevoStock) {
        if (idLote <= 0 || nuevoStock < 0) return false;
        return dao.actualizarStock(idLote, nuevoStock);
    }
    public LocalDate getFechaVtoProximaPorProducto(int idProducto) {
        return dao.getFechaVtoProximaPorProducto(idProducto);
    }
    public List<Lote> listarProximosAVencer(int dias) {
        return dao.listarProximosAVencer(dias);
    }
    public List<Lote> listarTodos() {
        return dao.listarTodos();
    }
    public List<Lote> buscarPorFiltro(String filtro) {
        return dao.buscarPorFiltro(filtro);
    }
    public int getStockActualDeProducto(int idProducto) {
        return dao.getStockActualDeProducto(idProducto);
    }
}    
}
