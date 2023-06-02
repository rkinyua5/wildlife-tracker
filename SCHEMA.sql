CREATE DATABASE wildlife_tracker_test;

CREATE TYPE valid_animal_types AS ENUM ('Animal', 'EndangeredAnimal');
CREATE TYPE valid_health_options AS ENUM ('healthy','ill','okay');
CREATE TYPE valid_age_options AS ENUM ('newborn','young','adult');

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
    health valid_health_options,
    age valid_age_options,
     CONSTRAINT fk_animal
          FOREIGN KEY(animal_id)
    	  REFERENCES animals(id)
);

CREATE TABLE sightings(
    id SERIAL PRIMARY KEY,
    animal_id INTEGER,
    location VARCHAR(255) NOT NULL,
    rangerName VARCHAR(255) NOT NULL,
    type valid_animal_types,
    sighting_time TIMESTAMP
);