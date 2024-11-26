/**
 * Clase que representa la entidad 'Inventario' en MongoDB
 */
package entidades;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author danie
 */

public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;


    private ObjectId id;

    private int cantidadDisponible;
    private Date fechaActualizacion;


    private Producto producto;

    public Inventario() {
    }

    public Inventario(ObjectId id, int cantidadDisponible, Date fechaActualizacion, Producto producto) {
        this.id = id;
        this.cantidadDisponible = cantidadDisponible;
        this.fechaActualizacion = fechaActualizacion;
        this.producto = producto;
    }

    public Inventario(int cantidadDisponible, Date fechaActualizacion, Producto producto) {
        this.cantidadDisponible = cantidadDisponible;
        this.fechaActualizacion = fechaActualizacion;
        this.producto = producto;
    }
    
    // Constructor para crear un objeto Inventario usando los parámetros de doc
    public Inventario(ObjectId id, Producto producto, int cantidadDisponible) {
        this.id = id;
        this.producto = producto;
        this.cantidadDisponible = cantidadDisponible;
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entidades.Inventario[ id=" + id + " ]";
    }
}