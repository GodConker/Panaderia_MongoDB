/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import business.objects.EmpleadoBO;
import business.objects.EntregaBO;
import daos.EmpleadoDAO;
import daos.EntregaDAO;
import dtos.EmpleadoDTO;
import dtos.EntregaDTO;
import entidades.Empleado;
import interfaces.IEmpleadoDAO;
import interfaces.IEntregaDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danie
 */
public class Control {

    private final EmpleadoBO empleadoBO; // Instancia de EmpleadoBO
    private final EntregaBO entregaBO;   // Instancia de EntregaBO

    // Constructor que inicializa los BO con sus DAOs
    public Control() {
        // Crear los DAOs necesarios
        IEmpleadoDAO empleadoDAO = new EmpleadoDAO(); // Configura el DAO según tu implementación
        IEntregaDAO entregaDAO = new EntregaDAO();    // Configura el DAO según tu implementación

        // Crear los BOs inyectando sus DAOs
        this.empleadoBO = new EmpleadoBO(empleadoDAO);
        this.entregaBO = new EntregaBO(entregaDAO);
    }

    public List<EmpleadoDTO> obtenerRepartidores() {
        try {
            // Obtener los repartidores como entidades desde la capa de negocio
            List<Empleado> empleados = empleadoBO.obtenerRepartidores();

            // Convertir las entidades a DTOs antes de retornarlas
            List<EmpleadoDTO> empleadosDTO = new ArrayList<>();
            for (Empleado empleado : empleados) {
                EmpleadoDTO dto = new EmpleadoDTO();
                dto.setId(empleado.getId().toString()); // Convertir ObjectId a String
                dto.setNombre(empleado.getNombre());
                dto.setCargo(empleado.getCargo());
                dto.setSalario(empleado.getSalario());
                empleadosDTO.add(dto);
            }

            return empleadosDTO;
        } catch (Exception e) {
            // Manejar excepciones si es necesario
            throw new RuntimeException("Error al obtener los repartidores: " + e.getMessage(), e);
        }
    }

    // Método para obtener un repartidor específico por su ID
    public EmpleadoDTO obtenerRepartidorPorId(String id) {
        try {
            // Llamar al BO para obtener el repartidor como entidad
            Empleado empleado = empleadoBO.obtenerRepartidorPorId(id);

            if (empleado == null) {
                throw new RuntimeException("No se encontró un repartidor con el ID: " + id);
            }

            // Convertir la entidad a DTO
            EmpleadoDTO dto = new EmpleadoDTO();
            dto.setId(empleado.getId().toString()); // Convertir ObjectId a String
            dto.setNombre(empleado.getNombre());
            dto.setCargo(empleado.getCargo());
            dto.setSalario(empleado.getSalario());
            return dto;
        } catch (Exception e) {
            // Manejar excepciones si es necesario
            throw new RuntimeException("Error al obtener el repartidor por ID: " + e.getMessage(), e);
        }
    }

    public void registrarEntrega(EntregaDTO entregaDTO) {
        try {
            EntregaBO entregaBusinessObject = new EntregaBO(new EntregaDAO());
            entregaBusinessObject.registrarEntrega(entregaDTO); // Llama al BO para registrar la entrega
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar la entrega: " + e.getMessage(), e);
        }
    }

    // Método estático para crear una instancia de EntregaBO
    public static EntregaBO crearEntregaBO() {
        // Crear una instancia de EntregaDAO (puedes usar la conexión a MongoDatabase si es necesario)
        IEntregaDAO entregaDAO = new EntregaDAO();
        // Retornar una instancia de EntregaBO con el DAO inyectado
        return new EntregaBO(entregaDAO);
    }

//    // Instancia estática de EmpleadoBO
//    private static final EmpleadoBO empleadoBO = null;
//
//    // Método para crear EntregaBO
//    public static EntregaBO crearEntregaBO() {
//        // Crear la instancia de EntregaDAO
//        IEntregaDAO entregaDAO = new EntregaDAO();  // Aquí debes pasar la configuración de la base de datos
//        return new EntregaBO(entregaDAO); // Retornar la instancia de EntregaBO
//    }
//
//    public static EmpleadoBO getEmpleadoBO(IEmpleadoDAO empleadoDAO) {
//        // Inyectar el DAO al BO
//        return new EmpleadoBO(empleadoDAO);
//    }
//
//    public static EntregaBO getEntregaBO(IEntregaDAO entregaDAO) {
//        // Inyectar el DAO al BO
//        return new EntregaBO(entregaDAO);
//    }
//    
//    // Aquí obtenemos el DAO y lo inyectamos en el BO
//    public static EmpleadoBO getEmpleadoBO(MongoDatabase baseDatos) {
//        IEmpleadoDAO empleadoDAO = new EmpleadoDAO(baseDatos); // Crear DAO y pasarlo al BO
//        return new EmpleadoBO(empleadoDAO); // Crear el BO y devolverlo
//    }
//    
//    // Método para obtener la instancia de EmpleadoBO sin pasarle parámetros
//    public static EmpleadoBO getEmpleadoBO() {
//        // Crear el DAO sin parámetros ni conexión a la base de datos desde la UI
//        IEmpleadoDAO empleadoDAO = new EmpleadoDAO(); // Esto debería encargarse de la conexión interna
//        return new EmpleadoBO(empleadoDAO); // Crear y devolver el EmpleadoBO
//    }
//    
//    
}
