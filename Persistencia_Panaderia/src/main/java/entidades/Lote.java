/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa la entidad 'Lote' en MongoDB
 */
@Entity("Lote")  // Nombre de la colección en MongoDB
public class Lote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id  // El identificador de la entidad (MongoDB usa ObjectId)
    private ObjectId id;

    private int cantidadProducida;
    private Date fechaProduccion;
    private Date fechaCaducidad;

    @Reference  // Relación con Producto (Referencia en MongoDB)
    private Producto producto;

    // Constructor vacío
    public Lote() {
    }

    // Constructor con parámetros
    public Lote(ObjectId id, int cantidadProducida, Date fechaProduccion, Date fechaCaducidad, Producto producto) {
        this.id = id;
        this.cantidadProducida = cantidadProducida;
        this.fechaProduccion = fechaProduccion;
        this.fechaCaducidad = fechaCaducidad;
        this.producto = producto;
    }

    // Constructor sin ID (cuando se genera automáticamente en MongoDB)
    public Lote(int cantidadProducida, Date fechaProduccion, Date fechaCaducidad, Producto producto) {
        this.cantidadProducida = cantidadProducida;
        this.fechaProduccion = fechaProduccion;
        this.fechaCaducidad = fechaCaducidad;
        this.producto = producto;
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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