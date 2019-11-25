package factories;

import java.util.Arrays;
import java.util.List;
import model.models.User;
import model.models.producer.impl.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserFactoryTest {

    private final UserFactory factory = new UserFactory();

    @Test
    public void produceTest() {
        List<String> testUserData = Arrays.asList("1", "Test User", "1999-01-01", "23");
        User testUser = factory.produce(testUserData,  s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""));

        Assertions.assertEquals(testUserData.get(0), String.valueOf(testUser.getId()));
        Assertions.assertEquals(testUserData.get(1), String.valueOf(testUser.getName()));
        Assertions.assertEquals(testUserData.get(2), String.valueOf(testUser.getBirthday()));
        Assertions.assertEquals(testUserData.get(3), String.valueOf(testUser.getCityId()));
    }
}
