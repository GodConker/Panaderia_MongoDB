/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase DTO para la entidad 'Entrega'. Usada para transferir datos entre las
 * capas de negocio y presentación.
 */
public class EntregaDTO {

    private String id;  // Usamos String en lugar de ObjectId
    private Date fechaEntrega;
    private String idTienda;  // Usamos String para representar el ID de la tienda
    private List<ProductoDTO> productos;
    private double montoTotal;
    private EmpleadoDTO repartidor;  // Cambio aquí para usar la entidad Empleado

    // Constructor de la clase EntregaDTO
    public EntregaDTO() {
        this.productos = new ArrayList<>(); // Inicializa la lista de productos
    }

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

    // Método para obtener los productos
    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }

    // Método para agregar un producto a la lista
    public void agregarProducto(ProductoDTO producto) {
        this.productos.add(producto);
    }

    // Método opcional para mostrar los productos (si es necesario)
    public void mostrarProductos() {
        for (ProductoDTO producto : productos) {
            System.out.println("Producto: " + producto.getNombre() + ", Cantidad: " + producto.getCantidad() + ", Precio: " + producto.getPrecio());
        }
    }

    // Método para establecer el monto total
    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    // Método para obtener el monto total
    public double getMontoTotal() {
        return montoTotal;
    }

    // Método para establecer el repartidor (EmpleadoDTO)
    public void setRepartidor(EmpleadoDTO repartidor) {
        this.repartidor = repartidor;
    }

    // Método para obtener el repartidor (EmpleadoDTO)
    public EmpleadoDTO getRepartidor() {
        return repartidor;
    }

    @Override
    public String toString() {
        return "EntregaDTO{"
                + "id='" + id + '\''
                + ", fechaEntrega=" + fechaEntrega
                + ", idTienda='" + idTienda + '\''
                + ", productos=" + productos
                + ", montoTotal=" + montoTotal
                + ", repartidor=" + (repartidor != null ? repartidor.getNombre() : "No asignado")
                + '}';
    }
}
