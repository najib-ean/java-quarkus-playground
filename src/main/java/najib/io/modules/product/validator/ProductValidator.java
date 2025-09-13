package najib.io.modules.product.validator;

import najib.io.modules.product.dto.ProductRequestDto;
import najib.io.utils.validation.Validator;

public class ProductValidator {
    public static void validateCreate(ProductRequestDto payload) {
        Validator.forModule("Product")
                .field("name", payload.getName())
                .notNull()
                .done()
                .field("qty", payload.getQuantity())
                .notNull()
                .done()
                .validate();
    }

    public static void validateUpdate(ProductRequestDto payload) {
        Validator.forModule("Product")
                .field("name", payload.getName())
                .notNull()
                .done()
                .field("qty", payload.getQuantity())
                .notNull()
                .done()
                .validate();
    }
}
