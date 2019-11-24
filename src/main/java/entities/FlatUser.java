package entities;

import java.time.LocalDate;
import java.util.Objects;
import models.BaseModel;

/**
 * The database entity class. Extends {@link BaseModel}.
 */
public class FlatUser extends BaseModel {
    private String name;
    private LocalDate birthday;
    private String cityName;
    private String countryName;

    /**
     * @param id of the {@link BaseModel}.
     * @param name of the user.
     * @param birthday the date of user's birth.
     * @param cityName the name of the user's city.
     * @param countryName the name of the user's country.
     */
    public FlatUser(int id, String name, LocalDate birthday, String cityName, String countryName) {
        super(id);
        this.name = name;
        this.birthday = birthday;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FlatUser flatUser = (FlatUser) o;
        return super.getId() == flatUser.getId() &&
            name.equals(flatUser.name) &&
            birthday.equals(flatUser.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), name, birthday);
    }

    @Override
    public String toString() {
        return "FlatUser{" +
            "id=" + super.getId() +
            ", name='" + name + '\'' +
            ", birthday=" + birthday +
            ", cityName='" + cityName + '\'' +
            ", countryName='" + countryName + '\'' +
            '}';
    }
}
