/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entrega implements Serializable {

    private static final long serialVersionUID = 1L;

    private ObjectId id;

    private Date fechaEntrega;

    private Tienda tienda;

    private Empleado repartidor;

    // Relación directa con Producto
    private List<Producto> productos;

    private double montoTotal;

    private String estado;

    // Listas auxiliares para cantidades y precios
    private List<Integer> cantidades;
    private List<Double> precios;

    // Constructores
    public Entrega() {
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
        this.precios = new ArrayList<>();
        this.estado = "En proceso de entrega";
    }

    public Entrega(Date fechaEntrega, Tienda tienda, Empleado repartidor, List<Producto> productos, double montoTotal) {
        this.fechaEntrega = fechaEntrega;
        this.tienda = tienda;
        this.repartidor = repartidor;
        this.productos = productos;
        this.montoTotal = montoTotal;
    }

    public Entrega(ObjectId id, Date fechaEntrega, Tienda tienda) {
        this.id = id;
        this.fechaEntrega = fechaEntrega;
        this.tienda = tienda;
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public Empleado getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Empleado repartidor) {
        this.repartidor = repartidor;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public void setCantidades(List<Integer> cantidades) {
        this.cantidades = cantidades;
    }

    public void setPrecios(List<Double> precios) {
        this.precios = precios;
    }

    /**
     * Retorna el ID como un String.
     *
     * @return El ID en formato String.
     */
    public String getIdAsString() {
        return id != null ? id.toString() : null;
    }

    /**
     * Convierte un String en ObjectId y lo asigna al campo ID.
     *
     * @param id El ID en formato String.
     */
    public void setIdFromString(String id) {
        this.id = (id != null) ? new ObjectId(id) : null;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Producto> getProductos() {
        if (productos == null) {
            productos = new ArrayList<>();
        }
        return productos;
    }

    public List<Integer> getCantidades() {
        if (cantidades == null) {
            cantidades = new ArrayList<>();
        }
        return cantidades;
    }

    public List<Double> getPrecios() {
        if (precios == null) {
            precios = new ArrayList<>();
        }
        return precios;
    }

    @Override
    public String toString() {
        return "Entrega{"
                + "id=" + (id != null ? id.toString() : "null")
                + ", fechaEntrega=" + fechaEntrega
                + ", tienda=" + (tienda != null ? tienda.getNombre() : "null")
                + ", repartidor=" + (repartidor != null ? repartidor.getNombre() : "null")
                + ", productos=" + productos
                + ", montoTotal=" + montoTotal
                + ", estado='" + estado + '\''
                + '}';
    }
}
