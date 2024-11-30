/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
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
 * Clase DAO para manejar las operaciones CRUD de la entidad 'Empleado' con MongoDB.
 */
public class EmpleadoDAO implements IEmpleadoDAO {

    private final MongoCollection<Document> coleccion;

    public EmpleadoDAO() {
        // Conexión a MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase baseDatos = mongoClient.getDatabase("panaderia");
        this.coleccion = baseDatos.getCollection("empleado");
    }

    // Método auxiliar para convertir un Document a Empleado
    private Empleado convertirDocumentoAEmpleado(Document doc) {
        if (doc == null) {
            return null;
        }

        Object salarioObj = doc.get("salario");
        Double salario = null;

        if (salarioObj instanceof Integer) {
            salario = ((Integer) salarioObj).doubleValue();
        } else if (salarioObj instanceof Double) {
            salario = (Double) salarioObj;
        }

        return new Empleado(
                doc.getObjectId("_id"),
                doc.getString("nombre"),
                doc.getString("puesto"),
                salario
        );
    }

    // Método auxiliar para convertir Empleado a Document
    private Document convertirEmpleadoADocumento(Empleado empleado) {
        Document documento = new Document("_id", empleado.getId())
                .append("nombre", empleado.getNombre())
                .append("puesto", empleado.getCargo())
                .append("salario", empleado.getSalario());
        return documento;
    }

    @Override
    public List<Empleado> obtenerTodosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                empleados.add(convertirDocumentoAEmpleado(cursor.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    @Override
    public Empleado obtenerEmpleadoPorID(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            Document doc = coleccion.find(Filters.eq("_id", objectId)).first();
            return convertirDocumentoAEmpleado(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean agregarEmpleado(Empleado empleado) {
        try {
            coleccion.insertOne(convertirEmpleadoADocumento(empleado));
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
            Document actualizacion = new Document("$set", convertirEmpleadoADocumento(empleado));
            coleccion.updateOne(filtro, actualizacion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarEmpleado(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            coleccion.deleteOne(Filters.eq("_id", objectId));
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
                empleados.add(convertirDocumentoAEmpleado(cursor.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    @Override
    public List<Empleado> obtenerRepartidores() {
        List<Empleado> repartidores = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find(Filters.eq("puesto", "Repartidor")).iterator()) {
            while (cursor.hasNext()) {
                repartidores.add(convertirDocumentoAEmpleado(cursor.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repartidores;
    }

    public Empleado buscarPorId(String idRepartidor) {
        return obtenerEmpleadoPorID(idRepartidor);
    }

    @Override
    public Empleado obtenerRepartidorPorId(String id) {
        return obtenerEmpleadoPorID(id);
    }

    @Override
    public Empleado buscarPorNombre(String nombre) {
        try {
            Document doc = coleccion.find(Filters.eq("nombre", nombre)).first();
            return convertirDocumentoAEmpleado(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
