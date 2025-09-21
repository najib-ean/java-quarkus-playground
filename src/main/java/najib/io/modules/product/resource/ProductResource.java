package najib.io.modules.product.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import najib.io.common.BasePaginationDto;
import najib.io.modules.product.dto.ProductReqDto;
import najib.io.modules.product.dto.ProductResDto;
import najib.io.modules.product.entity.ProductEntity;
import najib.io.modules.product.mapper.ProductMapper;
import najib.io.modules.product.repository.ProductRepository;
import najib.io.modules.product.service.ProductService;
import najib.io.utils.apiresponse.ApiResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @Inject
    ProductMapper productMapper;

    @Inject
    ProductRepository productRepository;

    @GET
    public ApiResponse<List<ProductResDto>> getAllProducts(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("sortField") @DefaultValue("updatedAt") String sortField,
            @QueryParam("sortOrder") @DefaultValue("DESC") String sortOrder,
            @Context UriInfo uriInfo
    ) {
        // Capture all query params
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>(uriInfo.getQueryParameters());

        // Remove known pagination/sort keys so only dynamic filters remain
        queryParams.remove("page");
        queryParams.remove("size");
        queryParams.remove("sortField");
        queryParams.remove("sortOrder");

        // Convert to Map<String, String> for 'search' from particular fields
        Map<String, String> filters = queryParams.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getFirst()));

        List<ProductEntity> products = productService.findAll(page, size, sortField, sortOrder, filters);

        BasePaginationDto paginationDto = new BasePaginationDto();
        paginationDto.page = page;
        paginationDto.size = size;
        paginationDto.totalItems = productRepository.countFiltered(filters);
        paginationDto.totalPages = (long) Math.ceil((double) paginationDto.totalItems / size);

        String message = products.isEmpty() ? "No products found" : "Success get all products";

        return ApiResponse.okPagination(message, productMapper.toResponse(products), paginationDto);
    }

    @GET
    @Path("/{id}")
    public ApiResponse<ProductResDto> getProduct(@PathParam("id") Long productId) {
        ProductEntity product = productService.findById(productId);
        return ApiResponse.ok("Success get product", productMapper.toResponse(product));
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
    public ApiResponse<String> deleteProduct(@PathParam("id") Long productId) {
        productService.deleteProduct(productId);
        return ApiResponse.ok("Success delete product with ID: " + productId);
    }
}
