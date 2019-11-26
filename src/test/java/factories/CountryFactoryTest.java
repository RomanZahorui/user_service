package factories;

import java.util.Arrays;
import java.util.List;
import model.models.City;
import model.models.Country;
import model.models.producer.impl.CountryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.execptions.NotValidDataException;
import utils.parsers.StringDataParser;

public class CountryFactoryTest {

    private final CountryFactory factory = new CountryFactory(new StringDataParser());

    @Test
    public void produceTest() {
        List<String> testData = Arrays.asList("1", "Test Country");
        Country test = factory.produce(testData, s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""));

        Assertions.assertEquals(testData.get(0), String.valueOf(test.getId()));
        Assertions.assertEquals(testData.get(1), String.valueOf(test.getName()));
    }

    @Test
    public void produceNotValidIdTest() {
        List<String> testData = Arrays.asList("1f", "Test Country");
        String exceptionMsg = "Id value is not valid! For input string: \"1f\"";

        try {
            Country test = factory.produce(testData, s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""));
        } catch (NotValidDataException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals(exceptionMsg, e.getMessage());
        }
    }

    @Test
    public void produceNotValidNullDataTest() {
        List<String> testData = Arrays.asList(null, null, null);
        String exceptionMsg = "Input data is not valid! [null, null, null]";

        try {
            Country test = factory.produce(testData, s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""));
            Assertions.fail("Expected NotValidEx");
        } catch (NotValidDataException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals(exceptionMsg, e.getMessage());
        }
    }
}
