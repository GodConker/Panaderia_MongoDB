/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.objects;

import entidades.Tienda;
import interfaces.ITiendaDAO;
import java.util.List;

/**
 *
 * @author danie
 */
public class TiendaBO {

    private final ITiendaDAO tiendaDAO;

    public TiendaBO(ITiendaDAO tiendaDAO) {
        this.tiendaDAO = tiendaDAO;
    }

    /**
     * Obtiene todas las tiendas desde la base de datos.
     *
     * @return Lista de objetos Tienda.
     */
    public List<Tienda> obtenerTodasLasTiendas() {
        try {
            // Llama al método del DAO para obtener las tiendas
            return tiendaDAO.obtenerTodasTiendas();
        } catch (Exception e) {
            // Manejo de errores
            throw new RuntimeException("Error al obtener las tiendas: " + e.getMessage(), e);
        }
    }

    public int obtenerCantidadDisponibleProducto(String nombreProducto) {
        // Llamar al DAO para obtener la cantidad disponible en inventario
        return tiendaDAO.obtenerCantidadDisponibleProducto(nombreProducto);
    }

    public void reducirCantidadInventario(String nombreProducto, int cantidadEntregada) {
        tiendaDAO.actualizarCantidadInventario(nombreProducto, -cantidadEntregada);
    }
}
