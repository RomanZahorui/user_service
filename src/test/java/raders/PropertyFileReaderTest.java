package raders;

import java.io.IOException;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.readers.file.PropertyFileReader;
import utils.readers.provider.BufferedReaderProvider;
import utils.readers.provider.ResourceFileReaderProvider;

public class PropertyFileReaderTest {
    private final PropertyFileReader reader = new PropertyFileReader();
    private final BufferedReaderProvider readerProvider = new ResourceFileReaderProvider();

    @Test
    public void readNotNullTest() throws IOException {
        String testPath = "test.properties";
        Assertions.assertNotNull(reader.read(readerProvider, testPath));
    }

    @Test
    public void readTest() throws IOException {
        String testPath = "test.properties";
        String testLine = "test.jdbc.driver";
        Properties result = reader.read(readerProvider, testPath);
        Assertions.assertEquals("com.mysql.jdbc.Driver", result.getProperty(testLine));
    }

    @Test
    public void readTestIOException() {
        String testPath = "test.properties23";
        Assertions.assertThrows(IOException.class, () -> {
            reader.read(readerProvider, testPath);
        });
    }
}
