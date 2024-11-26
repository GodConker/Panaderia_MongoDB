/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * Clase que representa la entidad 'Empleado' en MongoDB
 */

public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;


    private ObjectId id;

    private String nombre;
    private String cargo;
    private Double salario;

    // Constructor vacío
    public Empleado() {
    }

    // Constructor con parámetros
    public Empleado(ObjectId id, String nombre, String cargo, Double salario) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
    }

    // Constructor sin ID (puede usarse cuando el ID se genera automáticamente)
    public Empleado(String nombre, String cargo, Double salario) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    // Métodos para encapsular ObjectId como String
    public String getIdAsString() {
        return id != null ? id.toString() : null;
    }

    public void setIdFromString(String id) {
        this.id = (id != null) ? new ObjectId(id) : null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Empleado[ id=" + id + " ]";
    }
}
