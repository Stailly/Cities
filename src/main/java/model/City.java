package model;

import java.util.Objects;

public class City {
    private int id;
    private String name;
    private String region;
    private String district;
    private long population;
    private int foundation;

    public City(String name, String region, String district, long population, int foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getFoundation() {
        return foundation;
    }

    public void setFoundation(int foundation) {
        this.foundation = foundation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && population == city.population && foundation == city.foundation && Objects.equals(name, city.name) && Objects.equals(region, city.region) && Objects.equals(district, city.district);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, region, district, population, foundation);
    }

    @Override
    public String toString() {
        return "model.City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation=" + foundation +
                '}';
    }
}