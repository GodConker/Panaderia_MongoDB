/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.objects;

import interfaces.IInventarioDAO;

/**
 *
 * @author danie
 */
public class InventarioBO {
    
    private final IInventarioDAO inventarioDAO;

    public InventarioBO(IInventarioDAO inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
    }
    
    

    public int obtenerCantidadPorProducto(String nombreProducto) {
        try {
            return inventarioDAO.obtenerCantidadPorProducto(nombreProducto); // Delegación al DAO
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar el inventario del producto: " + nombreProducto, e);
        }
    }
}
