package najib.io.modules.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import najib.io.common.BaseMapper;
import najib.io.entities.ProductEntity;
import najib.io.entities.UserEntity;
import najib.io.modules.product.dto.ProductResDto;
import najib.io.modules.user.dto.UserReqDto;
import najib.io.modules.user.dto.UserResDto;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserMapper extends BaseMapper<UserEntity, UserReqDto, UserResDto> {
    @Inject
    Helper helper;

    @Override
    public UserEntity toEntity(UserReqDto payload, UserEntity userEntity) {
        if (userEntity == null) {
            userEntity = new UserEntity();
        }

        setIfNotNull(payload.getFirstName(), userEntity::setFirstName);
        setIfNotNull(payload.getLastName(), userEntity::setLastName);
        setIfNotNull(payload.getAddress(), userEntity::setAddress);
        setIfNotNull(payload.getAge(), userEntity::setAge);

        Integer gender = payload.getGender() == null ? null : toGenderCode(payload.getGender());
        setIfNotNull(gender, userEntity::setGender);

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
        response.setGender(helper.toGenderString(entity.getGender()));
        response.setProducts(getProductResDto(entity.getProduct()));
    }

    private List<ProductResDto> getProductResDto(List<ProductEntity> products) {
        if (products == null) {
            return new ArrayList<>();
        }

        List<ProductResDto> productResDtos = new ArrayList<>();
        for (ProductEntity product : products) {
            ProductResDto productResDto = new ProductResDto();
            productResDto.setId(product.getId());
            productResDto.setName(product.getName());
            productResDto.setQuantity(product.getQuantity());

            productResDtos.add(productResDto);
        }

        return productResDtos;
    }

    private int toGenderCode(String gender) {
        return switch (gender.toLowerCase()) {
            case "male" -> 1;
            case "female" -> 0;
            default -> throw new BadRequestException("Invalid gender value: " + gender);
        };
    }
}
