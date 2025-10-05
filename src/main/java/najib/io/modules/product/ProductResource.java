package najib.io.modules.product;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import najib.io.common.BaseMapper;
import najib.io.common.BaseRepository;
import najib.io.common.BaseResource;
import najib.io.common.BaseService;
import najib.io.modules.product.dto.ProductReqDto;
import najib.io.modules.product.dto.ProductResDto;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/product")
@APIResponse(
        responseCode = "200",
        content = @Content(schema = @Schema(implementation = ProductSingleResponse.class))
)
public class ProductResource extends BaseResource<ProductEntity, ProductReqDto, ProductResDto> {

    @Inject
    ProductRepository productRepository;

    @Inject
    ProductService productService;

    @Inject
    ProductMapper productMapper;

    @Override
    protected BaseRepository<ProductEntity> repository() {
        return productRepository;
    }

    @Override
    protected BaseService<ProductEntity, ProductReqDto, ProductResDto> service() {
        return productService;
    }

    @Override
    protected BaseMapper<ProductEntity, ProductReqDto, ProductResDto> mapper() {
        return productMapper;
    }

    @Override
    protected String moduleName() {
        return "product";
    }
}
