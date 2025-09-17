package najib.io.utils.exceptionHandler;

import io.quarkus.security.UnauthorizedException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import najib.io.utils.apiresponse.ApiResponse;
import najib.io.utils.validation.ValidationException;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        Response.Status status;
        String message = exception.getMessage();

        System.out.println(exception.getMessage());

        switch (exception) {
            case BadRequestException badRequestException -> status = Response.Status.BAD_REQUEST;
            case NotFoundException notFoundException -> status = Response.Status.NOT_FOUND;
            case UnauthorizedException unauthorizedException ->
                    status = Response.Status.UNAUTHORIZED;
            case ValidationException validationException -> {
                status = Response.Status.BAD_REQUEST;
                return Response.status(status).entity(ApiResponse.fail(status.getStatusCode(), "Validation Error", validationException.getErrors())).build();
            }
            default -> {
                status = Response.Status.INTERNAL_SERVER_ERROR;
                message = "Internal Server Error";
            }
        }

        return Response.status(status).entity(ApiResponse.fail(status.getStatusCode(), message)).build();
    }
}
