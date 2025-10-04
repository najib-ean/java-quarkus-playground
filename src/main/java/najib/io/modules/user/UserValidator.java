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
                .required("first_name", dto.getFirstName())
                .notContainOnlyNumbers("first_name", dto.getFirstName())
                .required("last_name", dto.getLastName())
                .notContainOnlyNumbers("last_name", dto.getLastName())
                .required("address", dto.getAddress())
                .notContainOnlyNumbers("address", dto.getAddress())
                .required("age", dto.getAge())
                .positiveIfPresent("age", dto.getAge())
                .required("gender", dto.getGender())
                .genderValidator("gender", dto.getGender());
        validator.throwIfErrors();
    }

    @Override
    public void validateUpdate(UserReqDto dto) {
        Validator validator = new Validator()
                .notBlankIfPresent("first_name", dto.getFirstName())
                .notContainOnlyNumbers("first_name", dto.getFirstName())
                .notBlankIfPresent("last_name", dto.getLastName())
                .notContainOnlyNumbers("last_name", dto.getLastName())
                .notBlankIfPresent("address", dto.getAddress())
                .notContainOnlyNumbers("address", dto.getAddress())
                .positiveIfPresent("age", dto.getAge())
                .genderValidator("gender", dto.getGender());
        validator.throwIfErrors();
    }
}
