package model.models.producer.impl;

import java.time.LocalDate;
import java.util.List;
import model.models.User;
import model.models.producer.Factory;
import utils.Formatter;

/**
 * The class implements {@link Factory} interface. Produces an instance of the {@link User} base on data contained in
 * the {@code List<String>} parameter and {@link Formatter} to format these strings.
 */
public class UserFactory implements Factory<User> {

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
    public User produce(List<String> modelStringData, Formatter formatter) throws RuntimeException {
        int id = Integer.parseInt(formatter.apply(modelStringData.get(0)));
        String name = formatter.apply(modelStringData.get(1));
        LocalDate date = LocalDate.parse(formatter.apply(modelStringData.get(2)));
        int cityId = Integer.parseInt(formatter.apply(modelStringData.get(3)));
        return new User(id, name, date, cityId);
    }
}
