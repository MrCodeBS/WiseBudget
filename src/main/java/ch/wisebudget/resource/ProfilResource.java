package ch.wisebudget.resource;

import java.net.URI;
import java.time.LocalDate;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestQuery;

import ch.wisebudget.entity.Benutzer;
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
import jakarta.ws.rs.core.Response;

@Path("/profil")
@Produces(MediaType.TEXT_HTML)
public class ProfilResource {

    @Inject
    BenutzerService benutzerService;

    @Inject
    Template profil;

    @GET
    public TemplateInstance profilAnzeigen(@RestQuery boolean gespeichert) {
        Benutzer benutzer = benutzerService.getAktuellerBenutzer();
        return profil.data("aktuellerBenutzer", benutzer)
                .data("kantone", benutzerService.alleKantone())
                .data("erwerbsstatusListe", Erwerbsstatus.values())
                .data("gespeichert", gespeichert)
                .data("fehler", null);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response profilSpeichern(@RestForm String name,
                                    @RestForm String geburtsdatum,
                                    @RestForm Erwerbsstatus erwerbsstatus,
                                    @RestForm Long kantonId) {
        try {
            LocalDate datum = (geburtsdatum != null && !geburtsdatum.isBlank())
                    ? LocalDate.parse(geburtsdatum)
                    : null;
            benutzerService.profilAktualisieren(name, datum, erwerbsstatus, kantonId);
            return Response.seeOther(URI.create("/profil?gespeichert=true")).build();
        } catch (IllegalArgumentException e) {
            Benutzer benutzer = benutzerService.getAktuellerBenutzer();
            TemplateInstance seite = profil.data("aktuellerBenutzer", benutzer)
                    .data("kantone", benutzerService.alleKantone())
                    .data("erwerbsstatusListe", Erwerbsstatus.values())
                    .data("gespeichert", false)
                    .data("fehler", e.getMessage());
            return Response.ok(seite).build();
        }
    }
}
