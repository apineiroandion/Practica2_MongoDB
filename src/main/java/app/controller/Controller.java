package app.controller;

import app.controller.dbConection.DB_Con;
import app.model.Country;
import app.model.President;
import app.service.CountryService;
import app.service.PresidentService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    public static final int PRESIDENT_COLLECTION = 0;
    public static final int COUNTRY_COLLECTION = 1;

    Scanner scanner = new Scanner(System.in);
    MongoClient mongoClient;
    MongoDatabase database;
    ArrayList<MongoCollection<Document>> collections = new ArrayList<>();

    public Controller() {
        DB_Con db = new DB_Con();
        mongoClient = db.createConnection();
        database = mongoClient.getDatabase("practica2_db");
        collections.add(database.getCollection("presidents"));
        collections.add(database.getCollection("countries"));
    }

    public void iniciarAp() {
        pintarMenu();
    }

    public void pintarMenu() {
        System.out.println("1. Insertar presidente");
        System.out.println("2. Insertar país");
        System.out.println("3. Borrar presidente");
        System.out.println("4. Borrar país");
        System.out.println("5. Salir");

        int opcion = scanner.nextInt();
        switch (opcion){
            case 1:
                insertarPresidente();
                pintarMenu();
                break;
            case 2:
                insertarPais();
                pintarMenu();
                break;
            case 3:
                borrarPresidente();
                pintarMenu();
                break;
            case 4:
                borrarPais();
                pintarMenu();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida");
                pintarMenu();
                break;
        }
    }

    public void insertarPresidente(){
        System.out.println("Introduce el nombre del presidente");
        String name = scanner.nextLine();
        System.out.println("Introduce la edad del presidente");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el partido del presidente");
        String party = scanner.nextLine();
        President president = new President(name, age, party);
        PresidentService.insertPresident(president, collections.get(PRESIDENT_COLLECTION));
        System.out.println("Presidente insertado");
    }

    public void insertarPais(){
        System.out.println("Introduce el nombre del país");
        String name = scanner.nextLine();
        System.out.println("Introduce la organización del país");
        String organization = scanner.nextLine();
        System.out.println("Introduce el partido del país");
        String party = scanner.nextLine();
        System.out.println("Introduce el presidente del país");
        String presidentName = scanner.nextLine();
        President president = PresidentService.getPresidentById(PresidentService.getPresidentIdByName(presidentName, collections.get(PRESIDENT_COLLECTION)), collections.get(PRESIDENT_COLLECTION));
        Country country = new Country(name, organization, party, president);
        CountryService.insertCountry(country, collections.get(COUNTRY_COLLECTION));

        System.out.println("País insertado");
    }

    public void borrarPresidente(){
        System.out.println("Introduce el nombre del presidente a borrar");
        String name = scanner.nextLine();
        PresidentService.deletePresident(name, collections.get(PRESIDENT_COLLECTION));
        System.out.println("Presidente borrado");
    }

    public void borrarPais(){
        System.out.println("Introduce el nombre del país a borrar");
        String name = scanner.nextLine();
        CountryService.deleteCountry(name, collections.get(COUNTRY_COLLECTION));
        System.out.println("País borrado");
    }

}
