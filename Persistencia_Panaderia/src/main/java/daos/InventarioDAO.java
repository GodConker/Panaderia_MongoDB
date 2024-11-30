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
    private final MongoCollection<Document> coleccionProducto;
    private final MongoCollection<Document> coleccionInventario;

    public InventarioDAO(MongoDatabase baseDatos) {
        this.coleccion = baseDatos.getCollection("inventario");
        this.coleccionProducto = baseDatos.getCollection("producto");
        this.coleccionInventario = baseDatos.getCollection("inventario");
    }

    public InventarioDAO() {
        // Establecer la conexión directamente dentro del constructor con la nueva forma de crear MongoClient
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");  // Usando MongoClients.create()
        MongoDatabase baseDatos = mongoClient.getDatabase("panaderia"); // Nombre de la base de datos

        // Inicialización de las colecciones
        this.coleccion = baseDatos.getCollection("inventario"); // Obtener la colección "inventario"
        this.coleccionProducto = baseDatos.getCollection("producto"); // Obtener la colección "producto"
        this.coleccionInventario = baseDatos.getCollection("inventario"); // Obtener la colección "inventario" (ya está definida)
    }

    // Método auxiliar para convertir un Document a Inventario
    private Inventario convertirADocumentoAInventario(Document doc) {
        Producto producto = new Producto();
        producto.setId(doc.getObjectId("idProducto")); // Suponiendo que "idProducto" es un campo en MongoDB

        return new Inventario(
                doc.getObjectId("idInventario"),
                producto, // Asumimos que Producto se maneja por su ID en el Inventario
                doc.getInteger("cantidad")
        );
    }

    // Método auxiliar para convertir Inventario a Document
    private Document convertirAInventarioADocumento(Inventario inventario) {
        return new Document("idInventario", inventario.getId())
                .append("idProducto", inventario.getProducto().getId()) // Se usa el ID del producto
                .append("cantidad", inventario.getCantidadDisponible());
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
    public Inventario obtenerInventarioPorID(int id) {
        Document doc = coleccion.find(Filters.eq("idInventario", id)).first();
        return (doc != null) ? convertirADocumentoAInventario(doc) : null;
    }

    @Override
    public boolean actualizarInventario(Inventario inventario) {
        try {
            Document filtro = new Document("idInventario", inventario.getId());
            Document actualizacion = new Document("$set", new Document("cantidad", inventario.getCantidadDisponible()));
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
    public int obtenerCantidadPorProducto(String nombreProducto) {
        try {
            // Busca el inventario relacionado al producto por su nombre en el subdocumento "producto"
            Document inventario = coleccionInventario.find(Filters.eq("producto.nombre", nombreProducto)).first();
            if (inventario == null) {
                return 0; // Sin registro de inventario
            }

            // Retorna la cantidad disponible
            return inventario.getInteger("cantidadDisponible", 0);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar el inventario para el producto: " + nombreProducto, e);
        }
    }

}
