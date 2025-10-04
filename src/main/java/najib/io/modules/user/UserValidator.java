package najib.io.modules.user;

import jakarta.enterprise.context.ApplicationScoped;
import najib.io.common.BaseValidator;
import najib.io.modules.user.dto.UserReqDto;
import najib.io.utils.validation.Validator;

@ApplicationScoped
public class UserValidator extends BaseValidator<UserReqDto> {
    @Override
    public void validateCreate(UserReqDto dto) {
        Validator validator = new Validator()
                .required("first_name", dto.getFirstName(), "first_name is required")
                .notContainOnlyNumbers("first_name", dto.getFirstName())
                .required("last_name", dto.getLastName(), "last_name is required")
                .required("address", dto.getAddress(), "address is required")
                .required("age", dto.getAge())
                .required("gender", dto.getGender());
        validator.throwIfErrors();
    }

    @Override
    public void validateUpdate(UserReqDto dto) {

    }
}
