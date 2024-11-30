/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.objects;

import entidades.Empleado;
import interfaces.IEmpleadoDAO;
import java.util.List;

/**
 *
 * @author danie
 */
public class EmpleadoBO {

    private final IEmpleadoDAO empleadoDAO; // DAO inyectado para manejar la persistencia

    public EmpleadoBO(IEmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO; // Inicializar el DAO
    }

    // Obtener una lista de repartidores
    public List<Empleado> obtenerRepartidores() throws Exception {
        return empleadoDAO.obtenerRepartidores(); // Delegar al DAO
    }

    // Método para obtener un repartidor por su ID
    public Empleado obtenerRepartidorPorId(String id) throws Exception {
        return empleadoDAO.buscarPorId(id); // Delegar al método del DAO
    }
}