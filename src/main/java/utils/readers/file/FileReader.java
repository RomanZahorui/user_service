package utils.readers.file;

import java.util.List;
import utils.readers.BaseReader;

/**
 * An extension of {@link BaseReader} with {@code List<String>} and {@code String} types.
 */
@FunctionalInterface
public interface FileReader extends BaseReader<List<String>, String> {
}
