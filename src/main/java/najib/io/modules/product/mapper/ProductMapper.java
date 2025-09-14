package najib.io.modules.product.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import najib.io.common.BaseMapper;
import najib.io.modules.product.dto.ProductRequestDto;
import najib.io.modules.product.dto.ProductResponseDto;
import najib.io.modules.product.entity.ProductEntity;

@ApplicationScoped
public class ProductMapper extends BaseMapper<ProductEntity, ProductRequestDto, ProductResponseDto> {
    @Override
    public ProductEntity toEntity(ProductRequestDto payload) {
        ProductEntity product = new ProductEntity();
        product.setName(payload.getName());
        product.setQuantity(payload.getQuantity());

        return product;
    }

    @Override
    public ProductResponseDto toResponse(ProductEntity productEntity) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(productEntity.getId());
        dto.setName(productEntity.getName());
        dto.setQuantity(productEntity.getQuantity());

        return dto;
    }
}
