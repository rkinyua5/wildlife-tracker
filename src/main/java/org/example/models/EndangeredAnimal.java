package org.example.models;

public class EndangeredAnimal extends Animal{
    private String health;

    public EndangeredAnimal(String name, String scientificName) {
        super(name, scientificName);
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
}
