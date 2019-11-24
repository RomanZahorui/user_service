package utils.readers.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import utils.readers.BaseReader;
import utils.readers.file.FileReader;

/**
 * An implementation of the {@link FileReader}. The class provides opening and
 * closing of an {@code InputStream} for loading it to the {@link Properties} object.
 */
public class PropertyReader implements BaseReader<Properties, String> {

    /**
     * @param resourceName the name of a .properties file.
     * @return a {@link Properties} object with read data.
     * @throws IOException if an error occurred when reading from the
     *                     input stream.
     */
    @Override
    public Properties read(String resourceName) throws IOException {
        final Properties properties = new Properties();
        try (InputStream propStream = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            properties.load(propStream);
        } catch (IOException e) {
            throw new IOException("Can't read property file : " + resourceName);
        }
        return properties;
    }
}
