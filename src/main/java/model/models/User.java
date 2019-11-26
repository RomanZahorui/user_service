package model.models;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The class extends {@link BaseModel}
 */
public class User extends BaseModel {
    private String name;
    private LocalDate birthday;
    private int cityId;

    /**
     * Constructor.
     *
     * @param id       of the user ({@link BaseModel}).
     * @param name     of the user.
     * @param birthday the date of user's birth.
     * @param cityId   the id the related city.
     */
    public User(int id, String name, LocalDate birthday, int cityId) {
        super(id);
        this.name = name;
        this.birthday = birthday;
        this.cityId = cityId;
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return super.equals(o) &&
            cityId == user.cityId &&
            name.equals(user.name) &&
            birthday.equals(user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, birthday, cityId);
    }

    @Override
    public String toString() {
        return "User{" +
            super.toString() +
            "name='" + name + '\'' +
            ", birthday=" + birthday +
            ", cityId=" + cityId +
            '}';
    }
}
