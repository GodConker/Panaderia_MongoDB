/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;

/**
 * Clase que representa la entidad 'Tienda' en MongoDB
 */
public class Tienda implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String nombre;
    private String ubicacionCoordenadas;
    private String telefono;
    private String direccion;

    // Constructor vacío
    public Tienda() {
    }

    // Constructor con parámetros
    public Tienda(String id, String nombre, String ubicacionCoordenadas, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacionCoordenadas = ubicacionCoordenadas;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Constructor sin ID (cuando se genera automáticamente en MongoDB)
    public Tienda(String nombre, String ubicacionCoordenadas, String telefono, String direccion) {
        this.nombre = nombre;
        this.ubicacionCoordenadas = ubicacionCoordenadas;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAsString() {
        return id != null ? id : null;
    }

    public void setIdFromString(String id) {
        this.id = (id != null) ? id : null;
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
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entidades.Tienda[ id=" + id + " ]";
    }
}
