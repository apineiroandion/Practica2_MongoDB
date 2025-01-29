package app.service;

import app.model.President;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

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


}
