/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.objects;

import entidades.Inventario;
import interfaces.IInventarioBO;
import interfaces.IInventarioDAO;

/**
 *
 * @author danie
 */
public class InventarioBO implements IInventarioBO {

    private final IInventarioDAO inventarioDAO;

    public InventarioBO(IInventarioDAO inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
    }

    @Override
    public int obtenerCantidadDisponible(String idProducto) {
        return inventarioDAO.obtenerCantidadDisponiblePorProducto(idProducto);
    }
    
    public Inventario obtenerInventarioPorId(String id) {
        return inventarioDAO.obtenerInventarioPorID(id);
    }
    
    public boolean actualizarInventario(Inventario inventario) {
        try {
            return inventarioDAO.actualizarInventario(inventario);
        }catch(Exception e) {
            throw new RuntimeException("Error al actualizar el inventario: " + e.getMessage(), e);
        }
    }
}

