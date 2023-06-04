package org.example.dao;

import org.example.models.Sighting;

import java.util.List;

public interface SightingDAO {
    void create(Sighting sighting);
    void update(Sighting sighting);
    List<Sighting> getAll();
    Sighting getById(Integer id);
    void delete(String id);
}
