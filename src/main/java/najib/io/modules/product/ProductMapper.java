package najib.io.modules.product;

import jakarta.enterprise.context.ApplicationScoped;
import najib.io.common.BaseMapper;
import najib.io.modules.product.dto.ProductReqDto;
import najib.io.modules.product.dto.ProductResDto;

@ApplicationScoped
public class ProductMapper extends BaseMapper<ProductEntity, ProductReqDto, ProductResDto> {
    @Override
    public ProductEntity toEntity(ProductReqDto payload) {
        ProductEntity product = new ProductEntity();
        product.setName(payload.getName());
        product.setQuantity(payload.getQuantity());

        return product;
    }

    @Override
    public ProductEntity toEntity(ProductReqDto payload, ProductEntity entity) {
        entity.setName(payload.getName() != null ? payload.getName() : entity.getName());
        entity.setQuantity(payload.getQuantity() != null ? payload.getQuantity() : entity.getQuantity());

        return entity;
    }

    @Override
    public ProductResDto toResponse(ProductEntity productEntity) {
        ProductResDto dto = new ProductResDto();
        dto.setId(productEntity.getId());
        dto.setName(productEntity.getName());
        dto.setQuantity(productEntity.getQuantity());
        dto.setCreatedAt(productEntity.getCreatedAt());
        dto.setUpdatedAt(productEntity.getUpdatedAt());

        return dto;
    }
}
