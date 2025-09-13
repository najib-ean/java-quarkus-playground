package najib.io.modules.product.mapper;

import najib.io.modules.product.dto.ProductRequestDto;
import najib.io.modules.product.dto.ProductResponseDto;
import najib.io.modules.product.entity.ProductEntity;

public class ProductMapper {
    public static ProductEntity toEntity(ProductRequestDto payload) {
        ProductEntity product = new ProductEntity();
        product.setName(payload.getName());
        product.setQuantity(payload.getQuantity());

        return product;
    }

    public static ProductResponseDto toResponse(ProductEntity productEntity) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setName(productEntity.getName());

        return dto;
    }
}
