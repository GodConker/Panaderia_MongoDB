/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Lote;
import entidades.Producto;
import interfaces.ILoteDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author danie
 */
public class LoteDAO implements ILoteDAO {

    private final MongoCollection<Document> coleccion;

    public LoteDAO(MongoDatabase baseDatos) {
        // Conectamos a la colección de lotes dentro de la base de datos
        this.coleccion = baseDatos.getCollection("lote");
    }

    @Override
    public List<Lote> obtenerTodosLotes() {
        List<Lote> lotes = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                // Obtenemos el idProducto desde el documento y lo asignamos al objeto Producto
                Producto producto = new Producto(); // Asumiendo que existe un constructor sin parámetros
                producto.setId(doc.getObjectId("id_producto")); // Esto asume que id_producto es un campo en Mongo

                Lote lote = new Lote(
                        doc.getObjectId("idLote"),
                        doc.getInteger("cantidadProducida"),
                        doc.getDate("fechaProduccion"),
                        doc.getDate("fechaCaducidad"), 
                        producto // Asignamos el objeto Producto completo
                );
                lotes.add(lote);
            }
        }
        return lotes;
    }

    @Override
    public Lote obtenerLotePorID(int id) {
        Document doc = coleccion.find(Filters.eq("idLote", id)).first();
        if (doc != null) {
            Producto producto = new Producto();
            producto.setId(doc.getObjectId("id_producto"));

            return new Lote(
                    doc.getObjectId("idLote"),
                    doc.getInteger("cantidadProducida"),
                    doc.getDate("fechaProduccion"),
                    doc.getDate("fechaCaducidad"),
                    producto // Asignamos el objeto Producto
            );
        }
        return null; // No se encontró el lote
    }

    @Override
    public boolean agregarLote(Lote lote) {
        try {
            Document doc = new Document("idLote", lote.getId())
                    .append("cantidadProducida", lote.getCantidadProducida())
                    .append("fechaProduccion", lote.getFechaProduccion())
                    .append("fechaCaducidad", lote.getFechaCaducidad())
                    // Usamos el idProducto del objeto Producto
                    .append("id_producto", lote.getProducto() != null ? lote.getProducto().getId(): null);
            coleccion.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarLote(Lote lote) {
        try {
            // Creamos el filtro para encontrar el lote por su id
            Document filtro = new Document("idLote", lote.getId());

            // Obtenemos el idProducto del objeto Producto asociado
            ObjectId idProducto = lote.getProducto() != null ? lote.getProducto().getId(): null;

            // Creamos el documento de actualización con los nuevos valores
            Document actualizacion = new Document("$set", new Document("cantidadProducida", lote.getCantidadProducida())
                    .append("fechaProduccion", lote.getFechaProduccion())
                    .append("fechaCaducidad", lote.getFechaCaducidad())
                    .append("id_producto", idProducto));  // Usamos idProducto del objeto Producto

            // Realizamos la actualización en la base de datos
            coleccion.updateOne(filtro, actualizacion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarLote(int id) {
        try {
            Document filtro = new Document("idLote", id);
            coleccion.deleteOne(filtro);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Lote> obtenerLotesPorFecha(Date fecha) {
        List<Lote> lotes = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find(Filters.eq("fechaProduccion", fecha)).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Producto producto = new Producto();
                producto.setId(doc.getObjectId("id_producto"));  // Asignamos el idProducto

                Lote lote = new Lote(
                        doc.getObjectId("idLote"),
                        doc.getInteger("cantidadProducida"),
                        doc.getDate("fechaProduccion"),
                        doc.getDate("fechaCaducidad"),
                        producto // Asignamos el objeto Producto
                );
                lotes.add(lote);
            }
        }
        return lotes;
    }

    @Override
    public List<Lote> obtenerLotesPorProductoID(int productoId) {
        List<Lote> lotes = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find(Filters.eq("id_producto", productoId)).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Producto producto = new Producto();
                producto.setId(doc.getObjectId("id_producto"));

                Lote lote = new Lote(
                        doc.getObjectId("idLote"),
                        doc.getInteger("cantidadProducida"),
                        doc.getDate("fechaProduccion"),
                        doc.getDate("fechaCaducidad"),
                        producto // Asignamos el objeto Producto
                );
                lotes.add(lote);
            }
        }
        return lotes;
    }
}