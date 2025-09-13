package najib.io.utils.validation;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.util.Map;

@Deprecated
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(
                Map.of(
                        "status", Response.Status.BAD_REQUEST.getStatusCode(),
                        "message", "validation error",
                        "errors", e.getErrors()
                )
        ).build();
    }
}
