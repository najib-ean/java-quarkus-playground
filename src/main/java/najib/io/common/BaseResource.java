package najib.io.common;

import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import najib.io.utils.apiResponse.ApiResponse;
import najib.io.utils.apiResponse.PaginatedResponse;
import najib.io.utils.validation.OnCreate;
import najib.io.utils.validation.OnUpdate;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class BaseResource<Entity extends BaseEntity, ReqDto, ResDto extends BaseResDto> {
    protected abstract BaseRepository<Entity> repository();

    protected abstract BaseService<Entity, ReqDto, ResDto> service();

    protected abstract BaseMapper<Entity, ReqDto, ResDto> mapper();

    protected abstract String moduleName();

    @GET
    public PaginatedResponse<List<ResDto>> getAll(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("sortField") @DefaultValue("updatedAt") String sortField,
            @QueryParam("sortOrder") @DefaultValue("DESC") String sortOrder,
            @Context UriInfo uriInfo
    ) {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>(uriInfo.getQueryParameters());

        queryParams.remove("page");
        queryParams.remove("size");
        queryParams.remove("sortField");
        queryParams.remove("sortOrder");

        Map<String, String> filters = queryParams.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getFirst()));

        List<Entity> entities = service().findAll(page, size, sortField, sortOrder, filters);

        BasePaginationDto paginationDto = new BasePaginationDto();
        paginationDto.page = page;
        paginationDto.size = size;
        paginationDto.totalItems = repository().countFiltered(filters);
        paginationDto.totalPages = (long) Math.ceil((double) paginationDto.totalItems / size);

        String message = entities.isEmpty() ? "No data found" : "Success get all " + moduleName() + "s";

        return PaginatedResponse.success(message, service().toDto(entities), paginationDto);
    }

    @GET
    @Path("/{id}")
    public ApiResponse<ResDto> getOne(@PathParam("id") UUID id) {
        Entity entity = service().findById(id);
        return ApiResponse.success("Success get " + moduleName(), service().toDto(entity));
    }

    @POST
    public ApiResponse<ResDto> create(@Valid @ConvertGroup(to = OnCreate.class) ReqDto payload) {
        Entity entity = service().save(payload);
        return ApiResponse.success("Success create " + moduleName(), service().toDto(entity));
    }

    @PATCH
    @Path("/{id}")
    public ApiResponse<ResDto> update(@PathParam("id") UUID id, @Valid @ConvertGroup(to = OnUpdate.class) ReqDto payload) {
        Entity entity = service().update(id, payload);
        return ApiResponse.success("Success update " + moduleName(), service().toDto(entity));
    }

    @DELETE
    @Path("/{id}")
    public ApiResponse<String> delete(@PathParam("id") UUID id) {
        service().remove(id);
        return ApiResponse.success("Success delete " + moduleName() + " with ID: " + id);
    }
}
