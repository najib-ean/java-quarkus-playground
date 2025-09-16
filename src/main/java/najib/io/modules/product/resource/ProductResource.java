package najib.io.modules.product.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import najib.io.modules.product.dto.ProductReqDto;
import najib.io.modules.product.dto.ProductResDto;
import najib.io.modules.product.entity.ProductEntity;
import najib.io.modules.product.mapper.ProductMapper;
import najib.io.modules.product.service.ProductService;
import najib.io.utils.apiresponse.ApiResponse;

import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @Inject
    ProductMapper productMapper;

    @GET
    public ApiResponse<List<ProductResDto>> getAllProducts() {
        List<ProductEntity> products = productService.findAllProducts();
        return ApiResponse.ok(productMapper.toResponse(products));
    }

    @GET
    @Path("/{id}")
    public ApiResponse<ProductResDto> getProduct(@PathParam("id") Long productId) {
        ProductEntity product = productService.findById(productId);
        return ApiResponse.ok(productMapper.toResponse(product));
    }

    @POST
    public ApiResponse<ProductResDto> createProduct(ProductReqDto payload) {
        ProductEntity product = productService.saveProduct(payload);
        return ApiResponse.ok("Success create product", productMapper.toResponse(product));
    }

    @PATCH
    @Path("/{id}")
    public ApiResponse<ProductResDto> updateProduct(@PathParam("id") Long productId, ProductReqDto payload) {
        ProductEntity product = productService.updateProduct(productId, payload);
        return ApiResponse.ok("Success update product", productMapper.toResponse(product));
    }

    @DELETE
    @Path("/{id}")
    public String deleteProduct(@PathParam("id") String productId) {
        return "Product deleted.";
    }
}
