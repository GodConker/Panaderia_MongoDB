/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.objects;

import convertidores.EmpleadoConvertidor;
import dtos.EmpleadoDTO;
import entidades.Empleado;
import interfaces.IEmpleadoDAO;

/**
 *
 * @author danie
 */
public class EmpleadoBO {

    private final IEmpleadoDAO empleadoDAO;

    public EmpleadoBO() {
        this.empleadoDAO = null;
    }

    // Constructor de la clase, inyectando el DAO
    public EmpleadoBO(IEmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    public EmpleadoDTO obtenerRepartidorPorId(String idRepartidor) {
        // Aquí puedes usar el convertidor para convertir un Empleado a EmpleadoDTO
        Empleado empleado = empleadoDAO.buscarPorId(idRepartidor); // Esto devuelve un Empleado
        return EmpleadoConvertidor.aDTO(empleado); // Convertirlo a DTO
    }
}
