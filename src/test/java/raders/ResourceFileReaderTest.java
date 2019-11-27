package raders;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.readers.provider.ResourceFileReaderProvider;
import utils.readers.provider.BufferedReaderProvider;
import utils.readers.file.CSVFileReader;

public class ResourceFileReaderTest {
    private final CSVFileReader reader = new CSVFileReader();
    private final BufferedReaderProvider readerProvider = new ResourceFileReaderProvider();

    @Test
    public void readTest() {
        String testPath = "test.properties";
        String testLine = "# MySQL DB properties";
        try {
            List<String> result = reader.read(readerProvider, testPath);
            Assertions.assertEquals(testLine, result.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readTestIOException() {
        String testPath = "test.properties23";
        String testLine = "Can't read the file : test.properties23";
        try {
            List<String> result = reader.read(readerProvider, testPath);
        } catch (IOException e) {
            Assertions.assertEquals(testLine, e.getMessage());
        }
    }
}
