/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import business.objects.EmpleadoBO;
import business.objects.EntregaBO;
import business.objects.InventarioBO;
import business.objects.ProductoBO;
import business.objects.TiendaBO;
import convertidores.EmpleadoConvertidor;
import convertidores.TiendaConvertidor;
import daos.EmpleadoDAO;
import daos.EntregaDAO;
import daos.InventarioDAO;
import daos.ProductoDAO;
import daos.TiendaDAO;
import dtos.EmpleadoDTO;
import dtos.EntregaDTO;
import dtos.InventarioDTO;
import dtos.ProductoDTO;
import dtos.TiendaDTO;
import entidades.Empleado;
import entidades.Entrega;
import entidades.Inventario;
import entidades.Producto;
import entidades.Tienda;
import interfaces.IEmpleadoDAO;
import interfaces.IEntregaDAO;
import interfaces.IInventarioDAO;
import interfaces.IProductoDAO;
import interfaces.ITiendaDAO;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;

/**
 *
 * @author danie
 */
public class Control {

    private final EmpleadoBO empleadoBO; // Instancia de EmpleadoBO
    private final EntregaBO entregaBO;   // Instancia de EntregaBO
    private final InventarioBO inventarioBO;
    private final TiendaBO tiendaBO;
    private final ProductoBO productoBO;

    public Control() {
        // Instanciar DAOs
        IEmpleadoDAO empleadoDAO = new EmpleadoDAO();
        IEntregaDAO entregaDAO = new EntregaDAO();
        IInventarioDAO inventarioDAO = new InventarioDAO();
        ITiendaDAO tiendaDAO = new TiendaDAO();
        IProductoDAO productoDAO = new ProductoDAO();

        // Instanciar BOs con los DAOs
        this.empleadoBO = new EmpleadoBO(empleadoDAO);
        this.entregaBO = new EntregaBO(entregaDAO, inventarioDAO);
        this.inventarioBO = new InventarioBO(inventarioDAO);
        this.tiendaBO = new TiendaBO(tiendaDAO);
        this.productoBO = new ProductoBO((ProductoDAO) productoDAO);
    }

    public List<ProductoDTO> obtenerProductos() {
        // Obtener la lista de productos desde ProductoBO
        List<Producto> productos = productoBO.obtenerTodosProductos(); // Obtiene los productos
        List<ProductoDTO> productosDTO = new ArrayList<>();

        // Recorrer cada producto y convertirlo a ProductoDTO
        for (Producto producto : productos) {
            ProductoDTO dto = new ProductoDTO();

            // Establecer el nombre y el precio del producto
            dto.setId(producto.getIdAsString());
            dto.setNombre(producto.getNombre());
            dto.setPrecio(producto.getPrecio());
            dto.setDescripcion(producto.getDescripcion());

            // Obtener la cantidad disponible desde InventarioBO usando el ID del producto
            int cantidadDisponible = inventarioBO.obtenerCantidadDisponible(producto.getIdAsString());
            dto.setCantidad(cantidadDisponible); // Establecer la cantidad disponible

            // Agregar el ProductoDTO a la lista
            productosDTO.add(dto);
        }

        // Devolver la lista de ProductoDTO
        return productosDTO;
    }
    
