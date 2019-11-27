package utils.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import utils.Formatter;


/**
 * Provides an interface to convert an input string object into different result types.
 */
public class StringDataParser implements DataParser<String> {

    /**
     * @param data      input {@code String} data.
     * @param formatter to format the input data.
     * @return int representation of the input data.
     * @throws NumberFormatException if there are errors when converting to type {@code int}.
     */
    @Override
    public int convertToInt(String data, Formatter formatter) throws NumberFormatException {
        return Integer.parseInt(formatter.apply(data));
    }

    /**
     * @param data      input {@code String} data.
     * @param formatter to format the input data.
     * @return formatted string.
     */
    @Override
    public String convertToString(String data, Formatter formatter) {
        return formatter.apply(data);
    }

    /**
     * @param data      input {@code String} data.
     * @param formatter to format the input data.
     * @return LocalDate representation of the input string.
     * @throws DateTimeParseException if there are errors when converting to type {@code LocalDate}.
     */
    @Override
    public LocalDate convertTOLocalDate(String data, Formatter formatter) throws DateTimeParseException {
        return LocalDate.parse(formatter.apply(data));
    }
}
