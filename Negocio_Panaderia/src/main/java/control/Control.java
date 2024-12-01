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
import org.bson.types.ObjectId;
import javax.swing.*;
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

    // Obtener todos los empleados (Repartidores, Cajeros y Panaderos)
    public List<EmpleadoDTO> obtenerEmpleados() {
        try {
            List<Empleado> empleados = empleadoBO.obtenerEmpleados();
            List<EmpleadoDTO> empleadosDTO = new ArrayList<>();
            for (Empleado empleado : empleados) {
                EmpleadoDTO dto = new EmpleadoDTO();
                dto.setId(empleado.getId().toString());
                dto.setNombre(empleado.getNombre());
                dto.setCargo(empleado.getCargo());
                dto.setSalario(empleado.getSalario());
                empleadosDTO.add(dto);
            }
            return empleadosDTO;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los empleados: " + e.getMessage(), e);
        }
    }

 public boolean agregarEmpleado(EmpleadoDTO empleadoDTO) {
    try {
        // Validar datos del empleado
        if (empleadoDTO.getNombre().isEmpty() || empleadoDTO.getCargo() == null || empleadoDTO.getSalario() <= 0) {
            return false;
        }

        // Validar que el cargo sea válido
        if (!(empleadoDTO.getCargo().equals("Panadero") || 
              empleadoDTO.getCargo().equals("Cajero") || 
              empleadoDTO.getCargo().equals("Repartidor"))) {
            return false;
        }

        // Crear el objeto de tipo Empleado para la base de datos
        Empleado empleado = new Empleado(new ObjectId(), empleadoDTO.getNombre(), empleadoDTO.getCargo(), empleadoDTO.getSalario());

        // Llamar al DAO para agregar el empleado
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        empleadoDAO.agregarEmpleado(empleado);

        return true; // Registro exitoso
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    public EmpleadoDTO obtenerEmpleadoPorId(String idEmpleado) {
        try {
            ObjectId objectId = new ObjectId(idEmpleado);
            Empleado empleado = empleadoBO.obtenerEmpleadoPorId(objectId);

            if (empleado == null) {
                throw new RuntimeException("No se encontró un empleado con el ID: " + idEmpleado);
            }

            EmpleadoDTO dto = new EmpleadoDTO();
            dto.setId(empleado.getId().toString());
            dto.setNombre(empleado.getNombre());
            dto.setCargo(empleado.getCargo());
            dto.setSalario(empleado.getSalario());
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el empleado por ID: " + e.getMessage(), e);
        }
    }

    public List<EmpleadoDTO> obtenerRepartidores() {
        // Implementar lógica para obtener empleados con cargo "Repartidor"
        try {
            List<Empleado> empleados = empleadoBO.obtenerEmpleados();
            List<EmpleadoDTO> repartidores = new ArrayList<>();
            for (Empleado empleado : empleados) {
                if ("Repartidor".equals(empleado.getCargo())) {
                    EmpleadoDTO dto = new EmpleadoDTO();
                    dto.setId(empleado.getId().toString());
                    dto.setNombre(empleado.getNombre());
                    dto.setCargo(empleado.getCargo());
                    dto.setSalario(empleado.getSalario());
                    repartidores.add(dto);
                }
            }
            return repartidores;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los repartidores: " + e.getMessage(), e);
        }
    }

    public List<TiendaDTO> obtenerTiendas() {
        try {
            List<Tienda> tiendas = tiendaBO.obtenerTiendas();
            List<TiendaDTO> tiendasDTO = new ArrayList<>();
            for (Tienda tienda : tiendas) {
                TiendaDTO dto = new TiendaDTO();
                dto.setId(tienda.getId().toString());
                dto.setNombre(tienda.getNombre());
                dto.setDireccion(tienda.getDireccion());
                tiendasDTO.add(dto);
            }
            return tiendasDTO;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las tiendas: " + e.getMessage(), e);
        }
    }

    public String obtenerIdTiendaPorNombre(String nombreTiendaSeleccionada) {
        try {
            Tienda tienda = tiendaBO.obtenerTiendaPorNombre(nombreTiendaSeleccionada);
            return tienda != null ? tienda.getId().toString() : null;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la tienda por nombre: " + e.getMessage(), e);
        }
    }

    public void registrarEntrega(EntregaDTO entregaDTO) {
        try {
            entregaBO.registrarEntrega(entregaDTO);
            JOptionPane.showMessageDialog(null, "Entrega registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar la entrega: " + e.getMessage(), e);
        }
    }

    public int calcularPaquetesDisponibles(String idProducto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
