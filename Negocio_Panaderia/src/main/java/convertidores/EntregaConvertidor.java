/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidores;

import dtos.EmpleadoDTO;
import dtos.EntregaDTO;
import dtos.ProductoDTO;
import entidades.Empleado;
import entidades.Entrega;
import entidades.Producto;
import entidades.Tienda;
import java.util.ArrayList;
import java.util.List;

public class EntregaConvertidor {

    // Convertir de Entidad a DTO
    public static EntregaDTO aDTO(Entrega entrega) {
        EntregaDTO entregaDTO = new EntregaDTO();
        entregaDTO.setId(entrega.getIdAsString());  // Convertir el ObjectId a String
        entregaDTO.setFechaEntrega(entrega.getFechaEntrega());
        entregaDTO.setMontoTotal(entrega.getMontoTotal());

        // Convertir Tienda
        if (entrega.getTienda() != null) {
            entregaDTO.setIdTienda(entrega.getTienda().getId());  // Ahora el ID es String, se asigna directamente
        }

        // Convertir Repartidor
        if (entrega.getRepartidor() != null) {
            Empleado repartidor = entrega.getRepartidor();
            EmpleadoDTO repartidorDTO = new EmpleadoDTO();
            repartidorDTO.setId(repartidor.getIdAsString());
            repartidorDTO.setNombre(repartidor.getNombre());
            repartidorDTO.setCargo(repartidor.getCargo());
            repartidorDTO.setSalario(repartidor.getSalario());
            entregaDTO.setRepartidor(repartidorDTO);
        }

        // Convertir Productos
        if (entrega.getProductos() != null) {
            List<ProductoDTO> productosDTO = new ArrayList<>();
            for (int i = 0; i < entrega.getProductos().size(); i++) {
                Producto producto = entrega.getProductos().get(i);
                ProductoDTO productoDTO = new ProductoDTO(producto.getNombre(), entrega.getCantidades().get(i), producto.getPrecio());
                productosDTO.add(productoDTO);
            }
            entregaDTO.setProductos(productosDTO);
        }

        return entregaDTO;
    }

    // Convertir de DTO a Entidad
    public static Entrega aEntidad(EntregaDTO dto) {
        Entrega entrega = new Entrega();

        // Convertir de String a ObjectId para el ID de la Entrega
        entrega.setIdFromString(dto.getId());  // Convertir el ID de String a ObjectId

        // Asignar la fecha de entrega
        entrega.setFechaEntrega(dto.getFechaEntrega());
        entrega.setMontoTotal(dto.getMontoTotal());

        // Convertir Tienda
        if (dto.getIdTienda() != null) {
            Tienda tienda = new Tienda();
            tienda.setId(dto.getIdTienda());  // Ahora el ID es String, se asigna directamente
            entrega.setTienda(tienda);
        }

        // Convertir Repartidor
        if (dto.getRepartidor() != null) {
            Empleado repartidor = EmpleadoConvertidor.aEntidad(dto.getRepartidor());
            entrega.setRepartidor(repartidor);
        }

        // Convertir los productos
        if (dto.getProductos() != null) {
            List<Producto> productos = new ArrayList<>();
            List<Integer> cantidades = new ArrayList<>();
            List<Double> precios = new ArrayList<>();
            for (ProductoDTO productoDTO : dto.getProductos()) {
                Producto producto = new Producto();
                producto.setNombre(productoDTO.getNombre());
                producto.setPrecio(productoDTO.getPrecio());
                productos.add(producto);
                cantidades.add(productoDTO.getCantidad());
                precios.add(productoDTO.getPrecio());
            }
            entrega.setProductos(productos);
            entrega.setCantidades(cantidades);
            entrega.setPrecios(precios);
        }

        return entrega;
    }
}
