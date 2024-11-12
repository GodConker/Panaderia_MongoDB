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
@Table(name = "Entrega")  // Mapea la tabla 'Entrega' en la base de datos
public class Entrega implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Asumimos que el ID es autoincrementable
    @Column(name = "idEntrega")  // Mapea la columna 'idEntrega'
    private Long id;

    @Column(name = "fechaEntrega")  // Mapea la columna 'fechaEntrega'
    @Temporal(TemporalType.DATE)  // Especifica que es una fecha (sin hora)
    private Date fechaEntrega;

    @ManyToOne(fetch = FetchType.LAZY)  // Relación muchos a uno con Tienda
    @JoinColumn(name = "id_tienda", referencedColumnName = "idTienda")  // Mapea la clave foránea
    private Tienda tienda;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Entrega[ id=" + id + " ]";
    }
}