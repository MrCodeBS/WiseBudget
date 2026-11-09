package ch.wisebudget.resource;

import java.math.BigDecimal;
import java.net.URI;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import ch.wisebudget.entity.Erwerbsstatus;
import ch.wisebudget.service.BenutzerService;
import ch.wisebudget.service.SteuerregelService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/admin")
@RolesAllowed("ADMIN")
@Produces(MediaType.TEXT_HTML)
public class AdminResource {

    @Inject
    BenutzerService benutzerService;

    @Inject
    SteuerregelService steuerregelService;

    @Inject
    Template admin;

    @GET
    public TemplateInstance seite(@RestQuery String erfolg, @RestQuery String fehler) {
        return admin.data("aktuellerBenutzer", benutzerService.getAktuellerBenutzer())
                .data("benutzerListe", benutzerService.alleBenutzer())
                .data("steuerregeln", steuerregelService.alleSteuerregeln())
                .data("kantone", benutzerService.alleKantone())
                .data("erwerbsstatusListe", Erwerbsstatus.values())
                .data("erfolg", erfolg)
                .data("fehler", fehler);
    }

    @POST
    @Path("/steuerregeln")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response steuerregelErstellen(@RestForm Long kantonId,
                                         @RestForm Erwerbsstatus erwerbsstatus,
                                         @RestForm int alterVon,
                                         @RestForm int alterBis,
                                         @RestForm BigDecimal einkommenVon,
                                         @RestForm BigDecimal einkommenBis,
                                         @RestForm BigDecimal satzProzent,
                                         @RestForm int gueltigJahr) {
        try {
            steuerregelService.steuerregelErstellen(
                    kantonId, erwerbsstatus, alterVon, alterBis,
                    einkommenVon, einkommenBis, satzProzent, gueltigJahr
            );
            return Response.seeOther(URI.create("/admin?erfolg=Steuerregel+gespeichert.")).build();
        } catch (IllegalArgumentException e) {
            return Response.ok(seite(null, e.getMessage())).build();
        }
    }

    @POST
    @Path("/steuerregeln/{id}/loeschen")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response steuerregelLoeschen(@RestPath Long id) {
        steuerregelService.steuerregelLoeschen(id);
        return Response.seeOther(URI.create("/admin?erfolg=Steuerregel+geloescht.")).build();
    }
}
