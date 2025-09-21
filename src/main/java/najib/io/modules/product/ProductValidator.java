package najib.io.modules.product;

import najib.io.modules.product.dto.ProductReqDto;
import najib.io.utils.validation.Validator;

public class ProductValidator {
    public static void validateCreate(ProductReqDto dto) {
        Validator validator = new Validator()
                .required("name", dto.getName(), "Name is required")
                .required("qty", dto.getQuantity(), "Qty is required");
        validator.throwIfErrors();
    }

    public static void validateUpdate(ProductReqDto dto) {
        Validator validator = new Validator()
                .notBlankIfPresent("name", dto.getName(), "Name must not blank")
                .notNullIfPresent("name", dto.getName(), "Name must not null")
                .positiveIfPresent("qty", dto.getQuantity(), "Qty must be > 0");
        validator.throwIfErrors();
    }
}
