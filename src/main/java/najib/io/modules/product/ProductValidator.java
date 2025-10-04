package najib.io.modules.product;

import jakarta.enterprise.context.ApplicationScoped;
import najib.io.common.BaseValidator;
import najib.io.modules.product.dto.ProductReqDto;
import najib.io.utils.validation.Validator;

@ApplicationScoped
public class ProductValidator extends BaseValidator<ProductReqDto> {
    @Override
    public void validateCreate(ProductReqDto dto) {
        Validator validator = new Validator()
                .required("name", dto.getName(), "Name is required")
                .required("qty", dto.getQuantity());
        validator.throwIfErrors();
    }

    @Override
    public void validateUpdate(ProductReqDto dto) {
        Validator validator = new Validator()
                .notBlankIfPresent("name", dto.getName(), "Name must not blank")
                .positiveIfPresent("qty", dto.getQuantity(), "Qty must be > 0");
        validator.throwIfErrors();
    }
}
