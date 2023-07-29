package co.ke.safaricom.dao;

import co.ke.safaricom.models.Sighting;

import java.util.List;

public interface SightingDAO {
    void create(Sighting sighting);
    void update(Sighting sighting);
    List<Sighting> getAll();
    Sighting getById(Integer id);
    void delete(String id);
}
