package najib.io.utils.validation;

import java.util.HashMap;
import java.util.Map;

public class Validator {
    private final String moduleName;
    private final Map<String, Object> errors = new HashMap<>();
    private String currentField;
    private Object currentValue;

    private Validator(String moduleName) {
        this.moduleName = moduleName;
    }

    public static Validator forModule(String moduleName) {
        return new Validator(moduleName);
    }

    //validating specific field.
    public FieldValidator field(String fieldName, Object value) {
        this.currentField = fieldName;
        this.currentValue = value;
        return new FieldValidator(this, fieldName, value);
    }

    //collect errors.
    void addError(String fieldName, String message) {
        errors.put(fieldName, message);
    }

    public void validate() {
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public static class FieldValidator {
        private final Validator parent;
        private final String fieldName;
        private final Object value;

        FieldValidator(Validator parent, String fieldName, Object value) {
            this.parent = parent;
            this.fieldName = fieldName;
            this.value = value;
        }

        public FieldValidator notNull() {
            if (this.value == null) {
                parent.addError(fieldName, "Field " + fieldName + " must not be null");
            }
            return this;
        }

        public FieldValidator minLength(int min) {
            if (value != null && value instanceof String str && str.length() < min) {
                parent.addError(fieldName, parent.moduleName + " " + fieldName + " must be at least " + min + " characters");
            }
            return this;
        }

        public FieldValidator maxLength(int max) {
            if (value != null && value instanceof String str && str.length() > max) {
                parent.addError(fieldName, parent.moduleName + " " + fieldName + " must be at most " + max + " characters");
            }
            return this;
        }

        public Validator done() {
            return parent;
        }
    }
}
