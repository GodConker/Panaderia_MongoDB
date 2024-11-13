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
 * Clase que representa la entidad 'Produccion' en MongoDB
 */
@Entity("Produccion")  // Nombre de la colección en MongoDB
public class Produccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id  // El identificador de la entidad (MongoDB usa ObjectId)
    private ObjectId id;

    private Date fechaProduccion;

    @Reference  // Relación con Empleado (Referencia en MongoDB)
    private Empleado empleado;

    // Constructor vacío
    public Produccion() {
    }

    // Constructor con parámetros
    public Produccion(ObjectId id, Date fechaProduccion, Empleado empleado) {
        this.id = id;
        this.fechaProduccion = fechaProduccion;
        this.empleado = empleado;
    }

    // Constructor sin ID (cuando se genera automáticamente en MongoDB)
    public Produccion(Date fechaProduccion, Empleado empleado) {
        this.fechaProduccion = fechaProduccion;
        this.empleado = empleado;
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(Date fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Produccion)) {
            return false;
        }
        Produccion other = (Produccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Produccion[ id=" + id + " ]";
    }
}