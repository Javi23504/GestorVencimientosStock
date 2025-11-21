package gvs.modelo;

import java.time.LocalDate;

/**
 *
 * @author Javi
 */
public class Producto {

    private int idProducto;
    private String codigoBarra;
    private String nombreProducto;
    private String marca;
    private int idCategoria;
    private LocalDate fechaVencimiento;
    public Producto() {
    }

    public Producto(int idProducto, String codigoBarra, String nombreProducto, String marca, int idCategoria, LocalDate fechaVencimiento, int stock) {
        this.idProducto = idProducto;
        this.codigoBarra = codigoBarra;
        this.nombreProducto = nombreProducto;
        this.marca = marca;
        this.idCategoria = idCategoria;
        this.fechaVencimiento = fechaVencimiento;
        this.stock = stock;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Producto:" + "Id Producto= " + idProducto + ", Código De Barra= " + codigoBarra + ", Nombre Producto= " + nombreProducto + ", Marca= " + marca + ", Id Categoría= " + idCategoria + ", Fecha Vencimiento= " + fechaVencimiento + ", Stock= " + stock + '}';
    }

    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
