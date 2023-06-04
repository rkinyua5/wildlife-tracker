package org.example.dao;

import org.example.models.EndangeredAnimal;

import java.util.List;

public interface EndangeredAnimalDAO{
    void create(EndangeredAnimal animal);
    void update(EndangeredAnimal animal);
    List<EndangeredAnimal> getAll();
    EndangeredAnimal getById(Integer id);
    void delete(String id);
}
