package factories;

import java.util.Arrays;
import java.util.List;
import model.City;
import model.producer.impl.CityFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.execptions.NotValidDataException;
import utils.parsers.StringDataParser;

public class CityFactoryTest {

    private final CityFactory factory = new CityFactory(new StringDataParser());

    @Test
    public void produceTest() {
        List<String> testData = Arrays.asList("1", "Test City", "21");
        City testUser = factory.produce(testData, s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""));

        Assertions.assertEquals(testData.get(0), String.valueOf(testUser.getId()));
        Assertions.assertEquals(testData.get(1), String.valueOf(testUser.getName()));
        Assertions.assertEquals(testData.get(2), String.valueOf(testUser.getCountryId()));
    }

    @Test
    public void produceNotValidIdTest() {
        List<String> testData = Arrays.asList("1f", "Test City", "21f");
        String exceptionMsg = "Id value is not valid! For input string: \"1f\"";

        try {
            City testUser = factory.produce(testData, s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""));
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
            City testUser = factory.produce(testData, s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""));
        } catch (NotValidDataException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals(exceptionMsg, e.getMessage());
        }
    }
}
