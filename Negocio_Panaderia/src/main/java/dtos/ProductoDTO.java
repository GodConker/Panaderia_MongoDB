/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author danie
 */
public class ProductoDTO {

    private String id;
    private String nombre;
    private int cantidad;
    private double precio;
    private String descripcion; // Nuevo atributo

    public ProductoDTO() {

    }
    
    public ProductoDTO(String nombre, int cantidad, double precio, String descripcion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    // Constructor de la clase ProductoDTO
    public ProductoDTO(String id, String nombre, int cantidad, double precio, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" + 
               "nombre=" + nombre + 
               ", cantidad=" + cantidad + 
               ", precio=" + precio + 
               ", descripcion=" + descripcion + 
               '}';
    }
}
