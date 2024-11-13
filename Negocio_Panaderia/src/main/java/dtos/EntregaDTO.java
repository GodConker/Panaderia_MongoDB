/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.Date;

/**
 * Clase DTO para la entidad 'Entrega'. 
 * Usada para transferir datos entre las capas de negocio y presentación.
 */
public class EntregaDTO {
    private String id;  // Usamos String en lugar de ObjectId
    private Date fechaEntrega;
    private String idTienda;  // Usamos String para representar el ID de la tienda

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(String idTienda) {
        this.idTienda = idTienda;
    }

    @Override
    public String toString() {
        return "EntregaDTO{" + "id=" + id + ", fechaEntrega=" + fechaEntrega + ", idTienda=" + idTienda + '}';
    }
}