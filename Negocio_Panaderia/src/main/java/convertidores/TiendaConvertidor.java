/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidores;

import dtos.TiendaDTO;
import entidades.Tienda;

/**
 *
 * @author danie
 */
public class TiendaConvertidor {
    
    public TiendaDTO aDTO(Tienda tienda) {
    return new TiendaDTO(
        tienda.getIdAsString(),
        tienda.getNombre(),
        tienda.getUbicacionCoordenadas(),
        tienda.getTelefono(),
        tienda.getDireccion()
    );
}
    
    public Tienda aEntidad(TiendaDTO tiendaDTO) {
        Tienda tienda = new Tienda();
        tienda.setIdFromString(tiendaDTO.getId());
        tienda.setNombre(tiendaDTO.getNombre());
        tienda.setUbicacionCoordenadas(tiendaDTO.getUbicacionCoordenadas());
        tienda.setTelefono(tiendaDTO.getTelefono());
        tienda.setDireccion(tiendaDTO.getDireccion());
        return tienda;
    }
}
