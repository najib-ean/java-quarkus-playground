package najib.io.utils.exceptionHandler;

import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import najib.io.utils.apiResponse.ApiResponse;
import najib.io.utils.validation.ValidationException;
import org.jboss.logging.Logger;

@Provider
@ApplicationScoped
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Inject
    Logger logger;

    @Override
    public Response toResponse(Exception exception) {
        Response.Status status;
        String message = exception.getMessage();

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
                logger.info(message, exception);
                status = Response.Status.INTERNAL_SERVER_ERROR;
                message = "Internal Server Error";
            }
        }

        return Response.status(status).entity(ApiResponse.fail(status.getStatusCode(), message)).build();
    }
}
