package Clases;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import org.bson.Document;


public class Main {

    public static void main(String[] args) {
        /*try {
            // Crea una instancia del servidor HTTP en el puerto 8000
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

            // Configura un manejador para la ruta "/hello"
            server.createContext("/hello", new HelloHandler());

            // Inicia el servidor
            server.start();

            System.out.println("Servidor HTTP iniciado en el puerto 8000");
        } catch (IOException e) {
            System.err.println("Error al crear el servidor: " + e.getMessage());
        }
*/
        DatabaseConnection dbConnection = new DatabaseConnection();

        try {
            // Conectar a MongoDB
            MongoClient mongoClient = dbConnection.connect();
            System.out.println("Conexión exitosa a MongoDB");

            MongoDatabase database = mongoClient.getDatabase("Almacen");

            // Obtener una colección (o crearla si no existe)
            MongoCollection<Document> collection = database.getCollection("servidoralmacen");

            // Crear un documento que represente tu archivo XML
            // Crear un documento que represente tu archivo XML
            Document xmlDocument = new Document("HOLLAAAAAAAAAAAAAA", "archivo.xml")
                    .append("contenido", "<root><element>Contenido del archivo XML</element></root>");


            // Insertar el documento en la colección
            collection.insertOne(xmlDocument);

            System.out.println("Archivo XML guardado en MongoDB.");

            // Realizar otras operaciones con MongoDB aquí

            // Cerrar la conexión con MongoDB
            //mongoClient.close();
            //System.out.println("Conexión cerrada");
        } catch (MongoException e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }

    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "¡Hola, mundo!";

            // Configura la respuesta HTTP
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        }

    }
}