package najib.io.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class BaseMapper<Entity extends BaseEntity, Req, Res extends BaseResDto> {
    protected abstract Entity toEntity(Req payload, Entity entity);

    protected abstract Res toResponse();

    protected abstract void mapResponse(Entity entity, Res response);

    protected Entity toEntity(Req payload) {
        return toEntity(payload, null);
    }

    protected Res toResponse(Entity entity) {
        Res resDto = toResponse();

        mapResponse(entity, resDto);

        resDto.setId(entity.getId());
        resDto.setCreatedAt(entity.getCreatedAt());
        resDto.setUpdatedAt(entity.getUpdatedAt());

        return resDto;
    }

    protected List<Res> toResponse(List<Entity> entities) {
        List<Res> dtos = new ArrayList<>();

        for (Entity entity : entities) {
            dtos.add(toResponse(entity));
        }

        return dtos;
    }

    protected <T> void setIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
