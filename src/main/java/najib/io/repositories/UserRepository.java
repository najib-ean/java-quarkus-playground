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
}
