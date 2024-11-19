/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author danie
 */
public class DAOException extends Exception {

    // Constructor por defecto
    public DAOException() {
        super();
    }

    // Constructor con mensaje
    public DAOException(String message) {
        super(message);
    }

    // Constructor con mensaje y causa
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor con mensaje, causa y control de StackTrace
    public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}