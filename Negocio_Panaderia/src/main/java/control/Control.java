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
                dto.setId(empleado.getIdAsString());
                dto.setNombre(empleado.getNombre());
                dto.setPuesto(empleado.getPuesto());
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
            Empleado empleado = new Empleado();
            empleado.setNombre(empleadoDTO.getNombre());
            empleado.setPuesto(empleadoDTO.getPuesto());
            empleado.setSalario(empleadoDTO.getSalario());

            // Llamar a la lógica del BO
            boolean resultado = empleadoBO.agregarEmpleado(empleado);
            if (resultado) {
                JOptionPane.showMessageDialog(null, "Empleado agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            return resultado;
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar empleado: " + e.getMessage(), e);
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
            dto.setPuesto(empleado.getPuesto());
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
                if ("Repartidor".equals(empleado.getPuesto())) {
                    EmpleadoDTO dto = new EmpleadoDTO();
                    dto.setId(empleado.getId().toString());
                    dto.setNombre(empleado.getNombre());
                    dto.setPuesto(empleado.getPuesto());
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

    public boolean actualizarEmpleado(EmpleadoDTO empleadoDTO) {
        try {
            // Verificar si el ID del empleado está presente y no es vacío
            String idEmpleado = empleadoDTO.getId();
            if (idEmpleado == null || idEmpleado.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID del empleado no puede ser null o vacío.");
            }

            // Crear ObjectId a partir del ID
            ObjectId objectId = new ObjectId(idEmpleado);

            // Obtener el empleado desde la base de datos usando el ID
            Empleado empleado = empleadoBO.obtenerEmpleadoPorId(objectId);

            if (empleado == null) {
                throw new RuntimeException("No se encontró un empleado con el ID: " + idEmpleado);
            }

            // Conservar el ID del empleado y solo actualizar los campos permitidos
            empleado.setNombre(empleadoDTO.getNombre());  // Actualizar nombre
            empleado.setPuesto(empleadoDTO.getPuesto());    // Actualizar cargo
            empleado.setSalario(empleadoDTO.getSalario()); // Actualizar salario

            // Llamar a la lógica del BO para actualizar el empleado
            boolean resultado = empleadoBO.actualizarEmpleado(empleado);

            return resultado;
        } catch (Exception e) {
            // Capturar cualquier error y lanzarlo como RuntimeException
            throw new RuntimeException("Error al actualizar el empleado: " + e.getMessage(), e);
        }
    }
    
    public boolean eliminarEmpleado(String idEmpleado) {
        try {
            if (idEmpleado == null || idEmpleado.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID del empleado no puede ser null o vacío.");
            }

            // Crear ObjectId a partir del ID
            ObjectId objectId = new ObjectId(idEmpleado);

            // Obtener el empleado desde la base de datos usando el ID
            Empleado empleado = empleadoBO.obtenerEmpleadoPorId(objectId);

            if (empleado == null) {
                throw new RuntimeException("No se encontró un empleado con el ID: " + idEmpleado);
            }
            
            System.out.println(idEmpleado);
            boolean resultado = empleadoBO.eliminarEmpleado(idEmpleado);
            System.out.println(resultado);
            
            return resultado;
        }catch (Exception e) {
            throw new RuntimeException("Error al eliminar el empleado: " + e.getMessage(), e);
        }
    }
}
