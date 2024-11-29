/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import business.objects.EmpleadoBO;
import business.objects.EntregaBO;
import business.objects.InventarioBO;
import business.objects.TiendaBO;
import daos.EmpleadoDAO;
import daos.EntregaDAO;
import daos.InventarioDAO;
import daos.TiendaDAO;
import dtos.EmpleadoDTO;
import dtos.EntregaDTO;
import dtos.TiendaDTO;
import entidades.Empleado;
import entidades.Tienda;
import interfaces.IEmpleadoDAO;
import interfaces.IEntregaDAO;
import interfaces.IInventarioDAO;
import interfaces.ITiendaDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danie
 */
public class Control {

    private final EmpleadoBO empleadoBO; // Instancia de EmpleadoBO
    private final EntregaBO entregaBO;   // Instancia de EntregaBO
    private final InventarioBO inventarioBO;
    private final TiendaBO tiendaBO;

    public Control() {
        // Instanciar DAOs
        IEmpleadoDAO empleadoDAO = new EmpleadoDAO();
        IEntregaDAO entregaDAO = new EntregaDAO();
        IInventarioDAO inventarioDAO = new InventarioDAO();
        ITiendaDAO tiendaDAO = new TiendaDAO();

        // Instanciar BOs con los DAOs
        this.empleadoBO = new EmpleadoBO(empleadoDAO);
        this.entregaBO = new EntregaBO(entregaDAO, inventarioDAO);
        this.inventarioBO = new InventarioBO(inventarioDAO);
        this.tiendaBO = new TiendaBO(tiendaDAO);
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

    public List<TiendaDTO> obtenerTiendas() {
        try {
            // Obtener las tiendas como entidades desde la capa de negocio
            List<Tienda> tiendas = tiendaBO.obtenerTiendas();

            // Convertir las entidades a DTOs antes de retornarlas
            List<TiendaDTO> tiendasDTO = new ArrayList<>();
            for (Tienda tienda : tiendas) {
                TiendaDTO dto = new TiendaDTO();
                dto.setId(tienda.getIdAsString()); // Convertir ObjectId a String
                dto.setNombre(tienda.getNombre());
                dto.setUbicacionCoordenadas(tienda.getUbicacionCoordenadas());
                dto.setTelefono(tienda.getTelefono());
                dto.setDireccion(tienda.getDireccion());
                tiendasDTO.add(dto);
            }

            return tiendasDTO;
        } catch (Exception e) {
            // Manejar excepciones si es necesario
            throw new RuntimeException("Error al obtener las tiendas: " + e.getMessage(), e);
        }
    }

    public EmpleadoDTO obtenerRepartidorPorId(int idEmpleado) {
        try {
            // Obtener el repartidor usando idEmpleado
            Empleado empleado = empleadoBO.obtenerRepartidorPorId(idEmpleado);

            if (empleado == null) {
                throw new RuntimeException("No se encontró un repartidor con el idEmpleado: " + idEmpleado);
            }

            // Convertir la entidad a DTO
            EmpleadoDTO dto = new EmpleadoDTO();
            dto.setIdEmpleado(empleado.getIdEmpleado());  // Usar idEmpleado
            dto.setNombre(empleado.getNombre());
            dto.setCargo(empleado.getCargo());
            dto.setSalario(empleado.getSalario());
            return dto;
        } catch (Exception e) {
            // Manejar excepciones
            throw new RuntimeException("Error al obtener el repartidor por ID: " + e.getMessage(), e);
        }
    }

    public String obtenerIdTiendaPorNombre(String nombreTienda) {
        try {
            // Buscar la tienda por nombre utilizando TiendaBO
            Tienda tienda = tiendaBO.obtenerTiendaPorNombre(nombreTienda);

            // Si no se encuentra la tienda, retornar null
            if (tienda == null) {
                return null;
            }

            // Si la tienda se encuentra, retornar su ID como String
            return tienda.getIdAsString(); // Asegúrate de que getIdAsString() devuelve el ID como String
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al obtener la tienda por nombre: " + e.getMessage(), e);
        }
    }

    public void registrarEntrega(EntregaDTO entregaDTO) {
        try {
            // Delegar el registro al BO
            entregaBO.registrarEntrega(entregaDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar la entrega: " + e.getMessage(), e);
        }
    }

    public int calcularPaquetesDisponibles(String idProducto) {
        try {
            // Delegar la lógica al BO
            return entregaBO.calcularPaquetesDisponiblesPorProducto(idProducto);
        } catch (Exception e) {
            throw new RuntimeException("Error al calcular los paquetes disponibles: " + e.getMessage(), e);
        }
    }
}
