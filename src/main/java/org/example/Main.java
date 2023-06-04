package org.example;

import org.example.dao.impl.AnimalDAOImpl;
import org.example.dao.impl.EndangeredAnimalDAOImpl;
import org.example.dao.impl.SightingDAOImpl;
import org.example.models.Animal;
import org.example.models.EndangeredAnimal;
import org.example.models.Sighting;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.Constants.ANIMAL;
import static org.example.Constants.ENDANGERED;
import static spark.Spark.*;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main {
    private static final String port = System.getenv("PORT");
    private static final String databaseUrl = System.getenv("JDBC_DATABASE_URL");
    private static final String databaseUsername = System.getenv("JDBC_DATABASE_USERNAME");
    private static final String databasePassword = System.getenv("JDBC_DATABASE_PASSWORD");

    public static void main(String[] args) {
        Sql2o sql2o = new Sql2o(databaseUrl, databaseUsername, databasePassword);
        AnimalDAOImpl animalDAO = new AnimalDAOImpl(sql2o);
        EndangeredAnimalDAOImpl endangeredAnimalDAO = new EndangeredAnimalDAOImpl(sql2o);
        SightingDAOImpl sightingDAO = new SightingDAOImpl(sql2o);

        int serverPort = port == null ? 8000 : Integer.parseInt(port);
        port(serverPort);
        staticFileLocation("/assets");
        System.out.println("Server started successfully on port " + serverPort);
        get("/", (req, res) -> {
            List<Sighting> sightings = sightingDAO.getAll();
            Map<String, Object> body = new HashMap<>();
            body.put("sightings", sightings);
            body.put("title", "Sightings");
            return new ModelAndView(body, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        post("/", (req, res) -> {
            String name = req.queryParams("common_name");
            String scientificName = req.queryParams("scientific_name");
            String endangered = req.queryParams("status");
            String location = req.queryParams("location");
            String rangerName = req.queryParams("ranger_name");
            Animal animal = new Animal(name, scientificName);
            Integer animalId = animalDAO.create(animal);
            Sighting sighting = new Sighting(animalId, location, ANIMAL, rangerName);
            if (endangered != null && !endangered.isBlank() && endangered.equals("on")) {
                String health = req.queryParams("health");
                String age = req.queryParams("age");
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(animalId, health, age);
                endangeredAnimalDAO.create(endangeredAnimal);
                sighting.setType(ENDANGERED);
            }
            sightingDAO.create(sighting);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}