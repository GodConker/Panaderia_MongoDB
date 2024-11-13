/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidores;

import dtos.EmpleadoDTO;
import entidades.Empleado;

public class EmpleadoConvertidor {

    // Convierte un objeto Empleado a EmpleadoDTO
    public static EmpleadoDTO aDTO(Empleado empleado) {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setId(empleado.getIdAsString());  // Convertimos ObjectId a String
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setCargo(empleado.getCargo());
        empleadoDTO.setSalario(empleado.getSalario());

        return empleadoDTO;
    }

    // Convierte un objeto EmpleadoDTO a Empleado
    public static Empleado aEntidad(EmpleadoDTO empleadoDTO) {
        Empleado empleado = new Empleado();
        empleado.setIdFromString(empleadoDTO.getId());  // Convertimos String a ObjectId
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setCargo(empleadoDTO.getCargo());
        empleado.setSalario(empleadoDTO.getSalario());

        return empleado;
    }
}