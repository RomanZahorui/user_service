package model;

import java.util.Objects;

/**
 * The class provides initial behavior of the application's model.models.
 */
public class BaseModel {
    private int id;

    public BaseModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseModel)) {
            return false;
        }
        BaseModel baseModel = (BaseModel) o;
        return id == baseModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseModel{" +
            "id=" + id +
            '}';
    }
}