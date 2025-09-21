package najib.io.modules.product.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import najib.io.modules.product.dto.ProductReqDto;
import najib.io.modules.product.entity.ProductEntity;
import najib.io.modules.product.mapper.ProductMapper;
import najib.io.modules.product.repository.ProductRepository;
import najib.io.modules.product.validator.ProductValidator;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Inject
    ProductMapper productMapper;

    public List<ProductEntity> findAll(int page, int size, String sortField, String sortOrder, Map<String, String> filters) {
        return productRepository.findPaginated(page, size, sortField, sortOrder, filters);
    }

    public List<ProductEntity> findAllProducts() {
        return productRepository.findAllActive();
    }

    public ProductEntity findById(Long id) {
        ProductEntity product = productRepository.findById(id);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        return product;
    }

    @Transactional
    public ProductEntity saveProduct(ProductReqDto payload) {
        ProductValidator.validateCreate(payload);
        ProductEntity product = productMapper.toEntity(payload);
        productRepository.persist(product);
        return product;
    }

    @Transactional
    public ProductEntity updateProduct(Long productId, ProductReqDto payload) {
        ProductValidator.validateUpdate(payload);
        ProductEntity productFound = findById(productId);
        return productMapper.toEntity(payload, productFound);
    }

    @Transactional
    public ProductEntity deleteProduct(Long productId) {
        ProductEntity product = findById(productId);
        boolean isDeleted = productRepository.softDelete(productId);
        if (!isDeleted) {
            throw new BadRequestException("Product not deleted");
        }
        return product;
    }
}
