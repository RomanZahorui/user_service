package utils.readers.file;

import java.util.List;
import utils.readers.BaseReader;

/**
 * An extension of {@link BaseReader} with {@code List<String>}
 * parameter to read all lines from a specified file.
 */
@FunctionalInterface
public interface RecordsReader extends BaseReader<List<String>> {
}
