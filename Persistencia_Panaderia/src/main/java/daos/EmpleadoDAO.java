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

public class EmpleadoDAO implements IEmpleadoDAO {

    private final MongoCollection<Document> coleccion;

    public EmpleadoDAO() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase baseDatos = mongoClient.getDatabase("panaderia");
        this.coleccion = baseDatos.getCollection("empleado");
    }

    public EmpleadoDAO(MongoDatabase baseDatos) {
        this.coleccion = baseDatos.getCollection("empleado");
    }

    // Método auxiliar para convertir un Document a Empleado
    private Empleado convertirADocumentoAEmpleado(Document doc) {
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
    private Document convertirAEmpleadoADocumento(Empleado empleado) {
        return new Document("_id", empleado.getId())
                .append("nombre", empleado.getNombre())
                .append("puesto", empleado.getPuesto())
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
            // Si el empleado no tiene un ID, se crea un nuevo ObjectId
            if (empleado.getId() == null) {
                empleado.setId(new ObjectId());
            }
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
            // Filtrar por nombre del empleado
            Document filtro = new Document("_id", empleado.getId());
            Document actualizacion = new Document("$set", new Document("nombre", empleado.getNombre())
                    .append("puesto", empleado.getPuesto())
                    .append("salario", empleado.getSalario()));
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
            Document filtro = new Document("_id", objectId);
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
        ObjectId objectId = new ObjectId(idRepartidor);
        Document doc = coleccion.find(Filters.eq("_id", objectId)).first();
        return (doc != null) ? convertirADocumentoAEmpleado(doc) : null;
    }

    @Override
    public Empleado obtenerEmpleadoPorId(ObjectId objectId) {
        Document doc = coleccion.find(Filters.eq("_id", objectId)).first();
        return (doc != null) ? convertirADocumentoAEmpleado(doc) : null;
    }

    @Override
    public List<Empleado> obtenerEmpleados() {
        return obtenerTodosEmpleados();
    }

    // Método para agregar un EmpleadoDTO
    public void agregarEmpleadoDTO(EmpleadoDTO empleadoDTO) {
        try {
            Empleado empleado = new Empleado(
                    new ObjectId(), // Nuevo ObjectId para el empleado
                    empleadoDTO.getNombre(),
                    empleadoDTO.getPuesto(),
                    empleadoDTO.getSalario()
            );
            Document doc = convertirAEmpleadoADocumento(empleado);
            coleccion.insertOne(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Empleado buscarPorId(int idEmpleado) {
        Document doc = coleccion.find(Filters.eq("idEmpleado", idEmpleado)).first();
        if (doc != null) {
            return convertirADocumentoAEmpleado(doc);
        } else {
            return null;
        }
    }
}
