package najib.io;

import io.quarkus.test.junit.QuarkusTest;
import najib.io.enums.Gender;
import najib.io.utils.PasswordUtil;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PasswordTest {

    @Test
    public void testPassword() {
        String hashedPassword = PasswordUtil.hash("password123");
        System.out.println(hashedPassword);

        if (PasswordUtil.verify("password", hashedPassword)) {
            System.out.println("Password verified");
        } else {
            System.out.println("Password verification failed");
        }
    }

    @Test
    public void testIgnoreCase() {
        String male = "MALE";

        String result = male.equalsIgnoreCase(Gender.MALE.label()) ? "yes" : "Female";

        System.out.println(result);
    }
}
