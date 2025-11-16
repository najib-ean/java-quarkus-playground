package najib.io.common;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import najib.io.utils.validation.OnCreate;
import najib.io.utils.validation.OnUpdate;

import java.util.List;
import java.util.Map;

public abstract class BaseService<Entity extends BaseEntity, ReqDto, ResDto extends BaseResDto> {
    protected abstract BaseRepository<Entity> repository();

    protected abstract BaseMapper<Entity, ReqDto, ResDto> mapper();

    protected abstract String moduleName();

    protected abstract BaseValidator<ReqDto> validator();

    protected List<Entity> findAll(int page, int size, String sortField, String sortOrder, Map<String, String> filters) {
        return repository().findPaginated(page, size, sortField, sortOrder, filters);
    }

    protected Entity findById(Long id) {
        Entity entity = repository().findById(id);
        if (entity == null) {
            throw new NotFoundException(moduleName() + " not found");
        }
        return entity;
    }

    @Transactional
    protected Entity save(@Valid @ConvertGroup(to = OnCreate.class) ReqDto dto) {
        Entity entity = mapper().toEntity(dto);
        repository().persist(entity);
        return entity;
    }

    @Transactional
    protected Entity update(Long id, @Valid @ConvertGroup(to = OnUpdate.class) ReqDto dto) {
        Entity entity = findById(id);
        return mapper().toEntity(dto, entity);
    }

    @Transactional
    protected void remove(Long id) {
        findById(id);
        boolean isDeleted = repository().softDelete(id);
        if (!isDeleted) {
            throw new BadRequestException(moduleName() + " not deleted");
        }
    }
}
