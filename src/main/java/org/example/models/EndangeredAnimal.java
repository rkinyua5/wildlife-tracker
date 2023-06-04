package org.example.models;

public class EndangeredAnimal{

    private Integer id;

    private Integer animalId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String health;


    public EndangeredAnimal(Integer animalId, String health, String age) {
        this.health = health;
        this.age = age;
        this.animalId=animalId;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private String age;

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }
}
