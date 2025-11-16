package najib.io.utils.validation.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AlphabetOnlyValidator implements ConstraintValidator<AlphabetOnly, String> {
    private static final String regex = "^[\\p{L} ]*$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        return value.matches(regex);
    }
}
