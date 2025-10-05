package najib.io.common.responseSchema;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(description = "For pagination responses")
public abstract class PagedResponseSchema<T> {
    @Schema(examples = "true")
    private boolean success;
    @Schema(examples = "200")
    private int status_code;
    @Schema(examples = "Success get all")
    private String message;
    @Schema(examples = "[{...}, {...}, {...}]", description = "List of data items")
    private List<T> data;

    @Schema(implementation = PaginationSchema.class)
    private PaginationSchema pagination;

    private static class PaginationSchema {
        @Schema(examples = "1")
        private int page;
        @Schema(examples = "10")
        private int size;
        @Schema(examples = "100")
        private int totalItems;
        @Schema(examples = "10")
        private int totalPages;
    }
}
