package co.ke.safaricom.dao.impl;

import co.ke.safaricom.dao.SightingDAO;
import co.ke.safaricom.models.Sighting;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class SightingDAOImpl implements SightingDAO {
    private final Sql2o sql2o;

    public SightingDAOImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void create(Sighting sighting) {
        String query = "INSERT INTO sightings (animal_id, location, ranger_name, type, sighting_time) VALUES (:animalId, :location, :rangerName, :type, NOW())";
        try (Connection con = sql2o.open()) {
            con.createQuery(query, false)
                    .bind(sighting)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Sighting sighting) {
        String sql = "UPDATE sightings SET (animal_id, location, ranger_name, type, sighting_time) = (:animalId, :location, :rangerName, :type, sightingTime)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql, false)
                    .bind(sighting)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Sighting> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT sightings.id,sightings.animal_id,sightings.location,sightings.ranger_name,sightings.type,TO_CHAR(sightings.sighting_time,'Day, DD " +
                            "Month, YYYY at HH24:mi:ss') as formatted_date,endangered_animals.health,endangered_animals.age,animals.name,animals.scientific_name FROM sightings LEFT OUTER JOIN endangered_ani" +
                            "mals ON sightings.animal_id=endangered_animals.animal_id JOIN animals ON sightings.animal_id=animals.id;")
                    .addColumnMapping("animal_id", "animalId")
                    .addColumnMapping("ranger_name","rangerName")
                    .addColumnMapping("sighting_time","sightingTime")
                    .addColumnMapping("scientific_name","scientificName")
                    .addColumnMapping("formatted_date","formattedDate")
                    .executeAndFetch(Sighting.class);
        }
    }

    @Override
    public Sighting getById(Integer id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings WHERE id = :id")
                    .addParameter("id", id)
                    .addColumnMapping("scientific_name", "scientificName")
                    .executeAndFetchFirst(Sighting.class);
        }    }

    @Override
    public void delete(String id) {
        String sql = "DELETE from sightings WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
