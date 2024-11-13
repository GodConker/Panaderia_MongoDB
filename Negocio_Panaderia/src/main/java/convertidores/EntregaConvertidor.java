/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidores;

import dtos.EntregaDTO;
import entidades.Entrega;
import entidades.Tienda;

public class EntregaConvertidor {

    public static EntregaDTO aDTO(Entrega entrega) {
        EntregaDTO entregaDTO = new EntregaDTO();
        entregaDTO.setId(entrega.getIdAsString());  // Convertir a String usando el nuevo método
        entregaDTO.setFechaEntrega(entrega.getFechaEntrega());

        if (entrega.getTienda() != null) {
            entregaDTO.setIdTienda(entrega.getTienda().getIdAsString());  // Convertir a String usando el nuevo método
        }

        return entregaDTO;
    }

    public static Entrega aEntidad(EntregaDTO entregaDTO) {
        Entrega entrega = new Entrega();
        entrega.setIdFromString(entregaDTO.getId());  // Convertir de String a ObjectId usando el nuevo método
        entrega.setFechaEntrega(entregaDTO.getFechaEntrega());

        if (entregaDTO.getIdTienda() != null) {
            Tienda tienda = new Tienda();
            tienda.setIdFromString(entregaDTO.getIdTienda());  // Convertir de String a ObjectId usando el nuevo método
            entrega.setTienda(tienda);
        }

        return entrega;
    }
}