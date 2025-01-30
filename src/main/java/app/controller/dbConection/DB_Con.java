package app.controller.dbConection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * Esta clase sirve para crear la conexión con la base de datos no relacional MongoDB
 * y/o para crear la propia base de datos.
 */
public class DB_Con {
    // Atributos
    private String host;
    private String port;
    private String dbName;
    private String user;
    private String password;
    private String url;

    // Constructores
    public DB_Con() {
        this.host = "172.19.0.2";
        this.port = "27017";
        this.dbName = "mydb";
        this.user = "root";
        this.password = "example";
        this.url = "mongodb://" + user + ":" + password + "@" + host + ":" + port;
    }

    public DB_Con(String user, String password) {
        this("172.19.0.2", "27017", "mydb", user, password);
    }

    public DB_Con(String host, String port, String dbName, String user, String password) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.url = "mongodb://" + user + ":" + password + "@" + host + ":" + port;
    }

    public MongoClient createConnection() {
        System.out.println("Conectando a MongoDB en: " + url); // Depuración
        return MongoClients.create(url);
    }
}