/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Empleado;
import entidades.Produccion;
import interfaces.IProduccionDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author danie
 */
public class ProduccionDAO implements IProduccionDAO {

    private final MongoCollection<Document> coleccion;

    public ProduccionDAO(MongoDatabase baseDatos) {
        // Conectamos a la colección de producciones dentro de la base de datos
        this.coleccion = baseDatos.getCollection("producciones");
    }

    @Override
    public List<Produccion> obtenerTodasProducciones() {
        List<Produccion> producciones = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                
                // Crear el objeto Empleado usando el id_empleado
                Empleado empleado = new Empleado();
                empleado.setId(doc.getObjectId("id_empleado"));  // Suponiendo que 'id_empleado' es el id del empleado

                Produccion produccion = new Produccion(
                        doc.getObjectId("idProduccion"),
                        doc.getDate("fechaProduccion"), 
                        empleado // Asignamos el objeto Empleado completo
                );
                producciones.add(produccion);
            }
        }
        return producciones;
    }

    @Override
    public Produccion obtenerProduccionPorID(int id) {
        Document doc = coleccion.find(Filters.eq("idProduccion", id)).first();
        if (doc != null) {
            // Crear el objeto Empleado usando el id_empleado
            Empleado empleado = new Empleado();
            empleado.setId(doc.getObjectId("id_empleado"));

            return new Produccion(
                    doc.getObjectId("idProduccion"),
                    doc.getDate("fechaProduccion"), 
                    empleado // Asignamos el objeto Empleado
            );
        }
        return null; // No se encontró la producción
    }

    @Override
    public boolean agregarProduccion(Produccion produccion) {
        try {
            Document doc = new Document("idProduccion", produccion.getId())
                    .append("fechaProduccion", produccion.getFechaProduccion())
                    // Usamos el id del empleado en lugar de almacenar el objeto
                    .append("id_empleado", produccion.getEmpleado() != null ? produccion.getEmpleado().getId(): null);
            coleccion.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarProduccion(Produccion produccion) {
        try {
            Document filtro = new Document("idProduccion", produccion.getId());
            // Usamos el id_empleado en lugar del objeto Empleado
            Document actualizacion = new Document("$set", new Document("fechaProduccion", produccion.getFechaProduccion())
                    .append("id_empleado", produccion.getEmpleado() != null ? produccion.getEmpleado().getId(): null));
            coleccion.updateOne(filtro, actualizacion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarProduccion(int id) {
        try {
            Document filtro = new Document("idProduccion", id);
            coleccion.deleteOne(filtro);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Produccion> obtenerProduccionPorFecha(Date fecha) {
        List<Produccion> producciones = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find(Filters.eq("fechaProduccion", fecha)).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                
                // Crear el objeto Empleado usando el id_empleado
                Empleado empleado = new Empleado();
                empleado.setId(doc.getObjectId("id_empleado"));  // Suponiendo que 'id_empleado' es el id del empleado

                Produccion produccion = new Produccion(
                        doc.getObjectId("idProduccion"),
                        doc.getDate("fechaProduccion"), 
                        empleado // Asignamos el objeto Empleado
                );
                producciones.add(produccion);
            }
        }
        return producciones;
    }

    @Override
    public List<Produccion> obtenerProduccionPorProductoID(int productoId) {
        List<Produccion> producciones = new ArrayList<>();
        // Supongamos que el idProducto está relacionado en otro campo
        // Si necesitas relacionarlo con la tabla Lote o Producto, harías una búsqueda similar
        // Esta parte se implementa de acuerdo con el esquema de la base de datos.
        try (MongoCursor<Document> cursor = coleccion.find(Filters.eq("id_producto", productoId)).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                
                // Crear el objeto Empleado usando el id_empleado
                Empleado empleado = new Empleado();
                empleado.setId(doc.getObjectId("id_empleado"));

                Produccion produccion = new Produccion(
                        doc.getObjectId("idProduccion"),
                        doc.getDate("fechaProduccion"), 
                        empleado // Asignamos el objeto Empleado
                );
                producciones.add(produccion);
            }
        }
        return producciones;
    }
}