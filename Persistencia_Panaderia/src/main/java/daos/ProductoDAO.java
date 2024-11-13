/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Producto;
import interfaces.IProductoDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author danie
 */
public class ProductoDAO implements IProductoDAO {

    private final MongoCollection<Document> coleccion;

    public ProductoDAO(MongoDatabase baseDatos) {
        // Conectamos a la colección de productos dentro de la base de datos
        this.coleccion = baseDatos.getCollection("productos");
    }

    // Método auxiliar para convertir un Document a Producto
    private Producto convertirADocumentoAProducto(Document doc) {
        return new Producto(
            doc.getObjectId("idProducto"),  // Usamos getInteger en lugar de getLong
            doc.getString("nombre"),
            doc.getString("marca"),
            doc.getDouble("precio"),
            doc.getString("descripcion")
        );
    }

    // Método auxiliar para convertir Producto a Document
    private Document convertirAProductoADocumento(Producto producto) {
        return new Document("idProducto", producto.getId())
            .append("nombre", producto.getNombre())
            .append("marca", producto.getMarca())
            .append("precio", producto.getPrecio())
            .append("descripcion", producto.getDescripcion());
    }

    @Override
    public List<Producto> obtenerTodosProductos() {
        List<Producto> productos = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                productos.add(convertirADocumentoAProducto(doc));
            }
        }
        return productos;
    }

    @Override
    public Producto obtenerProductoPorID(int id) {
        Document doc = coleccion.find(Filters.eq("idProducto", id)).first();
        return (doc != null) ? convertirADocumentoAProducto(doc) : null;
    }

    @Override
    public boolean agregarProducto(Producto producto) {
        try {
            Document doc = convertirAProductoADocumento(producto);
            coleccion.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        try {
            Document filtro = new Document("idProducto", producto.getId());
            Document actualizacion = new Document("$set", convertirAProductoADocumento(producto));
            coleccion.updateOne(filtro, actualizacion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void eliminarProducto(int idProducto) {
        try {
            Document filtro = new Document("idProducto", idProducto);
            coleccion.deleteOne(filtro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> buscarProductosPorNombre(String nombre) {
        List<Producto> productos = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find(Filters.regex("nombre", nombre, "i")).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                productos.add(convertirADocumentoAProducto(doc));
            }
        }
        return productos;
    }
}