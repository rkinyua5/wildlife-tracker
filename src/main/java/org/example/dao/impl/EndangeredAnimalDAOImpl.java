package org.example.dao.impl;

import org.example.dao.EndangeredAnimalDAO;
import org.example.models.EndangeredAnimal;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class EndangeredAnimalDAOImpl implements EndangeredAnimalDAO {
    private final Sql2o sql2o;

    public EndangeredAnimalDAOImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void create(EndangeredAnimal endangeredAnimal) {
        String query = "INSERT INTO endangered_animals (animal_id, health, age) VALUES (:animalId, :health, :age)";
        try (Connection con = sql2o.open()) {
            con.createQuery(query, false)
                    .bind(endangeredAnimal)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(EndangeredAnimal endangeredAnimal) {
        String sql = "UPDATE endangered_animals SET (animal_id, health, age) = (:animalId, :health, :age) WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql, false)
                    .bind(endangeredAnimal)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<EndangeredAnimal> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM endangered_animals")
                    .addColumnMapping("animal_id", "animalId")
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }

    @Override
    public EndangeredAnimal getById(Integer id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM endangered_animals WHERE id = :id")
                    .addParameter("id", id)
                    .addColumnMapping("animal_id", "animalId")
                    .executeAndFetchFirst(EndangeredAnimal.class);
        }    }

    @Override
    public void delete(String id) {
        String sql = "DELETE from endangered_animals WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
