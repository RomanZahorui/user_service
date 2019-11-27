package model.entities.mapper;

import java.util.List;
import model.BaseModel;
import model.City;
import model.Country;
import model.User;

/**
 * Provides the interface to map {@link User}, {@link City} and
 * {@link Country} objects to a result typed object.
 *
 * @param <T> result type of the mapping.
 */
public interface UserDataMapper<T extends BaseModel> {

    /**
     * @param users     a list of the incoming users data.
     * @param cities    a list of the incoming cities data.
     * @param countries a list of the incoming countries data.
     * @return a list of result instances.
     */
    List<T> mapToList(List<User> users, List<City> cities, List<Country> countries);

    /**
     * @param user      the incoming user's data.
     * @param cities    the incoming city's data.
     * @param countries the incoming country's data.
     * @return
     */
    T map(User user, List<City> cities, List<Country> countries);
}
