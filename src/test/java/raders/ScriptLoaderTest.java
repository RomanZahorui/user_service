package raders;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.readers.script.ScriptLoader;

public class ScriptLoaderTest {
    private final ScriptLoader reader = new ScriptLoader();

    @Test
    public void readTest() {
        String testPath = "test_schema.sql";
        String testLine = "CREATE SCHEMA `user_registry`;";
        try {
            String result = reader.read(testPath);
            Assertions.assertEquals(testLine, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readTestIOException() {
        String testPath = "test_schema.sql23";
        String testLine = "Can't read the file : test_schema.sql23";
        try {
            String result = reader.read(testPath);
        } catch (IOException e) {
            Assertions.assertEquals(testLine, e.getMessage());
        }
    }
}
