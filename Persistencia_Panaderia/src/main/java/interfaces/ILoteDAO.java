/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Lote;
import java.util.Date;
import java.util.List;

/**
 *
 * @author danie
 */
public interface ILoteDAO {

    List<Lote> obtenerTodosLotes();

    Lote obtenerLotePorID(int id);

    boolean agregarLote(Lote lote);

    boolean actualizarLote(Lote lote);

    boolean eliminarLote(int id);

    List<Lote> obtenerLotesPorFecha(Date fecha);

    List<Lote> obtenerLotesPorProductoID(int productoId);
}
