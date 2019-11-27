package factories;

import java.util.Arrays;
import java.util.List;
import model.User;
import model.producer.impl.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.SystemMsg;
import utils.execptions.NotValidDataException;
import utils.parsers.StringDataParser;

public class UserFactoryTest {

    private final UserFactory factory = new UserFactory(new StringDataParser());
    private final String testId = "1";
    private final String testWrongId = "1fffff";
    private final String testName = "Test User";
    private final String testBirthday = "1999-01-01";

    @Test
    public void produceNullValue() {
        List<String> testData = Arrays.asList(testId, testName, testBirthday, testId);
        Assertions.assertNotNull(
            factory.produce(testData, s -> s.replaceAll(SystemMsg.FORMAT_REGEX, ""))
        );
    }

    @Test
    public void produceTest() {
        List<String> testUserData = Arrays.asList(testId, testName, testBirthday, testId);
        User test = factory.produce(testUserData,  s -> s.replaceAll(SystemMsg.FORMAT_REGEX, ""));

        Assertions.assertEquals(testUserData.get(0), String.valueOf(test.getId()));
        Assertions.assertEquals(testUserData.get(1), String.valueOf(test.getName()));
        Assertions.assertEquals(testUserData.get(2), String.valueOf(test.getBirthday()));
        Assertions.assertEquals(testUserData.get(3), String.valueOf(test.getCityId()));
    }

    @Test
    public void produceNotValidIdTest() {
        List<String> testData = Arrays.asList(testWrongId, testName, testBirthday, testWrongId);
        Assertions.assertThrows(NotValidDataException.class, () -> {
            factory.produce(testData, s -> s.replaceAll(SystemMsg.FORMAT_REGEX, ""));
        });
    }

    @Test
    public void produceNotValidNullDataTest() {
        List<String> testData = Arrays.asList(null, null, null, null);
        Assertions.assertThrows(NotValidDataException.class, () -> {
            factory.produce(testData, s -> s.replaceAll(SystemMsg.FORMAT_REGEX, ""));
        });
    }
}
