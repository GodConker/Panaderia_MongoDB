/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Entrega;
import java.util.Date;
import java.util.List;

/**
 *
 * @author danie
 */
public interface IEntregaDAO {

    List<Entrega> obtenerTodasEntregas();

    Entrega obtenerEntregaPorID(int id);

    boolean agregarEntrega(Entrega entrega);

    boolean actualizarEntrega(Entrega entrega);

    boolean eliminarEntrega(int id);

    List<Entrega> obtenerEntregasPorFecha(Date fecha);
    
    void guardarEntrega(Entrega entrega);
}
