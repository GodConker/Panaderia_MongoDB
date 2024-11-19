/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import business.objects.EntregaBO;
import daos.EntregaDAO;
import interfaces.IEntregaDAO;

/**
 *
 * @author danie
 */
public class Control {
    public static EntregaBO crearEntregaBO() {
        // Crear la instancia de EntregaDAO
        IEntregaDAO entregaDAO = new EntregaDAO();  // Aquí debes pasar la configuración de la base de datos
        return new EntregaBO(entregaDAO); // Retornar la instancia de EntregaBO
    }
}
