package ch.wisebudget.resource;

import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.RollbackException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class OptimisticLockExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        // Prüfen, ob eine OptimisticLockException direkt oder in einer RollbackException beim Commit vorliegt
        Throwable cause = exception;
        while (cause != null) {
            if (cause instanceof OptimisticLockException || cause instanceof RollbackException) {
                return Response.status(Response.Status.CONFLICT)
                        .type(MediaType.TEXT_HTML)
                        .entity("<div class=\"fehler\">Jemand anderes hat dieses Budget gerade geändert. Bitte Seite neu laden.</div>")
                        .build();
            }
            cause = cause.getCause();
        }

        if (exception instanceof WebApplicationException webEx) {
            return webEx.getResponse();
        }
        return Response.serverError()
                .type(MediaType.TEXT_PLAIN)
                .entity(exception.getMessage())
                .build();
    }
}
