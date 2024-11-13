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
 * Clase que representa la entidad 'Entrega' en MongoDB
 */
@Entity("Entrega")  // Nombre de la colección en MongoDB
public class Entrega implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id  // El identificador de la entidad
    private ObjectId id;

    private Date fechaEntrega;

    @Reference  // Relación con Tienda
    private Tienda tienda;

    public Entrega() {
    }

    public Entrega(ObjectId id, Date fechaEntrega, Tienda tienda) {
        this.id = id;
        this.fechaEntrega = fechaEntrega;
        this.tienda = tienda;
    }

    public Entrega(Date fechaEntrega, Tienda tienda) {
        this.fechaEntrega = fechaEntrega;
        this.tienda = tienda;
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Entrega)) {
            return false;
        }
        Entrega other = (Entrega) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entidades.Entrega[ id=" + id + " ]";
    }
}