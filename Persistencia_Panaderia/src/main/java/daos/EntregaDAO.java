/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Entrega;
import entidades.Tienda;
import interfaces.IEntregaDAO;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase DAO para manejar las operaciones CRUD de la entidad 'Entrega' con
 * MongoDB
 */
public class EntregaDAO implements IEntregaDAO {

    private final MongoCollection<Document> coleccion;

    public EntregaDAO(MongoDatabase baseDatos) {
        // Conectamos a la colección "entregas" de la base de datos
        this.coleccion = baseDatos.getCollection("entregas");
    }

    // Método auxiliar para convertir un Document a Entrega
    private Entrega convertirADocumentoAEntrega(Document doc) {
        // Recuperamos la tienda desde su ID, ya que la tienda está guardada solo como un ID en MongoDB
        Tienda tienda = new Tienda();
        tienda.setId(doc.getObjectId("tienda"));  // Asumimos que el campo "tienda" contiene el ObjectId de la tienda

        return new Entrega(
                doc.getObjectId("_id"), // ID de la entrega
                doc.getDate("fechaEntrega"), // Fecha de entrega
                tienda // Tienda relacionada
        );
    }

    // Método auxiliar para convertir Entrega a Document
    private Document convertirAEntregaADocumento(Entrega entrega) {
        return new Document("_id", entrega.getId())
                .append("fechaEntrega", entrega.getFechaEntrega())
                .append("tienda", entrega.getTienda().getId());  // Guardamos solo el ID de la tienda
    }

    @Override
    public List<Entrega> obtenerTodasEntregas() {
        List<Entrega> entregas = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                entregas.add(convertirADocumentoAEntrega(doc));
            }
        }
        return entregas;
    }

    @Override
    public Entrega obtenerEntregaPorID(int id) {
        Document doc = coleccion.find(Filters.eq("_id", new ObjectId(id + ""))).first(); // Convertir a String y luego a ObjectId
        return (doc != null) ? convertirADocumentoAEntrega(doc) : null;
    }

    @Override
    public boolean agregarEntrega(Entrega entrega) {
        try {
            Document doc = convertirAEntregaADocumento(entrega);
            coleccion.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarEntrega(Entrega entrega) {
        try {
            Document filtro = new Document("_id", entrega.getId());
            Document actualizacion = new Document("$set", new Document("fechaEntrega", entrega.getFechaEntrega()));
            coleccion.updateOne(filtro, actualizacion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
public boolean eliminarEntrega(int id) {
    try {
        // Si 'id' es un int, entonces debes convertirlo a ObjectId correctamente
        Document filtro = new Document("_id", new ObjectId(id + ""));  // Convertimos el int a String y luego a ObjectId
        coleccion.deleteOne(filtro);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    @Override
    public List<Entrega> obtenerEntregasPorFecha(Date fecha) {
        List<Entrega> entregas = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find(Filters.eq("fechaEntrega", fecha)).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                entregas.add(convertirADocumentoAEntrega(doc));
            }
        }
        return entregas;
    }
}
