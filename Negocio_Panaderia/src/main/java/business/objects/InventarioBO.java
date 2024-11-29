/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.objects;

import interfaces.IInventarioBO;
import interfaces.IInventarioDAO;

/**
 *
 * @author danie
 */
public class InventarioBO implements IInventarioBO {

    private IInventarioDAO inventarioDAO;

    public InventarioBO(IInventarioDAO inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
    }

    @Override
    public int obtenerCantidadDisponible(String idProducto) {
        return inventarioDAO.obtenerCantidadDisponiblePorProducto(idProducto);
    }
}

