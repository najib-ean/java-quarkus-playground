package najib.io.modules.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import najib.io.common.BaseMapper;
import najib.io.entities.ProductEntity;
import najib.io.entities.UserEntity;
import najib.io.modules.product.dto.ProductReqDto;
import najib.io.modules.product.dto.ProductResDto;
import najib.io.modules.user.UserMapper;
import najib.io.repositories.UserRepository;

@ApplicationScoped
public class ProductMapper extends BaseMapper<ProductEntity, ProductReqDto, ProductResDto> {
    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    @Override
    public ProductEntity toEntity(ProductReqDto req, ProductEntity productEntity) {
        UserEntity user = userRepository.findById(req.getUserId());
        if (user == null) {
            throw new BadRequestException("User not found");
        }

        if (productEntity == null) {
            productEntity = new ProductEntity();
        }

        productEntity.setUser(user);

        setIfNotNull(req.getName(), productEntity::setName);
        setIfNotNull(req.getQuantity(), productEntity::setQuantity);

        return productEntity;
    }

    @Override
    protected ProductEntity createEntity() {
        return new ProductEntity();
    }

    @Override
    protected ProductResDto createResponse() {
        return new ProductResDto();
    }

    @Override
    protected void mapToEntity(ProductReqDto req, ProductEntity entity) {
        UserEntity user = userRepository.findById(req.getUserId());
        if (user == null) {
            throw new BadRequestException("User not found");
        }

        entity.setUser(user);
        setIfNotNull(req.getName(), entity::setName);
        setIfNotNull(req.getQuantity(), entity::setQuantity);
    }

    @Override
    protected void mapToResponse(ProductEntity entity, ProductResDto res) {
        res.setName(entity.getName());
        res.setQuantity(entity.getQuantity());
        res.setUser(userMapper.toResponse(entity.getUser()));
    }
}
