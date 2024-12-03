/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Tienda;
import java.util.List;

/**
 *
 * @author danie
 */
public interface ITiendaDAO {

    List<Tienda> obtenerTodasLasTiendas();

    Tienda obtenerTiendaPorID(int id);

    boolean agregarTienda(Tienda tienda);

    boolean actualizarTienda(Tienda tienda);

    boolean eliminarTienda(int id);

    List<Tienda> buscarTiendasPorNombre(String nombre);
    
    Tienda buscarTiendaPorNombre(String nombre);
    
    Tienda obtenerTiendaPorID(String id);
}
