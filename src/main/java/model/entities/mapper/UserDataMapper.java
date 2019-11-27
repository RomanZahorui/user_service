package model.entities.mapper;

import java.util.List;
import java.util.Map;
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
     * @param users     a map of id's and related users.
     * @param cities    a map of id's and related cities data.
     * @param countries a map of id's and related countries data.
     * @return a list of result instances.
     */
    List<T> mapToList(Map<Integer, User> users, Map<Integer, City> cities,
                      Map<Integer, Country> countries);

    /**
     * @param user      a single user.
     * @param cities    a map of id's and related cities data.
     * @param countries a map of id's and related countries data.
     * @return
     */
    T map(User user, Map<Integer, City> cities, Map<Integer, Country> countries);
}
