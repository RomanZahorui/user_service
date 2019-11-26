package factories;

import java.util.Arrays;
import java.util.List;
import model.models.User;
import model.models.producer.impl.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.execptions.NotValidDataException;
import utils.parsers.StringDataParser;

public class UserFactoryTest {

    private final UserFactory factory = new UserFactory(new StringDataParser());

    @Test
    public void produceTest() {
        List<String> testUserData = Arrays.asList("1", "Test User", "1999-01-01", "23");
        User testUser = factory.produce(testUserData,  s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""));

        Assertions.assertEquals(testUserData.get(0), String.valueOf(testUser.getId()));
        Assertions.assertEquals(testUserData.get(1), String.valueOf(testUser.getName()));
        Assertions.assertEquals(testUserData.get(2), String.valueOf(testUser.getBirthday()));
        Assertions.assertEquals(testUserData.get(3), String.valueOf(testUser.getCityId()));
    }

    @Test
    public void produceNotValidIdTest() {
        List<String> testData = Arrays.asList("1f", "Test User", "1999-01-01", "23");
        String exceptionMsg = "Id value is not valid! For input string: \"1f\"";

        try {
            User test = factory.produce(testData, s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""));
        } catch (NotValidDataException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals(exceptionMsg, e.getMessage());
        }
    }

    @Test
    public void produceNotValidNullDataTest() {
        List<String> testData = Arrays.asList(null, null, null, null);
        String exceptionMsg = "Input data is not valid! [null, null, null, null]";

        try {
            User test = factory.produce(testData, s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""));
        } catch (NotValidDataException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals(exceptionMsg, e.getMessage());
        }
    }
}
