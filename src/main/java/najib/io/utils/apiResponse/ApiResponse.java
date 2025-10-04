package najib.io.utils.apiResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import najib.io.common.BasePaginationDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    public boolean success;
    public int status_code;
    public String message;
    public T data;
    public Pagination pagination;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, int status, String message, T data) {
        this.success = success;
        this.status_code = status;
        this.message = message;
        this.data = data;
    }

    public static class Pagination {
        public int page;
        public int size;
        public long totalItems;
        public long totalPages;
    }

    public static <T> ApiResponse<T> ok(String message) {
        return new ApiResponse<>(true, 200, message, null);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, 200, "Success", data);
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(true, 200, message, data);
    }

    public static <T> ApiResponse<T> fail(int status, String message) {
        return new ApiResponse<>(false, status, message, null);
    }

    public static <T> ApiResponse<T> fail(int status, String message, T data) {
        return new ApiResponse<>(false, status, message, data);
    }

    public static <T> ApiResponse<T> okPagination(String message, T data, BasePaginationDto paginationDto) {
        ApiResponse<T> response = new ApiResponse<>(true, 200, message, data);

        ApiResponse.Pagination pagination = new ApiResponse.Pagination();
        pagination.page = paginationDto.page;
        pagination.size = paginationDto.size;
        pagination.totalItems = paginationDto.totalItems;
        pagination.totalPages = paginationDto.totalPages;

        response.pagination = pagination;

        return response;
    }
}
