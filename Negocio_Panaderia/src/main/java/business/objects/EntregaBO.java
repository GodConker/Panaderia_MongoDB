/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.objects;

import entidades.Entrega;
import excepciones.BOException;
import interfaces.IEntregaDAO;
import interfaces.IInventarioDAO;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    public String registrarEntrega(Entrega entregaEntidad) throws BOException {
        if (entregaEntidad.getMontoTotal() <= 0) {
            throw new BOException("El monto total debe ser mayor a 0.");
        }

        // Llamar al DAO para guardar la entrega y retornar el ID generado
        return entregaDAO.guardarEntrega(entregaEntidad);
    }

    public void programarCambioEstado(String idEntrega, String nuevoEstado) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            try {
                // Cambiar el estado de la entrega usando el método en el DAO
                entregaDAO.actualizarEstadoPorId(idEntrega, nuevoEstado);  // Esta es la lógica de persistencia.
            } catch (Exception e) {
                System.err.println("Error al actualizar el estado: " + e.getMessage());
            }
        }, 1, TimeUnit.HOURS); // Programar para 1 hora después
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

    public List<Entrega> obtenerEntregasPorFecha(Date fechaInicio, Date fechaFin) {
        // Suponiendo que tienes un DAO para obtener las entregas de la base de datos
        return entregaDAO.obtenerEntregasPorRangoFecha(fechaInicio, fechaFin);
    }
}
