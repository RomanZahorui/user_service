package model.entities.mapper;

import model.entities.FlatUser;
import utils.execptions.BadIdException;
import java.util.List;
import java.util.stream.Collectors;
import model.BaseModel;
import model.City;
import model.Country;
import model.User;

/**
 * An implementation of {@link UserDataMapper} interface with {@link FlatUser} type.
 */
public class FlatUserMapper implements UserDataMapper<FlatUser> {

    /**
     * {@inheritDoc}
     */
    @Override //TODO replace lists with Maps to improve Finder algorithm
    public List<FlatUser> mapToList(List<User> users, List<City> cities, List<Country> countries) throws BadIdException {
        return users.parallelStream().map(u -> this.map(u, cities, countries)).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override//TODO replace lists with Maps to improve Finder algorithm
    public FlatUser map(User user, List<City> cities, List<Country> countries) throws BadIdException {
        City city = new Finder<City>().findById(cities, user.getCityId());
        Country country = new Finder<Country>().findById(countries, city.getCountryId());
        return new FlatUser(user.getId(), user.getName(), user.getBirthday(), city.getName(), country.getName());
    }

    /**
     * The helper class designed to work with types extended {@link BaseModel}.
     *
     * @param <F> an extension of {@link BaseModel}.
     */
    private static class Finder<F extends BaseModel> {

        /**
         * Finds a single object whit a specified id.
         *
         * @param data a list of {@link BaseModel} to find an object by it's id.
         * @param id   a specified id.
         * @return the single object whit a specified id.
         * @throws BadIdException if the list is not contain the id.
         */
        private F findById(List<F> data, int id) throws BadIdException {
            return data.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(BadIdException::new);
        }
    }

}
