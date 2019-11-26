package utils.readers.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;
import utils.readers.provider.BufferedReaderProvider;

/**
 * An implementation of the {@link PropertyReader}.
 */
public class PropertyFileReader implements PropertyReader {

    /**
     * @param resourceName the name of a .properties file.
     * @return a {@link Properties} object with read data.
     * @throws IOException if an error occurred when reading from the
     *                     input stream.
     */
    @Override
    public Properties read(BufferedReaderProvider readerProvider, String resourceName) throws IOException {
        final Properties properties = new Properties();
        try (BufferedReader reader = readerProvider.getBufferedReader(resourceName)) {
            properties.load(reader);
        }
        return properties;
    }
}