/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "Tienda")  // Aquí se indica el nombre de la tabla en la base de datos
public class Tienda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Usamos IDENTIY para que el valor del ID sea autoincremental
    @Column(name = "idTienda")  // Se mapea al nombre de la columna en la base de datos
    private Long id;  // Aquí el tipo de datos es Long por convención JPA, aunque en la base de datos es INT

    @Column(name = "nombre", length = 100, nullable = false)  // Mapea la columna 'nombre'
    private String nombre;

    @Column(name = "ubicacionCoordenadas", length = 100, nullable = true)  // Mapea la columna 'ubicacionCoordenadas'
    private String ubicacionCoordenadas;

    @Column(name = "telefono", length = 15, nullable = true)  // Mapea la columna 'telefono'
    private String telefono;

    @Column(name = "direccion", length = 255, nullable = true)  // Mapea la columna 'direccion'
    private String direccion;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacionCoordenadas() {
        return ubicacionCoordenadas;
    }

    public void setUbicacionCoordenadas(String ubicacionCoordenadas) {
        this.ubicacionCoordenadas = ubicacionCoordenadas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tienda)) {
            return false;
        }
        Tienda other = (Tienda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tienda[ id=" + id + " ]";
    }
    
}