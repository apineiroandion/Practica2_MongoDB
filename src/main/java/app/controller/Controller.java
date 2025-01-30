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

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    public static final int PRESIDENT_COLLECTION = 0;
    public static final int COUNTRY_COLLECTION = 1;

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

        int opcion = Integer.parseInt(JOptionPane.showInputDialog("Introduce una opción: "
                + "\n1. Insertar presidente"
                + "\n2. Insertar país"
                + "\n3. Borrar presidente"
                + "\n4. Borrar país"
                + "\n5. Salir"));

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
        String name = JOptionPane.showInputDialog("Introduce el nombre del presidente");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Introduce la edad del presidente"));
        String party = JOptionPane.showInputDialog("Introduce el partido del presidente");
        President president = new President(name, age, party);
        PresidentService.insertPresident(president, collections.get(PRESIDENT_COLLECTION));
        System.out.println("Presidente insertado");
        pintarMenu();
    }

    public void insertarPais(){
        String name = JOptionPane.showInputDialog("Introduce el nombre del país");
        String organization = JOptionPane.showInputDialog("Introduce la organización del país");
        String party = JOptionPane.showInputDialog("Introduce el partido del país");
        String presidentName = JOptionPane.showInputDialog("Introduce el nombre del presidente del país");
        President president = PresidentService.getPresidentById(PresidentService.getPresidentIdByName(presidentName, collections.get(PRESIDENT_COLLECTION)), collections.get(PRESIDENT_COLLECTION));
        Country country = new Country(name, organization, party, president);
        CountryService.insertCountry(country, collections.get(COUNTRY_COLLECTION));

        System.out.println("País insertado");
        pintarMenu();
    }

    public void borrarPresidente(){
        String name = JOptionPane.showInputDialog("Introduce el nombre del presidente a borrar");
        PresidentService.deletePresident(name, collections.get(PRESIDENT_COLLECTION));
        System.out.println("Presidente borrado");
        pintarMenu();
    }

    public void borrarPais(){
        String name = JOptionPane.showInputDialog("Introduce el nombre del país a borrar");
        CountryService.deleteCountry(name, collections.get(COUNTRY_COLLECTION));
        System.out.println("País borrado");
        pintarMenu();
    }

}
