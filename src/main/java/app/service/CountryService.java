package app.service;

import app.model.Country;
import app.model.President;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.io.File;
import java.io.IOException;

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

    /**
     * Metodo que guarda un Pais en un archivo JSON en el directorio JSON
     * @param country
     * @param directoryPath
     */
    public static void saveCountryToJson(Country country, String directoryPath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, country.getName() + ".json");
            objectMapper.writeValue(file, country);
            System.out.println("Country saved to JSON file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metodo que muestra todos los paises por consola
     */
    public static void showAllCountries(MongoCollection<Document> collection) {
        FindIterable<Document> countries = collection.find();
        for (Document country : countries) {
            System.out.println(country.toJson());
        }
    }


    public static void updateCountry(Country country, String newName, String newOrganization, String newParties, MongoCollection<Document> collection) {
        Document query = new Document("name", newName);
        Document update = new Document("$set", new Document("organization", newOrganization)
                .append("parties", newParties)
                .append("president_id", country.getPresident_id()));
        collection.updateOne(query, update);
        System.out.println("Country updated: " + country.getName());
    }

}
