package factories;

import java.util.Arrays;
import java.util.List;
import model.models.City;
import model.models.producer.impl.CityFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CityFactoryTest {

    private final CityFactory factory = new CityFactory();

    @Test
    public void produceTest() {
        List<String> testData = Arrays.asList("1", "Test City", "21");
        City testUser = factory.produce(testData, s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""));

        Assertions.assertEquals(testData.get(0), String.valueOf(testUser.getId()));
        Assertions.assertEquals(testData.get(1), String.valueOf(testUser.getName()));
        Assertions.assertEquals(testData.get(2), String.valueOf(testUser.getCountryId()));
    }
}
