package najib.io.common;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper<Entity, Req, Res> {
    public abstract Entity toEntity(Req payload);

    public abstract Entity toEntity(Req payload, Entity entity);

    public abstract Res toResponse(Entity entity);

    public List<Res> toResponse(List<Entity> entities) {
        List<Res> dtos = new ArrayList<>();

        for (Entity entity : entities) {
            Res dto = toResponse(entity);
            dtos.add(dto);
        }

        return dtos;
    }
}
