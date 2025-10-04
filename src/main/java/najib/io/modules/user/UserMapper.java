package najib.io.modules.user;

import jakarta.enterprise.context.ApplicationScoped;
import najib.io.common.BaseMapper;
import najib.io.modules.user.dto.UserReqDto;
import najib.io.modules.user.dto.UserResDto;

@ApplicationScoped
public class UserMapper extends BaseMapper<UserEntity, UserReqDto, UserResDto> {

    @Override
    public UserEntity toEntity(UserReqDto payload) {
        UserEntity user = new UserEntity();
        user.setFirstName(payload.getFirstName());
        user.setLastName(payload.getLastName());
        user.setAddress(payload.getAddress());
        user.setAge(payload.getAge());
        user.setGender(payload.getGender());

        return user;
    }

    @Override
    public UserEntity toEntity(UserReqDto payload, UserEntity userEntity) {
        userEntity.setFirstName(payload.getFirstName() != null ? payload.getFirstName() : userEntity.getFirstName());
        userEntity.setLastName(payload.getLastName() != null ? payload.getLastName() : userEntity.getLastName());
        userEntity.setAddress(payload.getAddress() != null ? payload.getAddress() : userEntity.getAddress());
        userEntity.setAge(payload.getAge() != null ? payload.getAge() : userEntity.getAge());
        userEntity.setGender(payload.getGender() != null ? payload.getGender() : userEntity.getGender());

        return userEntity;
    }

    @Override
    public UserResDto toResponse(UserEntity userEntity) {
        UserResDto userResDto = new UserResDto();
        userResDto.setId(userEntity.getId());
        userResDto.setFirstName(userEntity.getFirstName());
        userResDto.setLastName(userEntity.getLastName());
        userResDto.setAddress(userEntity.getAddress());
        userResDto.setAge(userEntity.getAge());
        userResDto.setGender(userEntity.getGender());
        userResDto.setCreatedAt(userEntity.getCreatedAt());
        userResDto.setUpdatedAt(userEntity.getUpdatedAt());

        return userResDto;
    }
}
