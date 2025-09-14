package najib.io.modules.product.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import najib.io.modules.product.dto.ProductRequestDto;
import najib.io.modules.product.entity.ProductEntity;
import najib.io.modules.product.mapper.ProductMapper;
import najib.io.modules.product.repository.ProductRepository;
import najib.io.modules.product.validator.ProductValidator;

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
    public ProductEntity saveProduct(ProductRequestDto payload) {
        ProductValidator.validateCreate(payload);
        ProductEntity product = productMapper.toEntity(payload);
        productRepository.persist(product);
        return product;
    }

}
