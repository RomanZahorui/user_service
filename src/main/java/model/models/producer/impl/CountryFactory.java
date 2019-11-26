package model.models.producer.impl;

import java.util.List;
import model.models.Country;
import model.models.producer.Factory;
import utils.Formatter;
import utils.execptions.NotValidDataException;
import utils.parsers.DataParser;

/**
 * The class implements {@link Factory} interface. Produces an instance of the {@link Country} base on data contained in
 * the {@code List<String>} parameter and {@link Formatter} to format these strings.
 */
public class CountryFactory implements Factory<Country> {

    private final DataParser<String> parser;

    public CountryFactory(DataParser<String> parser) {
        this.parser = parser;
    }

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
    public Country produce(List<String> modelStringData, Formatter formatter) throws NotValidDataException {
        if (modelStringData.size() != 2 || modelStringData.contains(null)) {
            throw new NotValidDataException("Input data is not valid! " + modelStringData);
        }

        try {
            int id = parser.parseInt(modelStringData.get(0), formatter);
            String name = parser.parseStr(modelStringData.get(1), formatter);
            return new Country(id, name);
        } catch (NumberFormatException e) {
            throw new NotValidDataException("Id value is not valid! ", e);
        }
    }
}
