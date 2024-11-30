/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.objects;

import convertidores.EntregaConvertidor;
import dtos.EntregaDTO;
import entidades.Entrega;
import excepciones.BOException;
import interfaces.IEntregaDAO;
import interfaces.IInventarioDAO;

/**
 *
 * @author danie
 */
public class EntregaBO {

    private final IEntregaDAO entregaDAO;
    private final IInventarioDAO inventarioDAO;

    public EntregaBO(IEntregaDAO entregaDAO, IInventarioDAO inventarioDAO) {
        this.entregaDAO = entregaDAO;
        this.inventarioDAO = inventarioDAO;
    }

    public void registrarEntrega(Entrega entregaEntidad) throws BOException {
        if (entregaEntidad.getMontoTotal() <= 0) {
            throw new BOException("El monto total debe ser mayor a 0.");
        }

        // Llamar al DAO para guardar la entrega
        entregaDAO.guardarEntrega(entregaEntidad);
    }

    public int calcularPaquetesDisponibles(int cantidadDisponible) {
        return Math.min(cantidadDisponible / 6, 10);
    }

    public int calcularPaquetesDisponiblesPorProducto(String idProducto) {
        // Obtener la cantidad disponible del producto desde InventarioDAO
        int cantidadDisponible = inventarioDAO.obtenerCantidadDisponiblePorProducto(idProducto);

        // Calcular el número de paquetes disponibles
        return calcularPaquetesDisponibles(cantidadDisponible);
    }

}
