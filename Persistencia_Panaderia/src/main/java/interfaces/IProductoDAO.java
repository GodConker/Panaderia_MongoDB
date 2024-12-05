/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Producto;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author danie
 */
public interface IProductoDAO {

    List<Producto> obtenerTodosProductos();

    Producto obtenerProductoPorID(ObjectId id);

    boolean agregarProducto(Producto producto);

    boolean actualizarProducto(Producto producto);

    void eliminarProducto(int idProducto);

    List<Producto> buscarProductosPorNombre(String nombre);
}
