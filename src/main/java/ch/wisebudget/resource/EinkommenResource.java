package ch.wisebudget.resource;

import java.math.BigDecimal;
import java.net.URI;
import java.time.YearMonth;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import ch.wisebudget.entity.Berechtigung;
import ch.wisebudget.entity.Budgetmitglied;
import ch.wisebudget.service.BenutzerService;
import ch.wisebudget.service.BudgetService;
import ch.wisebudget.service.EinkommenService;
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

@Path("/budgets/{id}/einkommen")
@Produces(MediaType.TEXT_HTML)
public class EinkommenResource {

    @Inject
    EinkommenService einkommenService;

    @Inject
    BudgetService budgetService;

    @Inject
    BenutzerService benutzerService;

    @Inject
    Template einkommen;

    @GET
    public TemplateInstance seite(@RestPath Long id,
                                  @RestQuery String erfolg,
                                  @RestQuery String fehler) {
        Budgetmitglied meineMitgliedschaft = budgetService.pruefeZugriff(id, Berechtigung.LESEN);
        return einkommen.data("aktuellerBenutzer", benutzerService.getAktuellerBenutzer())
                .data("meineMitgliedschaft", meineMitgliedschaft)
                .data("budget", meineMitgliedschaft.getBudget())
                .data("einkommenListe", einkommenService.einkommenFuerBudget(id))
                .data("aktuellerMonat", YearMonth.now().toString())
                .data("schreibrecht", meineMitgliedschaft.getBerechtigung().hatMindestens(Berechtigung.SCHREIBEN))
                .data("erfolg", erfolg)
                .data("fehler", fehler);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response erfassen(@RestPath Long id,
                             @RestForm String monat,
                             @RestForm BigDecimal brutto) {
        try {
            einkommenService.einkommenErfassen(id, monat, brutto);
            return Response.seeOther(URI.create("/budgets/" + id + "/einkommen?erfolg=Einkommen+berechnet+und+gespeichert.")).build();
        } catch (IllegalArgumentException e) {
            return Response.ok(seite(id, null, e.getMessage())).build();
        }
    }

    @POST
    @Path("/{einkommenId}/loeschen")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response loeschen(@RestPath Long id, @RestPath Long einkommenId) {
        try {
            einkommenService.einkommenLoeschen(id, einkommenId);
            return Response.seeOther(URI.create("/budgets/" + id + "/einkommen?erfolg=Einkommen+geloescht.")).build();
        } catch (IllegalArgumentException e) {
            return Response.ok(seite(id, null, e.getMessage())).build();
        }
    }
}
