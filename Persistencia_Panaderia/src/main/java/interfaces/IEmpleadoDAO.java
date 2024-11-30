/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Empleado;
import java.util.List;

/**
 * Interfaz para operaciones de persistencia sobre la entidad Empleado.
 */
public interface IEmpleadoDAO {

    // Obtener una lista de repartidores
    List<Empleado> obtenerRepartidores() throws Exception;

    // Obtener un repartidor por ID
    Empleado obtenerRepartidorPorId(String id) throws Exception;

    // Agregar un nuevo empleado
    boolean agregarEmpleado(Empleado empleado);

    // Buscar un empleado por su nombre
    Empleado buscarPorNombre(String nombre) throws Exception;

    // Buscar empleados por nombre
    List<Empleado> buscarEmpleadosPorNombre(String nombre) throws Exception;

    // Eliminar un empleado por ID
    boolean eliminarEmpleado(String id);

    // Actualizar los datos de un empleado
    boolean actualizarEmpleado(Empleado empleado);

    // Obtener un empleado por su ID
    Empleado obtenerEmpleadoPorID(String id);

    // Obtener todos los empleados
    List<Empleado> obtenerTodosEmpleados();
}
