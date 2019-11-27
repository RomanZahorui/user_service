package factories;

import java.util.Arrays;
import java.util.List;
import model.Country;
import model.producer.impl.CountryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.SystemMsg;
import utils.execptions.NotValidDataException;
import utils.parsers.StringDataParser;

public class CountryFactoryTest {

    private final CountryFactory factory = new CountryFactory(new StringDataParser());
    private final String testName = "Test Country";
    private final String testId = "1";
    private final String testWrongId = "1fffff";

    @Test
    public void produceNullValue() {
        List<String> testData = Arrays.asList(testId, testName);
        Assertions.assertNotNull(
            factory.produce(testData, s -> s.replaceAll(SystemMsg.FORMAT_REGEX, ""))
        );
    }

    @Test
    public void produceTest() {
        List<String> testData = Arrays.asList(testId, testName);
        Country test = factory.produce(testData, s -> s.replaceAll(SystemMsg.FORMAT_REGEX, ""));

        Assertions.assertEquals(testData.get(0), String.valueOf(test.getId()));
        Assertions.assertEquals(testData.get(1), String.valueOf(test.getName()));
    }

    @Test
    public void produceNotValidIdTest() {
        List<String> testData = Arrays.asList(testWrongId, testName);
        Assertions.assertThrows(NotValidDataException.class, () -> {
            factory.produce(testData, s -> s.replaceAll(SystemMsg.FORMAT_REGEX, ""));
        });
    }

    @Test
    public void produceNotValidNullDataTest() {
        List<String> testData = Arrays.asList(null, null);
        Assertions.assertThrows(NotValidDataException.class, () -> {
            factory.produce(testData, s -> s.replaceAll(SystemMsg.FORMAT_REGEX, ""));
        });
    }
}
