package model.models.producer.impl;

import java.util.List;
import model.models.City;
import model.models.producer.Factory;
import utils.Formatter;

/**
 * The class implements {@link Factory} interface. Produces an instance of the {@link City} base on data contained in
 * the {@code List<String>} parameter and {@link Formatter} to format these strings.
 */
public class CityFactory implements Factory<City> {

    /**
     * Parses the list of string into related values.
     *
     * @param modelStringData a list of strings contained a state of a {@link City}.
     * @param formatter       to format the strings.
     * @return an instance of {@link City}.
     * @throws NumberFormatException if the string does not contain a
     *                               parsable integer.
     */
    @Override
    public City produce(List<String> modelStringData, Formatter formatter) throws NumberFormatException {
        if (modelStringData.size() != 3) {
            throw new NumberFormatException();
        }

        int id = Integer.parseInt(formatter.apply(modelStringData.get(0)));
        String name = formatter.apply(modelStringData.get(1));
        int countryId = Integer.parseInt(formatter.apply(modelStringData.get(2)));
        return new City(id, name, countryId);
    }
}
