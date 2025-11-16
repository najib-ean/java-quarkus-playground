package najib.io.utils.validation.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlphabetOnlyValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlphabetOnly {
    String message() default "must contain only letters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
