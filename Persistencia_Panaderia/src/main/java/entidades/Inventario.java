package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "Inventario")  // Mapea la tabla 'Inventario' en la base de datos
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Asumimos que el ID es autoincrementable
    @Column(name = "idInventario")  // Mapea la columna 'idInventario'
    private Long id;

    @Column(name = "cantidadDisponible")  // Mapea la columna 'cantidadDisponible'
    private int cantidadDisponible;

    @Column(name = "fechaActualizacion")  // Mapea la columna 'fechaActualizacion'
    @Temporal(TemporalType.DATE)  // Especifica que es una fecha (sin hora)
    private Date fechaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY)  // Relación muchos a uno con Producto
    @JoinColumn(name = "id_producto", referencedColumnName = "idProducto")  // Mapea la clave foránea
    private Producto producto;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Inventario[ id=" + id + " ]";
    }
}