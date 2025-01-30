package app.service;

import app.model.President;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.IOException;

/**
 * Clase con los metodos CRUD del presidente
 */
public class PresidentService {
    /**
     * Metodo para insertar un presidente
     * @param president
     */
    public static void insertPresident(President president, MongoCollection<Document> collection) {
        Document insert = new Document("_id", president.getId())
                .append("name", president.getName())
                .append("age", president.getAge())
                .append("party", president.getParty());
        collection.insertOne(insert);
    }

    public static ObjectId getPresidentIdByName(String name, MongoCollection<Document> collection) {
        Document query = new Document("name", name);
        Document result = collection.find(query).first();
        if (result != null) {
            return result.getObjectId("_id");
        }
        return null;
    }

    public static President getPresidentById(ObjectId id, MongoCollection<Document> collection) {
        Document query = new Document("_id", id);
        Document result = collection.find(query).first();
        if (result != null) {
            return new President(result.getString("name"), result.getInteger("age"), result.getString("party"));
        }
        return null;
    }

    /**
     * Metodo para eliminar un presidente
     * @param name
     */
    public static void deletePresident(String name, MongoCollection<Document> collection) {
        Document delete = new Document("name", name);
        collection.deleteOne(delete);
    }

    /**
     * Metodo para guardar un presidente en un archivo JSON
     * @param president
     */
    public static void savePresidentToJson(President president, String directoryPath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, president.getName() + ".json");
            objectMapper.writeValue(file, president);
            System.out.println("President saved to JSON file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());;
        }
    }

    /**
     * Metodo que muestra todos los presidentes por consola
     */
    public static void showAllPresidents(MongoCollection<Document> collection) {
        FindIterable<Document> presidents = collection.find();
        for (Document president : presidents) {
            System.out.println(president.toJson());
        }
    }


    public static void updatePresident(President president,String newName, int newAge, String newParty, MongoCollection<Document> collection) {
        Document query = new Document("_id", president.getId());
        Document update = new Document("$set", new Document("name", newName)
                .append("age", newAge)
                .append("party", newParty));
        collection.updateOne(query, update);
        System.out.println("President updated: " + president.getName());
    }





}
