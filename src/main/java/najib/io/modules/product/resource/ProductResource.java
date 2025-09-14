package najib.io.modules.product.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import najib.io.modules.product.dto.ProductRequestDto;
import najib.io.modules.product.dto.ProductResponseDto;
import najib.io.modules.product.entity.ProductEntity;
import najib.io.modules.product.mapper.ProductMapper;
import najib.io.modules.product.service.ProductService;
import najib.io.utils.apiresponse.ApiResponse;

import java.util.List;
import java.util.Map;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @Inject
    ProductMapper productMapper;

    @GET
    public ApiResponse<List<ProductResponseDto>> getAllProducts() {
        List<ProductEntity> products = productService.findAllProducts();
        return ApiResponse.ok(productMapper.toResponse(products));
    }

    @GET
    @Path("/{id}")
    public ApiResponse<ProductResponseDto> getProduct(@PathParam("id") Long productId) {
        ProductEntity product = productService.findById(productId);
        return ApiResponse.ok(productMapper.toResponse(product));
    }

    @POST
    public ApiResponse<ProductResponseDto> createProduct(ProductRequestDto payload) {
        ProductEntity productSaved = productService.saveProduct(payload);
        return ApiResponse.ok(productMapper.toResponse(productSaved));
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") String productId, Map<String, Object> payload) {
        return Response.ok("Has been deleted!").build();
    }

    @DELETE
    @Path("/{id}")
    public String deleteProduct(@PathParam("id") String productId) {
        return "Product deleted.";
    }
}
