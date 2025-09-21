package najib.io.modules.product.repository;

import jakarta.enterprise.context.ApplicationScoped;
import najib.io.common.BaseRepository;
import najib.io.modules.product.entity.ProductEntity;

import java.util.Set;

@ApplicationScoped
public class ProductRepository extends BaseRepository<ProductEntity> {
    @Override
    protected Set<String> allowedFields() {
        return Set.of("name");
    }
}
