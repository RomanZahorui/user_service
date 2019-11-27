package raders;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.readers.provider.ResourceFileReaderProvider;
import utils.readers.provider.BufferedReaderProvider;
import utils.readers.file.CSVFileReader;

public class ResourceFileReaderTest {
    private final CSVFileReader reader = new CSVFileReader();
    private final BufferedReaderProvider readerProvider = new ResourceFileReaderProvider();

    @Test
    public void readNotNullTest() throws IOException {
        String testPath = "test.properties";
        Assertions.assertNotNull(reader.read(readerProvider, testPath));
    }

    @Test
    public void readTest() throws IOException {
        String testPath = "test.properties";
        String testLine = "# MySQL DB properties";
        List<String> result = reader.read(readerProvider, testPath);
        Assertions.assertEquals(testLine, result.get(0));
    }

    @Test
    public void readTestIOException() {
        String testPath = "test.properties23";
        Assertions.assertThrows(IOException.class, () -> reader.read(readerProvider, testPath));
    }
}
