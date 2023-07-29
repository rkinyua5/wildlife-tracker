package co.ke.safaricom.models;

public class Animal {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    private Integer id;
    private String name;

    public Animal(String name, String scientificName) {
        this.name = name;
        this.scientificName = scientificName;
    }

    private String scientificName;
}
