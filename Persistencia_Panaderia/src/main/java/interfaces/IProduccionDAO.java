/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Produccion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author danie
 */
public interface IProduccionDAO {

    List<Produccion> obtenerTodasProducciones();

    Produccion obtenerProduccionPorID(int id);

    boolean agregarProduccion(Produccion produccion);

    boolean actualizarProduccion(Produccion produccion);

    boolean eliminarProduccion(int id);

    List<Produccion> obtenerProduccionPorFecha(Date fecha);

    List<Produccion> obtenerProduccionPorProductoID(int productoId);
}
