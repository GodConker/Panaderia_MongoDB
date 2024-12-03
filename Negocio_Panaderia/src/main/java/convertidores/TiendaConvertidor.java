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
                tienda.getIdAsString(),  // Devolver el ID como String
                tienda.getNombre(),
                tienda.getUbicacionCoordenadas(),
                tienda.getTelefono(),
                tienda.getDireccion()
        );
    }

    public static Tienda aEntidad(TiendaDTO tiendaDTO) {
        Tienda tienda = new Tienda();

        // Asignar el ID directamente como String
        if (tiendaDTO.getId() != null) {
            tienda.setId(tiendaDTO.getId());  // No es necesario convertir, ya que ahora id es String
        }

        tienda.setNombre(tiendaDTO.getNombre());
        tienda.setUbicacionCoordenadas(tiendaDTO.getUbicacionCoordenadas());
        tienda.setTelefono(tiendaDTO.getTelefono());
        tienda.setDireccion(tiendaDTO.getDireccion());

        return tienda;
    }
}
