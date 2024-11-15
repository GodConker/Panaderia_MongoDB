/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Empleado;
import java.util.List;

/**
 *
 * @author danie
 */
public interface IEmpleadoDAO {

    List<Empleado> obtenerTodosEmpleados();

    Empleado obtenerEmpleadoPorID(int id);

    boolean agregarEmpleado(Empleado empleado);

    boolean actualizarEmpleado(Empleado empleado);

    boolean eliminarEmpleado(int id);

    List<Empleado> buscarEmpleadosPorNombre(String nombre);
}
