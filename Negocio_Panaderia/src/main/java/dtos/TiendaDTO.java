/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author danie
 */
/**
 * Clase DTO para representar la información de una tienda.
 */
public class TiendaDTO {

    private String id; // Representación en texto del ObjectId
    private String nombre;
    private String ubicacionCoordenadas;
    private String telefono;
    private String direccion;

    // Constructor vacío
    public TiendaDTO() {
    }

    // Constructor con parámetros
    public TiendaDTO(String id, String nombre, String ubicacionCoordenadas, String telefono, String direccion) {
        this.id = id;
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
    public String toString() {
        return "TiendaDTO{"
                + "id='" + id + '\''
                + ", nombre='" + nombre + '\''
                + ", ubicacionCoordenadas='" + ubicacionCoordenadas + '\''
                + ", telefono='" + telefono + '\''
                + ", direccion='" + direccion + '\''
                + '}';
    }
}
