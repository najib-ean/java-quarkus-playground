package najib.io.utils.apiResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import najib.io.common.BasePaginationDto;

/**
 *
 * This class is for schema in swagger-ui so that able to catch the Pagination.
 * Can not inherite from ApiResponse class.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginatedResponse<T> {
    public boolean success;
    public int status_code;
    public String message;
    public T data;
    public BasePaginationDto pagination;

    public PaginatedResponse(boolean success, int status, String message, T data, BasePaginationDto pagination) {
        this.success = success;
        this.status_code = status;
        this.message = message;
        this.data = data;
        this.pagination = pagination;
    }

    public static <T> PaginatedResponse<T> success(String message, T data, BasePaginationDto pagination) {
        return new PaginatedResponse<T>(true, 200, message, data, pagination);
    }
}
