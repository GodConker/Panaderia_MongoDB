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
import com.mongodb.client.model.Updates;
import entidades.Tienda;
import interfaces.ITiendaDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author danie
 */
public class TiendaDAO implements ITiendaDAO {

    private final MongoCollection<Document> coleccion;

    // Constructor vacío que inicializa la conexión y la colección
    public TiendaDAO() {
        // Configura la conexión con MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase baseDatos = mongoClient.getDatabase("panaderia"); // Nombre de la base de datos
        this.coleccion = baseDatos.getCollection("tienda"); // Inicializa la colección
    }

    public TiendaDAO(MongoDatabase baseDatos) {
        // Conectamos a la colección de tiendas dentro de la base de datos
        this.coleccion = baseDatos.getCollection("tienda");
    }

    @Override
    public List<Tienda> obtenerTodasTiendas() {
        List<Tienda> tiendas = new ArrayList<>();
        // Consultamos todos los documentos en la colección
        try (MongoCursor<Document> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Tienda tienda = new Tienda(
                        doc.getObjectId("idTienda"), // idTienda
                        doc.getString("nombre"),
                        doc.getString("ubicacionCoordenadas"),
                        doc.getString("telefono"),
                        doc.getString("direccion")
                );
                tiendas.add(tienda);
            }
        }
        return tiendas;
    }

    @Override
    public Tienda obtenerTiendaPorID(int id) {
        // Filtramos por el idTienda
        Document doc = coleccion.find(Filters.eq("idTienda", id)).first();
        if (doc != null) {
            return new Tienda(
                    doc.getObjectId("idTienda"),
                    doc.getString("nombre"),
                    doc.getString("ubicacionCoordenadas"),
                    doc.getString("telefono"),
                    doc.getString("direccion")
            );
        }
        return null; // Tienda no encontrada
    }

    @Override
    public boolean agregarTienda(Tienda tienda) {
        try {
            // Convertir Tienda a Document para insertarla
            Document doc = new Document("idTienda", tienda.getId())
                    .append("nombre", tienda.getNombre())
                    .append("ubicacionCoordenadas", tienda.getUbicacionCoordenadas())
                    .append("telefono", tienda.getTelefono())
                    .append("direccion", tienda.getDireccion());
            coleccion.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarTienda(Tienda tienda) {
        try {
            // Filtramos por idTienda y actualizamos los datos de la tienda
            Document filtro = new Document("idTienda", tienda.getId());
            Document actualizacion = new Document("$set", new Document("nombre", tienda.getNombre())
                    .append("ubicacionCoordenadas", tienda.getUbicacionCoordenadas())
                    .append("telefono", tienda.getTelefono())
                    .append("direccion", tienda.getDireccion()));
            coleccion.updateOne(filtro, actualizacion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarTienda(int id) {
        try {
            // Filtramos por idTienda y la eliminamos
            Document filtro = new Document("idTienda", id);
            coleccion.deleteOne(filtro);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Tienda> buscarTiendasPorNombre(String nombre) {
        List<Tienda> tiendas = new ArrayList<>();
        // Filtramos tiendas cuyo nombre contenga la palabra clave
        try (MongoCursor<Document> cursor = coleccion.find(Filters.regex("nombre", nombre, "i")).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Tienda tienda = new Tienda(
                        doc.getObjectId("idTienda"),
                        doc.getString("nombre"),
                        doc.getString("ubicacionCoordenadas"),
                        doc.getString("telefono"),
                        doc.getString("direccion")
                );
                tiendas.add(tienda);
            }
        }
        return tiendas;
    }

    public int obtenerCantidadDisponibleProducto(String nombreProducto) {
        // Consulta en la colección de inventario basada en el nombre del producto
        Document producto = coleccion.find(Filters.eq("nombre", nombreProducto)).first();

        if (producto == null) {
            throw new RuntimeException("El producto no existe en el inventario: " + nombreProducto);
        }

        // Retorna la cantidad disponible del producto
        return producto.getInteger("cantidadDisponible");
    }
    
    public void actualizarCantidadInventario(String nombreProducto, int cantidadCambio) {
    coleccion.updateOne(Filters.eq("nombre", nombreProducto),
                        Updates.inc("cantidadDisponible", cantidadCambio));
}
}
