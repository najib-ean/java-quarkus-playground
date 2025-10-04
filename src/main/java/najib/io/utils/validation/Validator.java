package najib.io.utils.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Validator {
    private final Map<String, String> errors = new HashMap<>();

    /**
     * must not contain numbers
     */
    public Validator notContainOnlyNumbers(String field, String value) {
        if (value != null && value.matches("\\d+"))
            errors.put(field, field + " must not contain only numbers");
        return this;
    }

    /**
     * required (must exist)
     */
    public Validator required(String field, Object value) {
        if (value == null) errors.put(field, field + " is required");
        return this;
    }

    /**
     * not blank if provided (PATCH-friendly)
     */
    public Validator notBlankIfPresent(String field, String value) {
        if (value != null && value.isBlank()) errors.put(field, field + " must not be blank");
        return this;
    }

    /**
     * contained specific array
     */
    public Validator genderValidator(String field, String value) {
        if (value != null) {
            List<String> allowedGender = List.of("male", "female");

            if (!allowedGender.contains(value.toLowerCase())) {
                errors.put(field, field + " must be one of " + allowedGender);
            }
        }

        return this;
    }

    /**
     * positive if provided
     */
    public Validator positiveIfPresent(String field, Number value) {
        if (value != null && value.doubleValue() <= 0)
            errors.put(field, field + " must be a positive number");
        return this;
    }

    /**
     * min length
     */
    public Validator minLength(String field, String value, int min, String msg) {
        if (value != null && value.length() < min) errors.put(field, msg);
        return this;
    }

    /**
     * max length
     */
    public Validator maxLength(String field, String value, int max, String msg) {
        if (value != null && value.length() > max) errors.put(field, msg);
        return this;
    }

    /**
     * pattern/regex
     */
    public Validator matches(String field, String value, String regex, String msg) {
        if (value != null && !Pattern.matches(regex, value)) errors.put(field, msg);
        return this;
    }

    /**
     * email format (basic)
     */
    public Validator email(String field, String value, String msg) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (value != null && !Pattern.matches(regex, value)) errors.put(field, msg);
        return this;
    }

    /**
     * throw if any errors collected
     */
    public void throwIfErrors() {
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
