/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionMongoDB;
import entidades.Inventario;
import entidades.Producto;
import interfaces.IInventarioDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author danie
 */
public class InventarioDAO implements IInventarioDAO {

    private final MongoCollection<Document> coleccion;

    public InventarioDAO() {
        // Conectamos a la colección de inventarios dentro de la base de datos
        MongoDatabase baseDatos = ConexionMongoDB.getDatabase();
        this.coleccion = baseDatos.getCollection("inventario");
    }

    // Método auxiliar para convertir un Document a Inventario
    private Inventario convertirADocumentoAInventario(Document doc) {
        Producto producto = new Producto();
        //producto.setId(doc.getObjectId("_id")); // Suponiendo que "idProducto" es un campo en MongoDB

        return new Inventario(
                doc.getString("_id"),
                producto, // Asumimos que Producto se maneja por su ID en el Inventario
                doc.getInteger("cantidadDisponible")
        );
    }

    // Método auxiliar para convertir Inventario a Document
    private Document convertirAInventarioADocumento(Inventario inventario) {
        return new Document("_id", inventario.getId())
                .append("idProducto", inventario.getProducto().getId()) // Se usa el ID del producto
                .append("cantidadDisponible", inventario.getCantidadDisponible());
    }

    @Override
    public List<Inventario> obtenerInventarioCompleto() {
        List<Inventario> inventarios = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                inventarios.add(convertirADocumentoAInventario(doc));
            }
        }
        return inventarios;
    }

    @Override
    public Inventario obtenerInventarioPorID(String id) {
        Document doc = coleccion.find(Filters.eq("_id", id)).first();
        return (doc != null) ? convertirADocumentoAInventario(doc) : null;
    }

    @Override
    public boolean actualizarInventario(Inventario inventario) {
        try {
            Document filtro = new Document("_id", inventario.getId());
            Document actualizacion = new Document("$set", new Document("cantidadDisponible", inventario.getCantidadDisponible()));
            coleccion.updateOne(filtro, actualizacion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean agregarProductoAlInventario(Inventario inventario) {
        try {
            Document doc = convertirAInventarioADocumento(inventario);
            coleccion.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarProductoDelInventario(int id) {
        try {
            Document filtro = new Document("idInventario", id);
            coleccion.deleteOne(filtro);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Inventario> buscarInventarioPorNombre(String nombre) {
        List<Inventario> inventarios = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find(Filters.regex("nombre", nombre, "i")).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                inventarios.add(convertirADocumentoAInventario(doc));
            }
        }
        return inventarios;
    }

    @Override
    public int obtenerCantidadDisponiblePorProducto(String idProducto) {
        try {
            // Buscar en la colección usando el idProducto
            Document doc = coleccion.find(Filters.eq("id_producto", idProducto)).first();

            // Si encontramos el documento, retornamos la cantidad disponible
            if (doc != null) {
                return doc.getInteger("cantidadDisponible", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Si no se encuentra el producto o ocurre un error, retornamos 0
        return 0;
    }
}