    public int obtenerCantidadDisponible(String id) {
        return inventarioBO.obtenerCantidadDisponible(id);
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
            List<Tienda> tiendas = tiendaBO.obtenerTiendas();
            List<TiendaDTO> tiendasDTO = new ArrayList<>();
            for (Tienda tienda : tiendas) {
                TiendaDTO dto = new TiendaDTO();
                dto.setId(tienda.getId());  // Ya no es necesario usar getIdAsString, simplemente usa getId() que ya es un String
                dto.setNombre(tienda.getNombre());
                dto.setUbicacionCoordenadas(tienda.getUbicacionCoordenadas());
                dto.setTelefono(tienda.getTelefono());
                dto.setDireccion(tienda.getDireccion());
                tiendasDTO.add(dto);
            }
            return tiendasDTO;
        } catch (Exception e) {
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
            Tienda tienda = tiendaBO.obtenerTiendaPorNombre(nombreTienda);
            if (tienda == null) {
                return null;
            }
            return tienda.getId(); // El ID ahora es un String directamente
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la tienda por nombre: " + e.getMessage(), e);
        }
    }

    public String registrarEntrega(EntregaDTO entregaDTO) {
        try {
            if (entregaDTO.getRepartidor() == null) {
                throw new RuntimeException("Debe asignarse un repartidor a la entrega.");
            }

            EmpleadoDTO repartidorDTO = entregaDTO.getRepartidor();
            Empleado repartidorEntidad = EmpleadoConvertidor.aEntidad(repartidorDTO);

            Entrega entregaEntidad = new Entrega();

            LocalDateTime fechaHoyMasUnaHora = LocalDateTime.now(ZoneOffset.UTC).plusHours(1);
            Date fechaEntrega = Date.from(fechaHoyMasUnaHora.atZone(ZoneId.of("UTC")).toInstant());
            entregaEntidad.setFechaEntrega(fechaEntrega);

            entregaEntidad.setMontoTotal(entregaDTO.getMontoTotal());

            // Ahora simplemente asignamos el ID como String, ya que es un String directamente
            TiendaDTO tiendaDTO = new TiendaDTO();
            tiendaDTO.setId(entregaDTO.getIdTienda()); // Usamos getId() sin necesidad de convertir a ObjectId
            Tienda tiendaEntidad = TiendaConvertidor.aEntidad(tiendaDTO);
            entregaEntidad.setTienda(tiendaEntidad);

            entregaEntidad.setRepartidor(repartidorEntidad);

            if (entregaDTO.getProductos() == null || entregaDTO.getProductos().isEmpty()) {
                throw new RuntimeException("No hay productos en la entrega.");
            }

            List<Producto> productos = new ArrayList<>();
            List<Integer> cantidades = new ArrayList<>();
            List<Double> precios = new ArrayList<>();
            for (ProductoDTO productoDTO : entregaDTO.getProductos()) {
                Producto producto = new Producto();
                producto.setNombre(productoDTO.getNombre());
                producto.setPrecio(productoDTO.getPrecio());
                productos.add(producto);
                cantidades.add(productoDTO.getCantidad());
                precios.add(productoDTO.getPrecio());
            }

            entregaEntidad.setProductos(productos);
            entregaEntidad.setCantidades(cantidades);
            entregaEntidad.setPrecios(precios);

            String idEntrega = entregaBO.registrarEntrega(entregaEntidad);
            entregaBO.programarCambioEstado(idEntrega, "En proceso de entrega");

            return idEntrega;
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar la entrega: " + e.getMessage(), e);
        }
    }

    public void programarCambioEstado(String idEntrega) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            try {
                // Llamamos al método de BO para programar el cambio de estado
                entregaBO.programarCambioEstado(idEntrega, "Entregado");
                System.out.println("Estado actualizado a 'Entregado' para la entrega con ID: " + idEntrega);
            } catch (Exception e) {
                System.err.println("Error al actualizar el estado: " + e.getMessage());
            }
        }, 1, TimeUnit.HOURS); // Programar para 1 hora después
    }

    public int calcularPaquetesDisponibles(String idProducto) {
        try {
            // Delegar la lógica al BO
            return entregaBO.calcularPaquetesDisponiblesPorProducto(idProducto);
        } catch (Exception e) {
            throw new RuntimeException("Error al calcular los paquetes disponibles: " + e.getMessage(), e);
        }
    }

    public List<EntregaDTO> obtenerEntregasPorFecha(Date fechaInicio, Date fechaFin) {
        try {
            List<Entrega> entregas = entregaBO.obtenerEntregasPorFecha(fechaInicio, fechaFin);
            List<EntregaDTO> entregasDTO = new ArrayList<>();
            for (Entrega entrega : entregas) {
                EntregaDTO dto = new EntregaDTO();
                dto.setId(entrega.getIdAsString());
                dto.setFechaEntrega(entrega.getFechaEntrega());
                dto.setMontoTotal(entrega.getMontoTotal());

                List<ProductoDTO> productosDTO = new ArrayList<>();
                List<Integer> cantidades = new ArrayList<>();
                for (int i = 0; i < entrega.getProductos().size(); i++) {
                    Producto producto = entrega.getProductos().get(i);
                    ProductoDTO productoDTO = new ProductoDTO();
                    productoDTO.setNombre(producto.getNombre());
                    productoDTO.setPrecio(producto.getPrecio());
                    productosDTO.add(productoDTO);
                    cantidades.add(entrega.getCantidades().get(i));
                }
                dto.setProductos(productosDTO);
                dto.setCantidades(cantidades);

                if (entrega.getTienda() != null) {
                    String idTienda = entrega.getTienda().getId();
                    if (idTienda != null) {
                        dto.setIdTienda(idTienda);
                        System.out.println("Asignando ID Tienda al DTO: " + idTienda);
                    } else {
                        System.out.println("El ID de la tienda en Entrega es nulo.");
                    }
                } else {
                    System.out.println("La tienda en Entrega es nula.");
                }

                if (entrega.getRepartidor() != null) {
                    EmpleadoDTO empleadoDTO = new EmpleadoDTO();
                    empleadoDTO.setId(entrega.getRepartidor().getIdAsString());
                    empleadoDTO.setNombre(entrega.getRepartidor().getNombre());
                    dto.setRepartidor(empleadoDTO);
                }

                entregasDTO.add(dto);
            }

            return entregasDTO;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las entregas por fecha: " + e.getMessage(), e);
        }
    }

    public int obtenerCantidadProducto(String idProducto) {
        try {
            // Obtener la cantidad disponible del producto desde el inventario
            return inventarioBO.obtenerCantidadDisponible(idProducto);
        } catch (Exception e) {
            System.out.println("Error al obtener la cantidad del producto: " + e.getMessage());
            return 0;  // Retornar 0 en caso de error
        }
    }

    public String obtenerNombreTiendaPorId(String idTienda) {
        // Suponiendo que TiendaBO tiene un método para obtener una tienda por ID
        Tienda tienda = tiendaBO.obtenerTiendaPorID(idTienda);
        return tienda != null ? tienda.getNombre() : "Tienda no encontrada";
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
            empleado.setCargo(empleadoDTO.getCargo());    // Actualizar cargo
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
            return empleadoBO.eliminarEmpleado(idEmpleado);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error al eliminar el empleado: " + e.getMessage());
            return false;
        }
    }

    public boolean agregarEmpleado(EmpleadoDTO empleadoDTO) {
        try {
            Empleado empleado = new Empleado();
            empleado.setNombre(empleadoDTO.getNombre());
            empleado.setCargo(empleadoDTO.getCargo());
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

    // Obtener todos los empleados (Repartidores, Cajeros y Panaderos)
    public List<EmpleadoDTO> obtenerEmpleados() {
        try {
            List<Empleado> empleados = empleadoBO.obtenerEmpleados();
            List<EmpleadoDTO> empleadosDTO = new ArrayList<>();
            for (Empleado empleado : empleados) {
                EmpleadoDTO dto = new EmpleadoDTO();
                dto.setId(empleado.getIdAsString());
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
    
    public boolean actualizarProducto(ProductoDTO productoDTO) {
        try {
            // Verificar si el ID del producto está presente y no es vacío
            String idProducto = productoDTO.getId();
            if (idProducto == null || idProducto.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID del empleado no puede ser null o vacío.");
            }

            // Crear ObjectId a partir del ID
            ObjectId objectId = new ObjectId(idProducto);

            // Obtener el producto desde la base de datos usando el ID
            Producto producto = productoBO.obtenerProductoPorID(objectId);

            if (producto == null) {
                throw new RuntimeException("No se encontró un empleado con el ID: " + idProducto);
            }

            // Conservar el ID del producto y solo actualizar los campos permitidos
            producto.setDescripcion(productoDTO.getDescripcion());    // Actualizar descripcion
            producto.setPrecio(productoDTO.getPrecio()); // Actualizar precio

            // Llamar a la lógica del BO para actualizar el producto
            boolean resultado = productoBO.actualizarProducto(producto);

            return resultado;
        } catch (Exception e) {
            // Capturar cualquier error y lanzarlo como RuntimeException
            throw new RuntimeException("Error al actualizar el empleado: " + e.getMessage(), e);
        }
    }
    
    public boolean actualizarInventario(InventarioDTO inventarioDTO) {
        try {
            // Verificar si el ID del empleado está presente y no es vacío
            String idInventario = inventarioDTO.getId();
            if (idInventario == null || idInventario.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID del empleado no puede ser null o vacío.");
            }

            // Obtener el empleado desde la base de datos usando el ID
            Inventario inventario = inventarioBO.obtenerInventarioPorId(idInventario);

            if (inventario == null) {
                throw new RuntimeException("No se encontró un empleado con el ID: " + idInventario);
            }

            // Conservar el ID del empleado y solo actualizar los campos permitidos
            inventario.setCantidadDisponible(inventarioDTO.getCantidadDisponible());  // Actualizar cantidad disponible

            // Llamar a la lógica del BO para actualizar el empleado
            boolean resultado = inventarioBO.actualizarInventario(inventario);

            return resultado;
        } catch (Exception e) {
            // Capturar cualquier error y lanzarlo como RuntimeException
            throw new RuntimeException("Error al actualizar el empleado: " + e.getMessage(), e);
        }
    }
    
    public void desinventariar(String id, int cantidad) {
        Inventario inventario = inventarioBO.obtenerInventarioPorId(id);
        
        inventario.setCantidadDisponible(inventario.getCantidadDisponible() - cantidad);
        
        inventarioBO.actualizarInventario(inventario);
    }
}
