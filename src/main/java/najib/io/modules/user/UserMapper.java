package najib.io.modules.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BadRequestException;
import najib.io.common.BaseMapper;
import najib.io.entities.UserEntity;
import najib.io.modules.user.dto.UserReqDto;
import najib.io.modules.user.dto.UserResDto;

@ApplicationScoped
public class UserMapper extends BaseMapper<UserEntity, UserReqDto, UserResDto> {
    @Override
    public UserEntity toEntity(UserReqDto payload, UserEntity userEntity) {
        if (userEntity == null) {
            userEntity = new UserEntity();
        }

        setIfNotNull(payload.getFirstName(), userEntity::setFirstName);
        setIfNotNull(payload.getLastName(), userEntity::setLastName);
        setIfNotNull(payload.getAddress(), userEntity::setAddress);
        setIfNotNull(payload.getAge(), userEntity::setAge);
        setIfNotNull(toGenderCode(payload.getGender()), userEntity::setGender);

        return userEntity;
    }

    @Override
    protected UserResDto toResponse() {
        return new UserResDto();
    }

    @Override
    protected void mapResponse(UserEntity entity, UserResDto response) {
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setAddress(entity.getAddress());
        response.setAge(entity.getAge());
        response.setGender(entity.getGender());
    }

    private int toGenderCode(String gender) {
        return switch (gender.toLowerCase()) {
            case "male" -> 1;
            case "female" -> 0;
            default -> throw new BadRequestException("Invalid gender value: " + gender);
        };
    }
}
