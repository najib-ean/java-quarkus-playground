package najib.io.utils.exceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import najib.io.utils.apiresponse.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@Provider
public class ConstraintViolationHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        for (ConstraintViolation<?> v : ex.getConstraintViolations()) {
            // Get field name, strip method path like createProduct.payload.name → name
            String field = v.getPropertyPath().toString();
            int lastDot = field.lastIndexOf('.');
            if (lastDot != -1) {
                field = field.substring(lastDot + 1);
            }
            errors.put(field, v.getMessage());
        }

        ApiResponse<Map<String, String>> body =
                ApiResponse.fail("Validation Error", errors);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(body)
                .build();
    }
}

