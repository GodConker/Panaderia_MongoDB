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
@Table(name = "Producto")  // Aquí se indica el nombre de la tabla en la base de datos
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Usamos IDENTIY para que el valor del ID sea autoincremental
    @Column(name = "idProducto")  // Se mapea al nombre de la columna en la base de datos
    private Long id;  // Aquí el tipo de datos es Long por convención JPA, aunque en la base de datos es INT

    @Column(name = "nombre", length = 100, nullable = false)  // Mapea la columna 'nombre'
    private String nombre;

    @Column(name = "marca", length = 100, nullable = false)  // Mapea la columna 'marca'
    private String marca;

    @Column(name = "precio", precision = 10, scale = 2, nullable = false)  // Mapea la columna 'precio'
    private Double precio;

    @Column(name = "descripcion", columnDefinition = "TEXT")  // Mapea la columna 'descripcion'
    private String descripcion;

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Producto[ id=" + id + " ]";
    }

}