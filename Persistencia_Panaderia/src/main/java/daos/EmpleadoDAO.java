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
import dtos.EmpleadoDTO;
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

    public EmpleadoDAO() {
        // Establecer la conexión directamente dentro del constructor con la nueva forma de crear MongoClient
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");  // Usando MongoClients.create()
        MongoDatabase baseDatos = mongoClient.getDatabase("panaderia"); // Nombre de la base de datos
        this.coleccion = baseDatos.getCollection("empleado"); // Obtener la colección "empleados"
    }

    // Constructor con base de datos ya establecida
    public EmpleadoDAO(MongoDatabase baseDatos) {
        this.coleccion = baseDatos.getCollection("empleado");
    }

    // Método auxiliar para convertir un Document a Empleado
    private Empleado convertirADocumentoAEmpleado(Document doc) {
        Object salarioObj = doc.get("salario");
        Double salario = null;

        // Verificar el tipo de dato y convertirlo a Double si es necesario
        if (salarioObj instanceof Integer) {
            salario = ((Integer) salarioObj).doubleValue();
        } else if (salarioObj instanceof Double) {
            salario = (Double) salarioObj;
        }

        return new Empleado(
                doc.getObjectId("_id"), // ID de empleado
                doc.getString("nombre"), // Nombre del empleado
                doc.getString("puesto"), // Puesto del empleado
                salario // Salario del empleado
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

    @Override
    public Empleado obtenerEmpleadoPorId(ObjectId objectId) {
        // Buscar el empleado por su ObjectId
        Document doc = coleccion.find(Filters.eq("_id", objectId)).first();

        // Si encontramos el documento, lo convertimos a Empleado
        return (doc != null) ? convertirADocumentoAEmpleado(doc) : null;
    }

    @Override
    public List<Empleado> obtenerEmpleados() {
        return obtenerTodosEmpleados(); // Método que retorna todos los empleados
    }

    // Método agregado para agregar un EmpleadoDTO
    public void agregarEmpleadoDTO(EmpleadoDTO empleadoDTO) {
        try {
            // Crear un Empleado usando el DTO
            Empleado empleado = new Empleado(
                    new ObjectId(), // Nuevo ObjectId para el empleado
                    empleadoDTO.getNombre(),
                    empleadoDTO.getCargo(),
                    empleadoDTO.getSalario()
            );

            // Insertar el Empleado en la base de datos
            Document doc = convertirAEmpleadoADocumento(empleado);
            coleccion.insertOne(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Empleado buscarPorId(int idEmpleado) {
        // Buscar el empleado por su idEmpleado (no por _id)
        Document doc = coleccion.find(Filters.eq("idEmpleado", idEmpleado)).first();

        // Si encontramos el documento, lo convertimos a Empleado
        if (doc != null) {
            return convertirADocumentoAEmpleado(doc);  // Método que convierte el Document a un objeto Empleado
        } else {
            // Si no se encuentra el empleado, retornamos null
            return null;
        }
    }
}