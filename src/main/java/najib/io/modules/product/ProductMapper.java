package najib.io.modules.product;

import jakarta.enterprise.context.ApplicationScoped;
import najib.io.common.BaseMapper;
import najib.io.entities.ProductEntity;
import najib.io.modules.product.dto.ProductReqDto;
import najib.io.modules.product.dto.ProductResDto;

@ApplicationScoped
public class ProductMapper extends BaseMapper<ProductEntity, ProductReqDto, ProductResDto> {
    @Override
    public ProductEntity toEntity(ProductReqDto payload, ProductEntity productEntity) {
        if (productEntity == null) {
            productEntity = new ProductEntity();
        }

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
    }
}
