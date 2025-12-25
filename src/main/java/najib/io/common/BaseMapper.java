package najib.io.common;

import java.util.List;
import java.util.function.Consumer;

public abstract class BaseMapper<Entity extends BaseEntity, Req, Res extends BaseResDto> {
    protected Entity toEntity(Req req, Entity entity) {
        if (entity == null) {
            entity = createEntity();
        }

        mapToEntity(req, entity);
        return entity;
    }

    public Res toResponse(Entity entity) {
        Res res = createResponse();
        mapBaseFields(entity, res);
        mapToResponse(entity, res);
        return res;
    }

    protected abstract Entity createEntity();

    protected abstract Res createResponse();

    protected void mapBaseFields(Entity entity, Res res) {
        res.setId(entity.getId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
    }

    protected abstract void mapToEntity(Req req, Entity entity);

    protected abstract void mapToResponse(Entity entity, Res res);

    public List<Res> toResponse(List<Entity> entities) {
        //        List<Res> dtos = new ArrayList<>();
        //
        //        for (Entity entity : entities) {
        //            dtos.add(toResponse(entity));
        //        }
        //
        //        return dtos;
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toResponse)
                .toList();
    }

    protected <T> void setIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
