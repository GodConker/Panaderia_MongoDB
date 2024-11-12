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
@Table(name = "Produccion")  // Mapea la tabla 'Produccion' en la base de datos
public class Produccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Asumimos que el ID es autoincrementable
    @Column(name = "idProduccion")  // Mapea la columna 'idProduccion'
    private Long id;

    @Column(name = "fechaProduccion")  // Mapea la columna 'fechaProduccion'
    @Temporal(TemporalType.DATE)  // Especifica que es una fecha (sin hora)
    private Date fechaProduccion;

    @ManyToOne(fetch = FetchType.LAZY)  // Relación muchos a uno con Empleado
    @JoinColumn(name = "id_empleado", referencedColumnName = "idEmpleado")  // Mapea la clave foránea
    private Empleado empleado;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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