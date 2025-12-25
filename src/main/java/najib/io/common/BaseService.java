package najib.io.common;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class BaseService<E extends BaseEntity, Req, Res extends BaseResDto> {
    protected abstract BaseRepository<E> repository();

    protected abstract BaseMapper<E, Req, Res> mapper();

    protected abstract String moduleName();

    public List<E> findAll(int page, int size, String sortField, String sortOrder, Map<String, String> filters) {
        return repository().findPaginated(page, size, sortField, sortOrder, filters);
    }

    public E findById(UUID id) {
        E entity = repository().findById(id);
        if (entity == null) {
            throw new NotFoundException(moduleName() + " not found");
        }
        return entity;
    }

    @Transactional
    public E save(Req dto) {
        E entity = mapper().toEntity(dto, null);
        repository().persist(entity);
        return entity;
    }

    @Transactional
    public E update(UUID id, Req dto) {
        E entity = findById(id);
        return mapper().toEntity(dto, entity);
    }

    @Transactional
    public void remove(UUID id) {
        findById(id);
        boolean isDeleted = repository().softDelete(id);
        if (!isDeleted) {
            throw new BadRequestException(moduleName() + " not deleted");
        }
    }
}
