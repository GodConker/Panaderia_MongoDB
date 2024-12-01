/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Inventario;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Interfaz para definir los métodos de acceso a la colección de inventario.
 * Define operaciones CRUD y consultas específicas relacionadas con el inventario.
 * 
 * @author danie
 */
public interface IInventarioDAO {

    /**
     * Obtiene la lista completa del inventario.
     * 
     * @return Una lista de objetos Inventario.
     */
    List<Inventario> obtenerInventarioCompleto();

    /**
     * Obtiene un inventario por su ID único.
     * 
     * @param id El ID del inventario como ObjectId.
     * @return Un objeto Inventario si se encuentra; de lo contrario, null.
     */
    Inventario obtenerInventarioPorID(ObjectId id);

    /**
     * Actualiza la información de un inventario existente.
     * 
     * @param inventario El objeto Inventario con los datos actualizados.
     * @return true si la actualización fue exitosa; false en caso contrario.
     */
    boolean actualizarInventario(Inventario inventario);

    /**
     * Agrega un nuevo producto al inventario.
     * 
     * @param inventario El objeto Inventario que se desea agregar.
     * @return true si la inserción fue exitosa; false en caso contrario.
     */
    boolean agregarProductoAlInventario(Inventario inventario);

    /**
     * Elimina un producto del inventario por su ID.
     * 
     * @param id El ID único del inventario como ObjectId.
     * @return true si la eliminación fue exitosa; false en caso contrario.
     */
    boolean eliminarProductoDelInventario(ObjectId id);

    /**
     * Busca inventarios cuyo nombre coincida con un patrón específico.
     * 
     * @param nombre El patrón del nombre para buscar (soporte para expresiones regulares).
     * @return Una lista de objetos Inventario que coincidan con el criterio.
     */
    List<Inventario> buscarInventarioPorNombre(String nombre);

    /**
     * Obtiene la cantidad disponible de un producto en el inventario por su ID de producto.
     * 
     * @param idProducto El ID único del producto como ObjectId.
     * @return La cantidad disponible del producto o 0 si no se encuentra.
     */
    int obtenerCantidadDisponiblePorProducto(ObjectId idProducto);

   int obtenerCantidadDisponiblePorProducto(String idProducto);
}
