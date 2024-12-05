/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.objects;

import daos.ProductoDAO;
import entidades.Producto;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author danie
 */
public class ProductoBO {
    private final ProductoDAO productoDAO;

    public ProductoBO(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    // Método para obtener todos los productos
    public List<Producto> obtenerTodosProductos() {
        return productoDAO.obtenerTodosProductos(); // Llama al DAO para obtener productos
    }

    // Método para obtener un producto por ID
    public Producto obtenerProductoPorID(ObjectId id) {
        return productoDAO.obtenerProductoPorID(id); // Llama al DAO para obtener producto por ID
    }
    
    public boolean actualizarProducto(Producto producto) {
        return productoDAO.actualizarProducto(producto);
    }
}
