package model.producer.impl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import model.User;
import model.producer.Factory;
import utils.Formatter;
import utils.execptions.NotValidDataException;
import utils.parsers.DataParser;

/**
 * The class implements {@link Factory} interface. Produces an instance of the {@link User} base on data contained in
 * the {@code List<String>} parameter and {@link Formatter} to format these strings.
 */
public class UserFactory implements Factory<User> {

    private final DataParser<String> parser;

    public UserFactory(DataParser<String> parser) {
        this.parser = parser;
    }

    /**
     * Parses the list of string into related values.
     *
     * @param modelStringData a list of strings contained a state of a {@link User}.
     * @param formatter       to format the strings.
     * @return an instance of {@link User}.
     * @throws RuntimeException if a string cannot be parsed into a {@link LocalDate} object
     *                          or if the string does not contain a parsable integer.
     */
    @Override
    public User produce(List<String> modelStringData, Formatter formatter) throws NotValidDataException {
        if (modelStringData.size() != 4 || modelStringData.contains(null)) {
            throw new NotValidDataException("Input data is not valid! " + modelStringData);
        }

        try {
            int id = parser.parseInt(modelStringData.get(0), formatter);
            String name = parser.parseStr(modelStringData.get(1), formatter);
            LocalDate date = parser.parseLocalDate(modelStringData.get(2), formatter);
            int cityId = parser.parseInt(modelStringData.get(3), formatter);
            return new User(id, name, date, cityId);
        } catch (NumberFormatException e) {
            throw new NotValidDataException("Id value is not valid!", e);
        } catch (DateTimeParseException e) {
            throw new NotValidDataException("Day of birth is not valid!", e);
        }
    }
}
