CREATE DATABASE wildlife_tracker_test;

DROP TABLE IF EXISTS sightings;
DROP TABLE IF EXISTS endangered_animals;
DROP TABLE IF EXISTS animals;

CREATE TABLE animals(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    scientific_name VARCHAR(255) NOT NULL
);

CREATE TABLE endangered_animals(
    id SERIAL PRIMARY KEY,
    animal_id INTEGER ,
    health VARCHAR(20) NOT NULL,
    age VARCHAR(20) NOT NULL,
     CONSTRAINT fk_animal
          FOREIGN KEY(animal_id)
    	  REFERENCES animals(id)
);

CREATE TABLE sightings(
    id SERIAL PRIMARY KEY,
    animal_id INTEGER,
    location VARCHAR(255) NOT NULL,
    ranger_name VARCHAR(255) NOT NULL,
    type VARCHAR(20) NOT NULL,
    sighting_time TIMESTAMP
);