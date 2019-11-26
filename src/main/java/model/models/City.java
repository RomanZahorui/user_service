package model.models;

import java.util.Objects;

/**
 * The class extends {@link BaseModel}
 */
public class City extends BaseModel {
    private String name;
    private int countryId;

    /**
     * @param id of the {@link BaseModel}.
     * @param name of the city.
     * @param countryId an id of the related country.
     */
    public City(int id, String name, int countryId) {
        super(id);
        this.name = name;
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return super.equals(o) &&
            countryId == city.countryId &&
            name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, countryId);
    }

    @Override
    public String toString() {
        return "City{" +
            "id=" + super.getId() +
            ", name='" + name + '\'' +
            ", countryId=" + countryId +
            '}';
    }
}
