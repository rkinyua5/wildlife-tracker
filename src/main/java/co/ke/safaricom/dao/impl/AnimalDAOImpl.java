package co.ke.safaricom.dao.impl;

import co.ke.safaricom.models.Animal;
import co.ke.safaricom.dao.AnimalDAO;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class AnimalDAOImpl implements AnimalDAO {
    private final Sql2o sql2o;

    public AnimalDAOImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Integer create(Animal animal) {
        String query = "INSERT INTO animals (name, scientific_name) VALUES (:name, :scientificName)";
        try (Connection con = sql2o.open()) {
            return Integer.parseInt(con.createQuery(query, true)
                    .bind(animal)
                    .executeUpdate()
                    .getKey()
                    .toString());
        } catch (Sql2oException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public void update(Animal animal) {
        String sql = "UPDATE animals SET (name, scientific_name) = (:name, :scientificName) WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql, false)
                    .bind(animal)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Animal> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM animals")
                    .addColumnMapping("scientific_name", "scientificName")
                    .executeAndFetch(Animal.class);
        }
    }

    @Override
    public Animal getById(Integer id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM animals WHERE id = :id")
                    .addParameter("id", id)
                    .addColumnMapping("scientific_name", "scientificName")
                    .executeAndFetchFirst(Animal.class);
        }    }

    @Override
    public void delete(String id) {
        String sql = "DELETE from animals WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
