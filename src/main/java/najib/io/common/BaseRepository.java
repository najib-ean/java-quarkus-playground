package najib.io.common;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.persistence.NoResultException;

import java.time.ZonedDateTime;
import java.util.List;

public abstract class BaseRepository<T> implements PanacheRepository<T> {
    public List<T> findAllActive() {
        return find("where deletedAt is null").list();
    }

    public T findById(Long id) {
        try {
            return find("where id = ?1 and deletedAt is null", id).singleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public boolean softDelete(Long id) {
        return update("deletedAt=?1 where id=?2 and deletedAt is null", ZonedDateTime.now(), id) > 0;
    }
}
