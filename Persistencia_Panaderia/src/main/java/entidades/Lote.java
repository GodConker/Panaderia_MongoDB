/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "Lote")  // Mapea la tabla 'Lote' en la base de datos
public class Lote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Asumimos que el ID es autoincrementable
    @Column(name = "idLote")  // Mapea la columna 'idLote'
    private Long id;

    @Column(name = "cantidadProducida")  // Mapea la columna 'cantidadProducida'
    private int cantidadProducida;

    @Column(name = "fechaProduccion")  // Mapea la columna 'fechaProduccion'
    @Temporal(TemporalType.DATE)  // Especifica que es una fecha (sin hora)
    private Date fechaProduccion;

    @Column(name = "fechaCaducidad")  // Mapea la columna 'fechaCaducidad'
    @Temporal(TemporalType.DATE)  // Especifica que es una fecha (sin hora)
    private Date fechaCaducidad;

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

    public int getCantidadProducida() {
        return cantidadProducida;
    }

    public void setCantidadProducida(int cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }

    public Date getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(Date fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
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
        if (!(object instanceof Lote)) {
            return false;
        }
        Lote other = (Lote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Lote[ id=" + id + " ]";
    }
}