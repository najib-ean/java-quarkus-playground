package najib.io.modules.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import najib.io.common.BaseMapper;
import najib.io.common.BaseRepository;
import najib.io.common.BaseService;
import najib.io.common.BaseValidator;
import najib.io.entities.ProductEntity;
import najib.io.modules.product.dto.ProductReqDto;
import najib.io.modules.product.dto.ProductResDto;
import najib.io.repositories.ProductRepository;

@ApplicationScoped
public class ProductService extends BaseService<ProductEntity, ProductReqDto, ProductResDto> {

    @Inject
    ProductRepository productRepository;

    @Inject
    ProductMapper productMapper;

    @Inject
    ProductValidator productValidator;

    @Override
    protected BaseRepository<ProductEntity> repository() {
        return productRepository;
    }

    @Override
    protected BaseMapper<ProductEntity, ProductReqDto, ProductResDto> mapper() {
        return productMapper;
    }

    @Override
    protected String moduleName() {
        return "product";
    }

    @Override
    protected BaseValidator<ProductReqDto> validator() {
        return productValidator;
    }
}
