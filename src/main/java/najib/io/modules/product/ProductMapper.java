package najib.io.modules.product;

import jakarta.annotation.Nullable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import najib.io.common.BaseMapper;
import najib.io.entities.ProductEntity;
import najib.io.entities.UserEntity;
import najib.io.modules.product.dto.ProductReqDto;
import najib.io.modules.product.dto.ProductResDto;
import najib.io.modules.user.Helper;
import najib.io.modules.user.dto.UserResDto;
import najib.io.repositories.UserRepository;

@ApplicationScoped
public class ProductMapper extends BaseMapper<ProductEntity, ProductReqDto, ProductResDto> {
    @Inject
    UserRepository userRepository;

    @Inject
    Helper helper;

    @Override
    public ProductEntity toEntity(ProductReqDto payload, ProductEntity productEntity) {
        UserEntity user = userRepository.findById(payload.getUserId());
        if (user == null) {
            throw new BadRequestException("User not found");
        }

        if (productEntity == null) {
            productEntity = new ProductEntity();
        }

        productEntity.setUser(user);

        setIfNotNull(payload.getName(), productEntity::setName);
        setIfNotNull(payload.getQuantity(), productEntity::setQuantity);

        return productEntity;
    }

    @Override
    protected ProductResDto toResponse() {
        return new ProductResDto();
    }

    @Override
    protected void mapResponse(ProductEntity entity, ProductResDto response) {
        response.setName(entity.getName());
        response.setQuantity(entity.getQuantity());
        response.setUser(getUserResDto(entity.getUser()));
    }

    private UserResDto getUserResDto(@Nullable UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserResDto userResDto = new UserResDto();
        userResDto.setId(userEntity.getId());
        userResDto.setFirstName(userEntity.getFirstName());
        userResDto.setLastName(userEntity.getLastName());
        userResDto.setAddress(userEntity.getAddress());
        userResDto.setGender(helper.toGenderString(userEntity.getGender()));
        return userResDto;
    }
}
