package utils.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import utils.Formatter;


/**
 * Provides an interface to parse {@code T} objects into different result types.
 *
 * @param <T>
 */
public interface DataParser<T> {
    /**
     * @param data input data.
     * @param formatter to format the input data.
     * @return int representation of the input data.
     * @throws NumberFormatException if there are errors when converting to type {@code int}.
     */
    int convertToInt(T data, Formatter formatter) throws NumberFormatException;

    /**
     * @param data input data.
     * @param formatter to format the input data.
     * @return String representation of the input data.
     */
    String convertToString(T data, Formatter formatter);

    /**
     * @param data input data.
     * @param formatter to format the input data.
     * @return LocalDate representation of the input data.
     * @throws DateTimeParseException if there are errors when converting to type {@code LocalDate}.
     */
    LocalDate convertTOLocalDate(T data, Formatter formatter) throws DateTimeParseException;
}
