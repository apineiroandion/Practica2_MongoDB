package app.service;

import app.model.Country;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * Clase con los metodos CRUD del pais
 */
public class CountryService {
    /**
     * Metodo para insertar un pais
     * @param country
     * @param collection
     */
    public static void insertCountry(Country country, MongoCollection<Document> collection) {
        Document insert = new Document("name", country.getName())
                .append("organization", country.getOrganization())
                .append("parties", country.getParties())
                .append("president_id", country.getPresident_id());
        collection.insertOne(insert);
    }

    /**
     * Metodo para eliminar un pais
     * @param name
     * @param collection
     */
    public static void deleteCountry(String name, MongoCollection<Document> collection) {
        Document delete = new Document("name", name);
        collection.deleteOne(delete);
    }

}
