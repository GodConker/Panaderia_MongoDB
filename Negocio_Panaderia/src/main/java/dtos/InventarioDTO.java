package dtos;

import java.util.Date;

/**
 * DTO para la clase Inventario
 * 
 * @author danie
 */
public class InventarioDTO {

    private String id;
    private int cantidadDisponible;
    private Date fechaActualizacion;
    private ProductoDTO producto;

    public InventarioDTO() {
    }

    public InventarioDTO(String id, int cantidadDisponible, Date fechaActualizacion, ProductoDTO producto) {
        this.id = id;
        this.cantidadDisponible = cantidadDisponible;
        this.fechaActualizacion = fechaActualizacion;
        this.producto = producto;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "InventarioDTO{" +
               "id='" + id + '\'' +
               ", cantidadDisponible=" + cantidadDisponible +
               ", fechaActualizacion=" + fechaActualizacion +
               ", producto=" + producto +
               '}';
    }
}
