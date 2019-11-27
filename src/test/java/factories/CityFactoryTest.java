package factories;

import java.util.Arrays;
import java.util.List;
import model.City;
import model.producer.impl.CityFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.SystemMsg;
import utils.execptions.NotValidDataException;
import utils.parsers.StringDataParser;

public class CityFactoryTest {

    private final CityFactory factory = new CityFactory(new StringDataParser());
    private final String testName = "Test City";
    private final String testId = "1";
    private final String testWrongId = "1fffff";

    @Test
    public void produceNullValue() {
        List<String> testData = Arrays.asList(testId, testName, testId);
        Assertions.assertNotNull(
            factory.produce(testData, s -> s.replaceAll(SystemMsg.FORMAT_REGEX, ""))
        );
    }

    @Test
    public void produceTest() {
        List<String> testData = Arrays.asList(testId, testName, testId);
        City test = factory.produce(testData, s -> s.replaceAll(SystemMsg.FORMAT_REGEX, ""));

        Assertions.assertEquals(testData.get(0), String.valueOf(test.getId()));
        Assertions.assertEquals(testData.get(1), String.valueOf(test.getName()));
        Assertions.assertEquals(testData.get(2), String.valueOf(test.getCountryId()));
    }

    @Test
    public void produceNotValidIdTest() {
        List<String> testData = Arrays.asList(testWrongId, testName, testWrongId);
        Assertions.assertThrows(NotValidDataException.class, () -> {
            factory.produce(testData, s -> s.replaceAll(SystemMsg.FORMAT_REGEX, ""));
        });
    }

    @Test
    public void produceNotValidNullDataTest() {
        List<String> testData = Arrays.asList(null, null, null);
        Assertions.assertThrows(NotValidDataException.class, () -> {
            factory.produce(testData, s -> s.replaceAll(SystemMsg.FORMAT_REGEX, ""));
        });
    }
}
