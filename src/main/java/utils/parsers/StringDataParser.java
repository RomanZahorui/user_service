package utils.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import utils.Formatter;

//TODO javaDocs and tests
public class StringDataParser implements DataParser<String> {

    @Override
    public int parseInt(String data, Formatter formatter) throws NumberFormatException {
        return Integer.parseInt(formatter.apply(data));
    }

    @Override
    public String parseStr(String data, Formatter formatter) {
        return formatter.apply(data);
    }

    @Override
    public LocalDate parseLocalDate(String data, Formatter formatter) throws DateTimeParseException {
        return LocalDate.parse(formatter.apply(data));
    }
}
