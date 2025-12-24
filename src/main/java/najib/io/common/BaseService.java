package najib.io.common;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class BaseService<Entity extends BaseEntity, ReqDto, ResDto extends BaseResDto> {
    protected abstract BaseRepository<Entity> repository();

    protected abstract BaseMapper<Entity, ReqDto, ResDto> mapper();

    protected abstract String moduleName();

    protected abstract ResDto toDto();

    protected abstract void mapDto(Entity entity, ResDto resDto);

    protected ResDto toDto(Entity entity) {
        ResDto resDto = toDto();

        mapDto(entity, resDto);

        resDto.setId(entity.getId());
        resDto.setCreatedAt(entity.getCreatedAt());
        resDto.setUpdatedAt(entity.getUpdatedAt());

        return resDto;
    }

    protected List<ResDto> toDto(List<Entity> entities) {
        List<ResDto> dtos = new ArrayList<>();

        for (Entity entity : entities) {
            dtos.add(toDto(entity));
        }

        return dtos;
    }

    protected List<Entity> findAll(int page, int size, String sortField, String sortOrder, Map<String, String> filters) {
        return repository().findPaginated(page, size, sortField, sortOrder, filters);
    }

    protected Entity findById(UUID id) {
        Entity entity = repository().findById(id);
        if (entity == null) {
            throw new NotFoundException(moduleName() + " not found");
        }
        return entity;
    }

    @Transactional
    protected Entity save(ReqDto dto) {
        Entity entity = mapper().toEntity(dto);
        repository().persist(entity);
        return entity;
    }

    @Transactional
    protected Entity update(UUID id, ReqDto dto) {
        Entity entity = findById(id);
        return mapper().toEntity(dto, entity);
    }

    @Transactional
    protected void remove(UUID id) {
        findById(id);
        boolean isDeleted = repository().softDelete(id);
        if (!isDeleted) {
            throw new BadRequestException(moduleName() + " not deleted");
        }
    }
}
