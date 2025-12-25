package najib.io.modules.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import najib.io.common.BaseMapper;
import najib.io.entities.UserEntity;
import najib.io.modules.product.ProductMapper;
import najib.io.modules.user.dto.UserReqDto;
import najib.io.modules.user.dto.UserResDto;

@ApplicationScoped
public class UserMapper extends BaseMapper<UserEntity, UserReqDto, UserResDto> {
    @Inject
    ProductMapper productMapper;

    @Override
    protected UserEntity createEntity() {
        return new UserEntity();
    }

    @Override
    protected UserResDto createResponse() {
        return new UserResDto();
    }

    @Override
    protected void mapToEntity(UserReqDto req, UserEntity entity) {
        setIfNotNull(req.getFirstName(), entity::setFirstName);
        setIfNotNull(req.getLastName(), entity::setLastName);
        setIfNotNull(req.getAddress(), entity::setAddress);
        setIfNotNull(req.getAge(), entity::setAge);
        setIfNotNull(req.getGender(), entity::setGender);
    }

    @Override
    protected void mapToResponse(UserEntity entity, UserResDto res) {
        res.setFirstName(entity.getFirstName());
        res.setLastName(entity.getLastName());
        res.setAddress(entity.getAddress());
        res.setAge(entity.getAge());
        res.setGender(entity.getGender());
        res.setProducts(productMapper.toResponse(entity.getProducts()));
    }
}
