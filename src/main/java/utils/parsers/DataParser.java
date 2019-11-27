package utils.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import utils.Formatter;

//TODO javaDocs
public interface DataParser<T> {
    int parseInt(T data, Formatter formatter) throws NumberFormatException;

    String parseStr(T data, Formatter formatter);

    LocalDate parseLocalDate(T data, Formatter formatter) throws DateTimeParseException;
}
