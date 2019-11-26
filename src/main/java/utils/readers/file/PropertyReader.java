package utils.readers.file;

import java.util.Properties;
import utils.readers.BaseReader;

/**
 * An extension of {@link BaseReader} with {@link Properties}
 * parameter to read all lines from a specified file into
 * the properties file.
 */
@FunctionalInterface
public interface PropertyReader extends BaseReader<Properties> {
}
