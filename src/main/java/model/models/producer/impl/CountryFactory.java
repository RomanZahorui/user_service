package model.models.producer.impl;

import java.util.List;
import model.models.Country;
import model.models.producer.Factory;
import utils.Formatter;

/**
 * The class implements {@link Factory} interface. Produces an instance of the {@link Country} base on data contained in
 * the {@code List<String>} parameter and {@link Formatter} to format these strings.
 */
public class CountryFactory implements Factory<Country> {

    /**
     * Parses the list of string into related values.
     *
     * @param modelStringData a list of strings contained a state of a {@link Country}.
     * @param formatter       to format the strings.
     * @return an instance of {@link Country}.
     * @throws NumberFormatException if the string does not contain a
     *                               parsable integer.
     */
    @Override
    public Country produce(List<String> modelStringData, Formatter formatter) throws NumberFormatException {
        int id = Integer.parseInt(formatter.apply(modelStringData.get(0)));
        String name = formatter.apply(modelStringData.get(1));
        return new Country(id, name);
    }
}
