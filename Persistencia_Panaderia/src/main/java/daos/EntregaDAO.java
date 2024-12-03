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
import entidades.Empleado;
import entidades.Entrega;
import entidades.Producto;
import entidades.Tienda;
import interfaces.IEntregaDAO;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    public EntregaDAO() {
        // Usamos la conexión singleton para obtener la base de datos
        MongoDatabase baseDatos = ConexionMongoDB.getDatabase();  // Obtenemos la base de datos
        this.coleccion = baseDatos.getCollection("entrega");  // Obtener la colección "empleado"
    }

    public EntregaDAO(MongoDatabase baseDatos) {
        // Conectamos a la colección "entregas" de la base de datos
        this.coleccion = baseDatos.getCollection("entrega");
    }

    // Método auxiliar para convertir un Document a Entrega
    private Entrega convertirADocumentoAEntrega(Document doc) {
        Entrega entrega = new Entrega();
        entrega.setId(doc.getObjectId("_id"));
        entrega.setFechaEntrega(doc.getDate("fechaEntrega"));
        entrega.setMontoTotal(doc.getDouble("montoTotal"));

        // Manejar productos
        List<Document> productosDocs = doc.getList("productos", Document.class);
        List<Producto> productos = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();
        List<Double> precios = new ArrayList<>();

        if (productosDocs != null) {
            for (Document productoDoc : productosDocs) {
                Producto producto = new Producto();
                producto.setNombre(productoDoc.getString("nombre"));
                producto.setPrecio(productoDoc.containsKey("precio") ? productoDoc.getDouble("precio") : 0.0);
                productos.add(producto);
                cantidades.add(productoDoc.getInteger("cantidad", 0));  // Agregar la cantidad correspondiente
                precios.add(productoDoc.containsKey("precio") ? productoDoc.getDouble("precio") : 0.0);  // Agregar el precio
            }
        }

        entrega.setProductos(productos);
        entrega.setCantidades(cantidades);
        entrega.setPrecios(precios);

        // Recuperar tienda
        String tiendaId = doc.getString("tienda");
        if (tiendaId != null) {
            TiendaDAO tiendaDAO = new TiendaDAO();
            Tienda tienda = tiendaDAO.obtenerTiendaPorID(tiendaId);  // Asegúrate de que esto esté buscando bien por el ID.
            if (tienda != null) {
                entrega.setTienda(tienda);  // Si encuentra la tienda, la asigna
            } else {
                System.err.println("Tienda no encontrada para el ID: " + tiendaId);  // Si no encuentra la tienda
            }
        }

        // Recuperar repartidor
        String repartidorId = doc.getString("repartidor");
        if (repartidorId != null) {
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            Empleado repartidor = empleadoDAO.obtenerEmpleadoPorID(repartidorId);
            if (repartidor != null) {
                entrega.setRepartidor(repartidor);
            } else {
                System.err.println("Repartidor no encontrado para el ID: " + repartidorId);
            }
        }

        return entrega;
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

    @Override
    public List<Entrega> obtenerEntregasPorRangoFecha(Date fechaInicio, Date fechaFin) {
        List<Entrega> entregas = new ArrayList<>();
        try {
            // Crear un filtro que busque entregas dentro del rango de fechas
            Document filtro = new Document("fechaEntrega",
                    new Document("$gte", fechaInicio) // Fecha mayor o igual a fechaInicio
                            .append("$lte", fechaFin));        // Fecha menor o igual a fechaFin

            // Buscar en la colección usando el filtro
            try (MongoCursor<Document> cursor = coleccion.find(filtro).iterator()) {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    entregas.add(convertirADocumentoAEntrega(doc)); // Convertimos el documento a objeto Entrega
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones (podrías lanzar una RuntimeException o manejarla según tu caso)
        }
        return entregas;
    }

    @Override
    public void actualizarEstadoPorId(String idEntrega, String nuevoEstado) {
        try {
            // Convertir el ID a ObjectId
            ObjectId objectId = new ObjectId(idEntrega);

            // Crear el filtro para buscar por ID
            Document filtro = new Document("_id", objectId);

            // Crear la actualización del estado
            Document actualizacion = new Document("$set", new Document("estado", nuevoEstado));

            // Realizar la actualización en la base de datos
            coleccion.updateOne(filtro, actualizacion);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el estado en la base de datos: " + e.getMessage(), e);
        }
    }

    @Override
    public String guardarEntrega(Entrega entrega) {
        if (entrega.getRepartidor() == null) {
            throw new RuntimeException("Debe asignarse un repartidor a la entrega.");
        }

        List<Document> productosDocs = new ArrayList<>();
        for (int i = 0; i < entrega.getProductos().size(); i++) {
            Producto producto = entrega.getProductos().get(i);
            Integer cantidad = entrega.getCantidades().get(i);
            Double precio = entrega.getPrecios().get(i);

            Document productoDoc = new Document("nombre", producto.getNombre())
                    .append("cantidad", cantidad)
                    .append("precio", precio);
            productosDocs.add(productoDoc);
        }

        // Obtener la fecha actual
        Date fechaActual = entrega.getFechaEntrega();

        // Convertir la fecha a ZonedDateTime
        ZonedDateTime fechaZoned = fechaActual.toInstant().atZone(ZoneId.systemDefault());

        // Sumar 1 hora
        ZonedDateTime nuevaFecha = fechaZoned.plusHours(1);

        // Convertir nuevamente a Date
        Date nuevaFechaEntrega = Date.from(nuevaFecha.toInstant());

        // Crear el documento para insertar en MongoDB
        Document documento = new Document("tienda", entrega.getTienda().getIdAsString())
                .append("repartidor", entrega.getRepartidor().getIdAsString())
                .append("montoTotal", entrega.getMontoTotal())
                .append("productos", productosDocs)
                .append("fechaEntrega", nuevaFechaEntrega) // Usamos la nueva fecha con 1 hora añadida
                .append("estado", "En proceso de entrega");

        // Insertar el documento en la colección
        coleccion.insertOne(documento);

        // Retornar el ID generado
        return documento.getObjectId("_id").toString();
    }

}
