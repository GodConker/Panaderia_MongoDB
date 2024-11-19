/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Empleado;
import interfaces.IEmpleadoDAO;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para manejar las operaciones CRUD de la entidad 'Empleado' con
 * MongoDB.
 */
public class EmpleadoDAO implements IEmpleadoDAO {

    private final MongoCollection<Document> coleccion;

    public EmpleadoDAO(MongoDatabase baseDatos) {
        // Conectamos a la colección "empleados" de la base de datos
        this.coleccion = baseDatos.getCollection("empleados");
    }

    // Método auxiliar para convertir un Document a Empleado
    private Empleado convertirADocumentoAEmpleado(Document doc) {
        return new Empleado(
                doc.getObjectId("_id"), // ID de empleado
                doc.getString("nombre"), // Nombre del empleado
                doc.getString("puesto"), // Puesto del empleado
                doc.getDouble("salario") // Salario del empleado
        );
    }

    // Método auxiliar para convertir Empleado a Document
    private Document convertirAEmpleadoADocumento(Empleado empleado) {
        return new Document("_id", empleado.getId())
                .append("nombre", empleado.getNombre())
                .append("puesto", empleado.getCargo())
                .append("salario", empleado.getSalario());
    }

    @Override
    public List<Empleado> obtenerTodosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                empleados.add(convertirADocumentoAEmpleado(doc));
            }
        }
        return empleados;
    }

    @Override
    public Empleado obtenerEmpleadoPorID(int id) {
        Document doc = coleccion.find(Filters.eq("_id", new ObjectId(id + ""))).first();
        return (doc != null) ? convertirADocumentoAEmpleado(doc) : null;
    }

    @Override
    public boolean agregarEmpleado(Empleado empleado) {
        try {
            Document doc = convertirAEmpleadoADocumento(empleado);
            coleccion.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarEmpleado(Empleado empleado) {
        try {
            Document filtro = new Document("_id", empleado.getId());
            Document actualizacion = new Document("$set", new Document("nombre", empleado.getNombre())
                    .append("puesto", empleado.getCargo())
                    .append("salario", empleado.getSalario()));
            coleccion.updateOne(filtro, actualizacion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarEmpleado(int id) {
        try {
            Document filtro = new Document("_id", new ObjectId(id + ""));
            coleccion.deleteOne(filtro);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Empleado> buscarEmpleadosPorNombre(String nombre) {
        List<Empleado> empleados = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find(Filters.eq("nombre", nombre)).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                empleados.add(convertirADocumentoAEmpleado(doc));
            }
        }
        return empleados;
    }

    @Override
    public Empleado buscarPorId(String idRepartidor) {
        // Convertimos la cadena 'idRepartidor' a ObjectId, ya que MongoDB usa ObjectId como identificador
        ObjectId objectId = new ObjectId(idRepartidor);

        // Buscar el empleado por su ObjectId
        Document doc = coleccion.find(Filters.eq("_id", objectId)).first();

        // Si encontramos el documento, lo convertimos a Empleado
        if (doc != null) {
            return convertirADocumentoAEmpleado(doc);
        } else {
            // Si no se encuentra el empleado, retornamos null
            return null;
        }
    }
}
