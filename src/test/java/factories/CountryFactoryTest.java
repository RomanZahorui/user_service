package factories;

import java.util.Arrays;
import java.util.List;
import model.models.Country;
import model.models.producer.impl.CountryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountryFactoryTest {

    private final CountryFactory factory = new CountryFactory();

    @Test
    public void produceTest() {
        List<String> testData = Arrays.asList("1", "Test Country");
        Country testUser = factory.produce(testData, s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""));

        Assertions.assertEquals(testData.get(0), String.valueOf(testUser.getId()));
        Assertions.assertEquals(testData.get(1), String.valueOf(testUser.getName()));
    }
}
