/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Empleado;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author danie
 */
public interface IEmpleadoDAO {

    List<Empleado> obtenerTodosEmpleados();
    
    List<Empleado> obtenerEmpleados();

    Empleado obtenerEmpleadoPorID(int id);
    
    Empleado obtenerEmpleadoPorId(ObjectId objectId);

    boolean agregarEmpleado(Empleado empleado);

    boolean actualizarEmpleado(Empleado empleado);

    boolean eliminarEmpleado(String id);

    List<Empleado> buscarEmpleadosPorNombre(String nombre);
    
    Empleado buscarPorId(String idRepartidor);
    
    List<Empleado> obtenerRepartidores();
    
    Empleado obtenerRepartidorPorId(String id) throws Exception;
    
    Empleado buscarPorId(int idEmpleado);
    
    Empleado obtenerEmpleadoPorID(String id);
}
