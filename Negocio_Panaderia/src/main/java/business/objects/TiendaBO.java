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
/**
 * Clase de negocio para manejar la lógica relacionada con Tiendas.
 */
public class TiendaBO {

    private final ITiendaDAO tiendaDAO;

    public TiendaBO(ITiendaDAO tiendaDAO) {
        this.tiendaDAO = tiendaDAO;
    }

    /**
     * Método para obtener todas las tiendas desde la capa de persistencia.
     *
     * @return Lista de entidades Tienda.
     * @throws RuntimeException Si ocurre algún error durante la operación.
     */
    public List<Tienda> obtenerTiendas() {
        try {
            // Llamar al DAO para obtener la lista de tiendas
            return tiendaDAO.obtenerTodasLasTiendas();
        } catch (Exception e) {
            // Manejar y encapsular excepciones
            throw new RuntimeException("Error al obtener las tiendas: " + e.getMessage(), e);
        }
    }

    public Tienda obtenerTiendaPorNombre(String nombreTienda) {
        try {
            // Llamada al DAO para buscar la tienda por nombre
            Tienda tienda = tiendaDAO.buscarTiendaPorNombre(nombreTienda);

            // Retornar la tienda encontrada
            return tienda;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al obtener la tienda por nombre: " + e.getMessage(), e);
        }
    }
}
