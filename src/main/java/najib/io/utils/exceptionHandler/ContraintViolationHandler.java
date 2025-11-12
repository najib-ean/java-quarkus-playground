package najib.io.utils.exceptionHandler;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import najib.io.utils.apiResponse.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@Provider
@ApplicationScoped
public class ContraintViolationHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Response.Status status = Response.Status.BAD_REQUEST;

        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String key = violation.getPropertyPath().toString();
            if (key.contains(".")) {
                key = key.substring(key.lastIndexOf(".") + 1);
            }
            errors.put(key, violation.getMessage());
        }
        
        return Response.status(status).entity(ApiResponse.fail(status.getStatusCode(), "Validation Error", errors)).build();
    }
}
