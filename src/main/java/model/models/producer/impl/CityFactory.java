package model.models.producer.impl;

import java.util.List;
import model.models.City;
import model.models.producer.Factory;
import utils.Formatter;
import utils.execptions.NotValidDataException;
import utils.parsers.DataParser;

/**
 * The class implements {@link Factory} interface. Produces an instance of the {@link City} base on data contained in
 * the {@code List<String>} parameter and {@link Formatter} to format these strings.
 */
public class CityFactory implements Factory<City> {

    private final DataParser<String> parser;

    public CityFactory(DataParser<String> parser) {
        this.parser = parser;
    }
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
    public City produce(List<String> modelStringData, Formatter formatter) throws NotValidDataException {
        if (modelStringData.size() != 3 || modelStringData.contains(null)) {
            throw new NotValidDataException("Input data is not valid! " + modelStringData);
        }

        try {
            int id = parser.parseInt(modelStringData.get(0), formatter);
            String name = parser.parseStr(modelStringData.get(1), formatter);
            int countryId = parser.parseInt(modelStringData.get(2), formatter);
            return new City(id, name, countryId);
        } catch (NumberFormatException e) {
            throw new NotValidDataException("Id value is not valid! " + e.getMessage());
        }
    }
}
