/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Inventario;
import java.util.List;

/**
 *
 * @author danie
 */
public interface IInventarioDAO {

    List<Inventario> obtenerInventarioCompleto();

    Inventario obtenerInventarioPorID(String id);

    boolean actualizarInventario(Inventario inventario);

    boolean agregarProductoAlInventario(Inventario inventario);

    boolean eliminarProductoDelInventario(int id);

    List<Inventario> buscarInventarioPorNombre(String nombre);
    
    int obtenerCantidadDisponiblePorProducto(String idProducto);
}
