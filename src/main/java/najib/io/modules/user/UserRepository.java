package najib.io.modules.user;

import jakarta.enterprise.context.ApplicationScoped;
import najib.io.common.BaseRepository;

import java.util.Set;

@ApplicationScoped
public class UserRepository extends BaseRepository<UserEntity> {
    @Override
    protected Set<String> allowedSearchQueryFields() {
        return Set.of("firstName", "lastName", "address");
    }
}
