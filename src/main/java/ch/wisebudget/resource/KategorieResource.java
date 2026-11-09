package ch.wisebudget.resource;

import java.net.URI;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import ch.wisebudget.entity.Berechtigung;
import ch.wisebudget.entity.Budgetmitglied;
import ch.wisebudget.entity.Topf;
import ch.wisebudget.service.BenutzerService;
import ch.wisebudget.service.BudgetService;
import ch.wisebudget.service.KategorieService;
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

@Path("/budgets/{id}/kategorien")
@Produces(MediaType.TEXT_HTML)
public class KategorieResource {

    @Inject
    KategorieService kategorieService;

    @Inject
    BudgetService budgetService;

    @Inject
    BenutzerService benutzerService;

    @Inject
    Template kategorien;

    @GET
    public TemplateInstance seite(@RestPath Long id,
                                  @RestQuery String erfolg,
                                  @RestQuery String fehler) {
        Budgetmitglied meineMitgliedschaft = budgetService.pruefeZugriff(id, Berechtigung.LESEN);
        return kategorien.data("aktuellerBenutzer", benutzerService.getAktuellerBenutzer())
                .data("meineMitgliedschaft", meineMitgliedschaft)
                .data("budget", meineMitgliedschaft.getBudget())
                .data("kategorienListe", kategorieService.kategorienFuerBudget(id))
                .data("toepfe", Topf.values())
                .data("schreibrecht", meineMitgliedschaft.getBerechtigung().hatMindestens(Berechtigung.SCHREIBEN))
                .data("erfolg", erfolg)
                .data("fehler", fehler);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response erstellen(@RestPath Long id,
                              @RestForm String name,
                              @RestForm Topf topf) {
        try {
            kategorieService.kategorieErstellen(id, name, topf);
            return Response.seeOther(URI.create("/budgets/" + id + "/kategorien?erfolg=Kategorie+erstellt.")).build();
        } catch (IllegalArgumentException e) {
            return Response.ok(seite(id, null, e.getMessage())).build();
        }
    }

    @POST
    @Path("/{kategorieId}/umbenennen")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response umbenennen(@RestPath Long id,
                               @RestPath Long kategorieId,
                               @RestForm String name,
                               @RestForm Topf topf) {
        try {
            kategorieService.kategorieUmbenennen(id, kategorieId, name, topf);
            return Response.seeOther(URI.create("/budgets/" + id + "/kategorien?erfolg=Kategorie+gespeichert.")).build();
        } catch (IllegalArgumentException e) {
            return Response.ok(seite(id, null, e.getMessage())).build();
        }
    }

    @POST
    @Path("/{kategorieId}/loeschen")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response loeschen(@RestPath Long id, @RestPath Long kategorieId) {
        try {
            kategorieService.kategorieLoeschen(id, kategorieId);
            return Response.seeOther(URI.create("/budgets/" + id + "/kategorien?erfolg=Kategorie+geloescht.")).build();
        } catch (IllegalArgumentException e) {
            return Response.ok(seite(id, null, e.getMessage())).build();
        }
    }
}
