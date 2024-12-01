package business.objects;

import entidades.Empleado;
import interfaces.IEmpleadoDAO;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author danie
 */
public class EmpleadoBO {

    private final IEmpleadoDAO empleadoDAO; // DAO inyectado para manejar la persistencia

    public EmpleadoBO(IEmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO; // Inicializar el DAO
    }

    /**
     * Obtiene una lista de todos los empleados.
     *
     * @return Una lista de objetos Empleado.
     */
    public List<Empleado> obtenerEmpleados() {
        try {
            // Llamar al DAO para obtener la lista de empleados
            return empleadoDAO.obtenerEmpleados();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los empleados: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene un empleado a partir del ID de empleado.
     *
     * @param objectId El ID del empleado como ObjectId.
     * @return El empleado correspondiente o null si no existe.
     */
    public Empleado obtenerEmpleadoPorId(ObjectId objectId) {
        try {
            // Llamar al DAO para obtener el empleado usando el ID
            return empleadoDAO.obtenerEmpleadoPorId(objectId);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el empleado por ID: " + e.getMessage(), e);
        }
    }
}
