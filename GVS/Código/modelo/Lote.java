package gvs.modelo;

import java.time.LocalDate;

/**
 *
 * @author Javi
 */
public class Lote {

    private int idLote;
    private int idProducto;
    private LocalDate fechaVencimiento;
    private LocalDate fechaProduccion;
    private int stockInicial;
    private int stockActual;
    private String nombreProducto;

    public Lote() {
    }

    public Lote(int idLote, int idProducto, LocalDate fechaVencimiento, LocalDate fechaProduccion, int stockInicial, int stockActual, String nombreProducto) {
        this.idLote = idLote;
        this.idProducto = idProducto;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaProduccion = fechaProduccion;
        this.stockInicial = stockInicial;
        this.stockActual = stockActual;
        this.nombreProducto = nombreProducto;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(LocalDate fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public int getStockInicial() {
        return stockInicial;
    }

    public void setStockInicial(int stockInicial) {
        this.stockInicial = stockInicial;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    @Override
    public String toString() {
        return "Lote: " + "Id Lote= " + idLote + ", Id Producto= " + idProducto + ", Fecha Vencimiento= " + fechaVencimiento + ", Fecha Producción= " + fechaProduccion + ", Stock Inicial= " + stockInicial + ", Stock Actual= " + stockActual + ", Nombre Producto= " + nombreProducto;
    }
}
