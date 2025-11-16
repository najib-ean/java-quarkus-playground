package najib.io.utils.validation.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import najib.io.enums.Gender;

import java.util.Arrays;

public class ValidGenderValidator implements ConstraintValidator<ValidGender, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        return Arrays.stream(Gender.values()).anyMatch(gender -> gender.name().equalsIgnoreCase(value));
    }
}
