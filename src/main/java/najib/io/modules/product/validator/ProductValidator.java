package najib.io.modules.product.validator;

import najib.io.modules.product.dto.ProductReqDto;
import najib.io.utils.validation.Validator;

public class ProductValidator {
    public static void validateCreate(ProductReqDto payload) {
        Validator.forModule("Product")
                .field("name", payload.getName())
                .notNull()
                .done()
                .field("qty", payload.getQuantity())
                .notNull()
                .done()
                .validate();
    }

    public static void validateUpdate(ProductReqDto payload) {
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
