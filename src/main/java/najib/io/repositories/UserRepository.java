package najib.io.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import najib.io.common.BaseRepository;
import najib.io.entities.UserEntity;

import java.util.Set;

@ApplicationScoped
public class UserRepository extends BaseRepository<UserEntity> {
    @Override
    protected Set<String> allowedSearchQueryFields() {
        return Set.of("firstName", "lastName", "address");
    }

    public UserEntity findByEmail(String email) {
        try {
            return find("WHERE email = ?1 AND deletedBy IS NULL", email).singleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
