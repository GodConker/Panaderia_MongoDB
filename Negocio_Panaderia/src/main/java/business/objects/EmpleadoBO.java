/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.objects;

import entidades.Empleado;
import interfaces.IEmpleadoDAO;
import java.util.List;
import org.bson.types.ObjectId;

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

    // Método para obtener un repartidor por su ID (ahora usando int idEmpleado)
    public Empleado obtenerRepartidorPorId(int idEmpleado) throws Exception {
        return empleadoDAO.buscarPorId(idEmpleado); // Delegar al método del DAO
    }

    public boolean actualizarEmpleado(Empleado empleado) {
        try {
            // Llamar al DAO para realizar la actualización en la base de datos
            return empleadoDAO.actualizarEmpleado(empleado);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el empleado: " + e.getMessage(), e);
        }
    }

    public List<Empleado> obtenerEmpleados() {
        try {
            // Llamar al DAO para obtener la lista de empleados
            return empleadoDAO.obtenerEmpleados();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los empleados: " + e.getMessage(), e);
        }
    }

    public Empleado obtenerEmpleadoPorId(ObjectId objectId) {
        try {
            // Llamar al DAO para obtener el empleado usando el ID
            return empleadoDAO.obtenerEmpleadoPorId(objectId);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el empleado por ID: " + e.getMessage(), e);
        }
    }

    public boolean agregarEmpleado(Empleado empleado) {
        try {
            if (empleado.getId() == null) {
                empleado.setId(new ObjectId()); // Generar un nuevo ObjectId si es necesario
            }
            empleadoDAO.agregarEmpleado(empleado);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar el empleado: " + e.getMessage(), e);
        }
    }

    public boolean eliminarEmpleado(String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID no puede ser null o vacío.");
            }
            // Verificar si el empleado existe
            Empleado empleado = empleadoDAO.obtenerEmpleadoPorID(id);
            if (empleado == null) {
                throw new RuntimeException("Empleado no encontrado con ID: " + id);
            }
            // Eliminar empleado
            return empleadoDAO.eliminarEmpleado(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el empleado: " + e.getMessage(), e);
        }
    }

}
