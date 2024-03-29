package model;

import java.util.Objects;

/**
 * The class extends {@link BaseModel}
 */
public class Country extends BaseModel {
    private String name;

    /**
     * @param id of the {@link BaseModel}.
     * @param name of the country.
     */
    public Country(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Country country = (Country) o;
        return super.equals(o) &&
            name.equals(country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + super.getId() +
            ", name='" + name + '\'' +
            '}';
    }
}
