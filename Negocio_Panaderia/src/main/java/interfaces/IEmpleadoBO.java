/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Empleado;
import java.util.List;

/**
 * Interfaz para las operaciones de negocio relacionadas con el empleado.
 */
public interface IEmpleadoBO {

    // Método para obtener todos los empleados
    List<Empleado> obtenerTodosEmpleados();

    // Método para obtener un empleado por su ID
    Empleado obtenerEmpleadoPorID(int id);

    // Método para agregar un nuevo empleado
    boolean agregarEmpleado(Empleado empleado);

    // Método para actualizar los detalles de un empleado
    boolean actualizarEmpleado(Empleado empleado);

    // Método para eliminar un empleado
    boolean eliminarEmpleado(int id);

    // Método para buscar empleados por su nombre
    List<Empleado> buscarEmpleadosPorNombre(String nombre);

    // Método para buscar un empleado por su ID de repartidor
    Empleado buscarPorId(String idRepartidor);

    // Método para obtener una lista de repartidores
    List<Empleado> obtenerRepartidores();

    // Método para obtener un repartidor por su ID
    Empleado obtenerRepartidorPorId(String id) throws Exception;
}
