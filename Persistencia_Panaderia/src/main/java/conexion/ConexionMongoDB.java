/**
 * 
 */
package conexion;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;

public class ConexionMongoDB {
    private static final String CADENA_CONEXION = "mongodb://localhost:27017";
    private static final String NOMBRE_BASE_DE_DATOS = "panaderia";
    private static MongoClient mongoClient = null;
    private static MongoDatabase db = null;

    // Método para obtener la conexión a la base de datos
    public static MongoDatabase getDatabase() {
        if (db == null) {
            conectar();
        }
        return db;
    }

    // Método privado para establecer la conexión
    private static void conectar() {
        try {
            // Usamos MongoClients para crear la conexión a la base de datos
            mongoClient = MongoClients.create(CADENA_CONEXION);
            db = mongoClient.getDatabase(NOMBRE_BASE_DE_DATOS);
            System.out.println("Conexión a MongoDB exitosa!");
        } catch (Exception e) {
            System.err.println("Error al conectar con MongoDB: " + e.getMessage());
        }
    }

    // Método para cerrar la conexión cuando ya no se necesita
    public static void cerrar() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión a MongoDB cerrada.");
        }
    }
}