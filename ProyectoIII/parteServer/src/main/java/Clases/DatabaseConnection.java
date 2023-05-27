package Clases;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

public class DatabaseConnection {
    private static final String MONGODB_CONNECTION_STRING = "mongodb://localhost:27017";

    public MongoClient connect() {
        ConnectionString connectionString = new ConnectionString(MONGODB_CONNECTION_STRING);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        return mongoClient;
    }

}
