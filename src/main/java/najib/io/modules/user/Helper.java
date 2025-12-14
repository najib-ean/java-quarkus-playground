package najib.io.modules.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BadRequestException;
import najib.io.enums.Gender;

@ApplicationScoped
public class Helper {
    public String toGenderString(Integer gender) {
        return switch (gender) {
            case 1 -> Gender.MALE.label();
            case 0 -> Gender.FEMALE.label();
            case null -> "Unknown";
            default -> throw new BadRequestException("Invalid gender value: " + gender);
        };
    }
}
