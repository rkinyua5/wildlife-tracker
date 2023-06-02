package org.example;

import org.example.dao.impl.AnimalDAOImpl;
import org.example.models.Animal;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    private static final String port = System.getenv("PORT");
    private static final String databaseUrl = System.getenv("JDBC_DATABASE_URL");
    private static final String databaseUsername = System.getenv("JDBC_DATABASE_USERNAME");
    private static final String databasePassword = System.getenv("JDBC_DATABASE_PASSWORD");

    public static void main(String[] args) {
        Sql2o sql2o = new Sql2o(databaseUrl, databaseUsername, databasePassword);
        AnimalDAOImpl animalDAO = new AnimalDAOImpl(sql2o);
        port(port == null ? 8000 : Integer.parseInt(port));
        staticFileLocation("/static");
        get("/", (req, res) -> {
            List<Animal> animals = animalDAO.getAll();
            Map<String, Object> body = new HashMap<>();
            body.put("animals", animals);
            return body;
        });

        post("/", (req, res) -> {
            String name = req.queryParams("name");
            String scientificName = req.queryParams("scientific_name");
            Animal animal = new Animal(name, scientificName);
            animalDAO.create(animal);
            res.redirect("/");
            return null;
        });
    }
}