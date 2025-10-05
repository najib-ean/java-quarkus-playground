package najib.io.common;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Base response structure for all API endpoints")
public abstract class SingleResponseSchema<T> {
    @Schema(examples = "true")
    private boolean success;
    @Schema(examples = "200")
    private int status_code;
    @Schema(examples = "Success get by ID")
    private String message;
    private T data;
}
