package raders;

import java.io.IOException;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.readers.provider.ResourceFileReaderProvider;
import utils.readers.provider.BufferedReaderProvider;
import utils.readers.file.PropertyFileReader;

public class PropertyFileReaderTest {
    private final PropertyFileReader reader = new PropertyFileReader();
    private final BufferedReaderProvider readerProvider = new ResourceFileReaderProvider();

    @Test
    public void readTest() {
        String testPath = "test.properties";
        String testLine = "test.jdbc.driver";
        try {
            Properties result = reader.read(readerProvider, testPath);
            Assertions.assertEquals("com.mysql.jdbc.Driver", result.getProperty(testLine));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readTestIOException() {
        String testPath = "test.properties23";
        String testLine = "Can't read property file : test.properties23";
        try {
            Properties result = reader.read(readerProvider, testPath);
        } catch (IOException e) {
            Assertions.assertEquals(testLine, e.getMessage());
        }
    }
}
