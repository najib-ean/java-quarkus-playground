package najib.io.common;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import najib.io.utils.apiResponse.ApiResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class BaseResource<Entity extends BaseEntity, ReqDto, ResDto extends BaseResDto> {
    protected abstract BaseRepository<Entity> repository();

    protected abstract BaseService<Entity, ReqDto, ResDto> service();

    protected abstract BaseMapper<Entity, ReqDto, ResDto> mapper();

    protected abstract String moduleName();

    @GET
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(
                                    name = "Success Example",
                                    value = """
                                            {
                                              "success": true,
                                              "status_code": 200,
                                              "message": "Success get all",
                                              "data": "< ARRAY_OF_DATA_SAME_LIKE_GET_ONE >",
                                              "pagination": {
                                                "page": 1,
                                                "size": 10,
                                                "totalItems": 100,
                                                "totalPages": 10
                                              }
                                            }
                                            """
                            )
                    }
            )
    )
    public ApiResponse<List<ResDto>> getAll(
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

        return ApiResponse.okPagination(message, mapper().toResponse(entities), paginationDto);
    }

    @GET
    @Path("/{id}")
    public ApiResponse<ResDto> getOne(@PathParam("id") Long id) {
        Entity entity = service().findById(id);
        return ApiResponse.ok("Success get " + moduleName(), mapper().toResponse(entity));
    }

    @POST
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(
                                    name = "Success Example",
                                    value = """
                                            {
                                              "success": true,
                                              "status_code": 200,
                                              "message": "Success create",
                                              "data": "< DATA_SAME_LIKE_GET_ONE >"
                                            }
                                            """
                            )
                    }
            )
    )
    public ApiResponse<ResDto> create(ReqDto payload) {
        Entity entity = service().save(payload);
        return ApiResponse.ok("Success create " + moduleName(), mapper().toResponse(entity));
    }

    @PATCH
    @Path("/{id}")
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(
                                    name = "Success Example",
                                    value = """
                                            {
                                              "success": true,
                                              "status_code": 200,
                                              "message": "Success update",
                                              "data": "< DATA_SAME_LIKE_GET_ONE >"
                                            }
                                            """
                            )
                    }
            )
    )
    public ApiResponse<ResDto> update(@PathParam("id") Long id, ReqDto payload) {
        Entity entity = service().update(id, payload);
        return ApiResponse.ok("Success update " + moduleName(), mapper().toResponse(entity));
    }

    @DELETE
    @Path("/{id}")
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(
                                    name = "Success Example",
                                    value = """
                                            {
                                              "success": true,
                                              "status_code": 200,
                                              "message": "Success delete with ID: <id>"
                                            }
                                            """
                            )
                    }
            )
    )
    public ApiResponse<String> delete(@PathParam("id") Long id) {
        service().remove(id);
        return ApiResponse.ok("Success delete " + moduleName() + " with ID: " + id);
    }
}
