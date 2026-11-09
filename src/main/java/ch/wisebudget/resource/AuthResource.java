package ch.wisebudget.resource;

import java.net.URI;
import java.time.LocalDate;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestQuery;

import ch.wisebudget.entity.Erwerbsstatus;
import ch.wisebudget.service.BenutzerService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class AuthResource {

    @Inject
    BenutzerService benutzerService;

    @Inject
    Template login;

    @Inject
    Template registrieren;

    @GET
    public Response startseite() {
        return Response.seeOther(URI.create("/budgets")).build();
    }

    @GET
    @Path("favicon.ico")
    public Response favicon() {
        return Response.noContent().build();
    }

    @GET
    @Path("login.html")
    public TemplateInstance loginSeite(@RestQuery boolean error, @RestQuery boolean registered) {
        return login.data("error", error)
                .data("registered", registered)
                .data("aktuellerBenutzer", null);
    }

    @GET
    @Path("registrieren")
    public TemplateInstance registrierenSeite() {
        return registrieren.data("kantone", benutzerService.alleKantone())
                .data("erwerbsstatusListe", Erwerbsstatus.values())
                .data("fehler", null)
                .data("aktuellerBenutzer", null);
    }

    @POST
    @Path("registrieren")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response registrierenAbsenden(@RestForm String name,
                                         @RestForm String email,
                                         @RestForm String passwort,
                                         @RestForm String geburtsdatum,
                                         @RestForm Erwerbsstatus erwerbsstatus,
                                         @RestForm Long kantonId) {
        try {
            LocalDate datum = (geburtsdatum != null && !geburtsdatum.isBlank())
                    ? LocalDate.parse(geburtsdatum)
                    : null;
            benutzerService.registrieren(name, email, passwort, datum, erwerbsstatus, kantonId);
            return Response.seeOther(URI.create("/login.html?registered=true")).build();
        } catch (IllegalArgumentException e) {
            TemplateInstance seite = registrieren.data("kantone", benutzerService.alleKantone())
                    .data("erwerbsstatusListe", Erwerbsstatus.values())
                    .data("fehler", e.getMessage())
                    .data("aktuellerBenutzer", null);
            return Response.ok(seite).build();
        }
    }

    @GET
    @Path("logout")
    public Response logout() {
        NewCookie abgelaufenesCookie = new NewCookie.Builder("quarkus-credential")
                .value("")
                .path("/")
                .maxAge(0)
                .build();
        return Response.seeOther(URI.create("/login.html"))
                .cookie(abgelaufenesCookie)
                .build();
    }
}
