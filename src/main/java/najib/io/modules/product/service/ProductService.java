package najib.io.modules.product.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import najib.io.modules.product.dto.ProductReqDto;
import najib.io.modules.product.entity.ProductEntity;
import najib.io.modules.product.mapper.ProductMapper;
import najib.io.modules.product.repository.ProductRepository;

import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Inject
    ProductMapper productMapper;

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
        // todo -- make validation here
        ProductEntity product = productMapper.toEntity(payload);
        productRepository.persist(product);
        return product;
    }

    @Transactional
    public ProductEntity updateProduct(Long productId, ProductReqDto payload) {
        ProductEntity productFound = findById(productId);
        ProductEntity product = productMapper.toEntity(payload, productFound);
        productRepository.persist(product);
        return product;
    }
}
