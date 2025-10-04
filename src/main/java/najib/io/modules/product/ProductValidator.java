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
                .required("name", dto.getName())
                .notContainOnlyNumbers("name", dto.getName())
                .required("qty", dto.getQuantity());
        validator.throwIfErrors();
    }

    @Override
    public void validateUpdate(ProductReqDto dto) {
        Validator validator = new Validator()
                .notBlankIfPresent("name", dto.getName())
                .notContainOnlyNumbers("name", dto.getName())
                .positiveIfPresent("qty", dto.getQuantity());
        validator.throwIfErrors();
    }
}
