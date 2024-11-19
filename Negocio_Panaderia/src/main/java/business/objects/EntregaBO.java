/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.objects;

import convertidores.EntregaConvertidor;
import dtos.EntregaDTO;
import entidades.Entrega;
import excepciones.BOException;
import interfaces.IEntregaDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class EntregaBO {
    private final IEntregaDAO entregaDAO;

    public EntregaBO(IEntregaDAO entregaDAO) {
        this.entregaDAO = entregaDAO;
    }

    public void registrarEntrega(EntregaDTO entregaDTO) {
        if (entregaDTO.getMontoTotal() <= 0) {
            try {
                throw new BOException("El monto total debe ser mayor a 0.");
            } catch (BOException ex) {
                Logger.getLogger(EntregaBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Convertir el DTO a Entidad
        Entrega entrega = EntregaConvertidor.aEntidad(entregaDTO);

        // Llamar a la capa de persistencia
        entregaDAO.guardarEntrega(entrega);
    }
}
