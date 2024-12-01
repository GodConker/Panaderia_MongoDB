/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.objects;

import interfaces.IInventarioBO;
import interfaces.IInventarioDAO;
import org.bson.types.ObjectId;

/**
 * Clase BO para manejar la lógica de negocio relacionada con el inventario.
 */
public class InventarioBO implements IInventarioBO {

    private final IInventarioDAO inventarioDAO;

    public InventarioBO(IInventarioDAO inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
    }

    @Override
    public int obtenerCantidadDisponible(String idProducto) {
        try {
            ObjectId objectId = new ObjectId(idProducto); // Validación del formato de ObjectId
            return inventarioDAO.obtenerCantidadDisponiblePorProducto(objectId);
        } catch (IllegalArgumentException e) {
            System.err.println("El ID del producto no es válido: " + idProducto);
            return 0;
        }
    }
}
