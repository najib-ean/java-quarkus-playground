package najib.io.modules.product.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import najib.io.modules.product.entity.ProductEntity;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductEntity> {
    public ProductEntity findById(Long id) {
        System.out.println("Find product by id " + id);
        try {
            return find("where id = ?1 and deletedAt is null", id).singleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
