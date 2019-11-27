package model.entities.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.BaseModel;
import model.City;
import model.Country;
import model.User;
import model.entities.FlatUser;
import utils.execptions.BadIdException;

/**
 * An implementation of {@link UserDataMapper} interface with {@link FlatUser} type.
 */
public class FlatUserMapper implements UserDataMapper<FlatUser> {

    private final Finder<City> cityFinder;
    private final Finder<Country> countryFinder;

    public FlatUserMapper() {
        this.cityFinder = new Finder<>();
        this.countryFinder = new Finder<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FlatUser> mapToList(Map<Integer, User> users,
                                    Map<Integer, City> cities,
                                    Map<Integer, Country> countries) throws BadIdException {
        return users.values().stream()
            .map(u -> this.map(u, cities, countries))
            .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FlatUser map(User user,
                        Map<Integer, City> cities,
                        Map<Integer, Country> countries) throws BadIdException {
        City city = cityFinder.findById(cities, user.getCityId());
        Country country = countryFinder.findById(countries, city.getCountryId());
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
        private F findById(Map<Integer, F> data, int id) throws BadIdException {
            return data.get(id);
        }
    }

}
