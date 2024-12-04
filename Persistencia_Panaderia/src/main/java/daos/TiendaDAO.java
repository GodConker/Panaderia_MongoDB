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
import entidades.Tienda;
import interfaces.ITiendaDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author danie
 */
public class TiendaDAO implements ITiendaDAO {

    private final MongoCollection<Document> coleccion;

    public TiendaDAO() {
        // Conectamos a la colección de tiendas dentro de la base de datos
        MongoDatabase baseDatos = ConexionMongoDB.getDatabase();
        this.coleccion = baseDatos.getCollection("tienda");
    }

    @Override
    public List<Tienda> obtenerTodasLasTiendas() {
        List<Tienda> tiendas = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Tienda tienda = convertirDocumentoATienda(doc);
                tiendas.add(tienda);
            }
        }
        return tiendas;
    }

    @Override
    public Tienda obtenerTiendaPorID(String id) {
        try {
            Document doc = coleccion.find(Filters.eq("_id", id)).first(); // Buscar directamente por el ID (String)

            if (doc != null) {
                return convertirDocumentoATienda(doc);
            } else {
                System.err.println("Tienda no encontrada para el ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener la Tienda por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean agregarTienda(Tienda tienda) {
        try {
            // Convertir Tienda a Document para insertarla
            Document doc = convertirTiendaADocumento(tienda);
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
            // Filtramos por _id y actualizamos los datos de la tienda
            ObjectId objectId = new ObjectId(tienda.getId());
            Document filtro = new Document("_id", objectId);
            Document actualizacion = new Document("$set", convertirTiendaADocumento(tienda));
            coleccion.updateOne(filtro, actualizacion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarTienda(String id) {
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
    public List<Tienda> buscarTiendasPorNombre(String nombre) {
        List<Tienda> tiendas = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find(Filters.regex("nombre", nombre, "i")).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Tienda tienda = convertirDocumentoATienda(doc);
                tiendas.add(tienda);
            }
        }
        return tiendas;
    }

    @Override
    public Tienda buscarTiendaPorNombre(String nombre) {
        try {
            Document doc = coleccion.find(Filters.regex("nombre", "^" + nombre + "$", "i")).first(); // Búsqueda por nombre (insensible a mayúsculas/minúsculas)

            if (doc != null) {
                return convertirDocumentoATienda(doc);
            }
        } catch (Exception e) {
            System.err.println("Error al buscar la tienda por nombre: " + e.getMessage());
        }
        return null;
    }

    private Tienda convertirDocumentoATienda(Document doc) {
        return new Tienda(
                doc.getString("_id"), // Obtener el ID como String
                doc.getString("nombre"),
                doc.getString("ubicacionCoordenadas"),
                doc.getString("telefono"),
                doc.getString("direccion")
        );
    }

    private Document convertirTiendaADocumento(Tienda tienda) {
        return new Document("_id", tienda.getId()) // Usar el ID directamente como String
                .append("nombre", tienda.getNombre())
                .append("ubicacionCoordenadas", tienda.getUbicacionCoordenadas())
                .append("telefono", tienda.getTelefono())
                .append("direccion", tienda.getDireccion());
    }
}
