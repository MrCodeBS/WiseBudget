package ch.wisebudget.resource;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.YearMonth;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;

import ch.wisebudget.entity.Berechtigung;
import ch.wisebudget.entity.Budgetmitglied;
import ch.wisebudget.entity.Transaktion;
import ch.wisebudget.service.BenutzerService;
import ch.wisebudget.service.BudgetService;
import ch.wisebudget.service.KategorieService;
import ch.wisebudget.service.TransaktionService;
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

@Path("/budgets/{id}/transaktionen")
@Produces(MediaType.TEXT_HTML)
public class TransaktionResource {

    @Inject
    TransaktionService transaktionService;

    @Inject
    KategorieService kategorieService;

    @Inject
    BudgetService budgetService;

    @Inject
    BenutzerService benutzerService;

    @Inject
    Template transaktionBearbeiten;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response buchen(@RestPath Long id,
                           @RestForm Long kategorieId,
                           @RestForm BigDecimal betrag,
                           @RestForm String datum,
                           @RestForm String beschreibung) {
        LocalDate buchungsdatum = (datum != null && !datum.isBlank()) ? LocalDate.parse(datum) : null;
        Transaktion tx = transaktionService.transaktionBuchen(id, kategorieId, betrag, buchungsdatum, beschreibung);
        String monat = YearMonth.from(tx.getDatum()).toString();
        return Response.seeOther(URI.create("/budgets/" + id + "?monat=" + monat + "&erfolg=Transaktion+gebucht.")).build();
    }

    @GET
    @Path("/{transaktionId}/bearbeiten")
    public TemplateInstance bearbeitenSeite(@RestPath Long id, @RestPath Long transaktionId) {
        Budgetmitglied meineMitgliedschaft = budgetService.pruefeZugriff(id, Berechtigung.SCHREIBEN);
        Transaktion tx = transaktionService.transaktionLaden(id, transaktionId);
        return transaktionBearbeiten.data("aktuellerBenutzer", benutzerService.getAktuellerBenutzer())
                .data("budget", meineMitgliedschaft.getBudget())
                .data("transaktion", tx)
                .data("kategorienListe", kategorieService.kategorienFuerBudget(id))
                .data("fehler", null);
    }

    @POST
    @Path("/{transaktionId}/bearbeiten")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response aktualisieren(@RestPath Long id,
                                  @RestPath Long transaktionId,
                                  @RestForm Long kategorieId,
                                  @RestForm BigDecimal betrag,
                                  @RestForm String datum,
                                  @RestForm String beschreibung) {
        try {
            LocalDate buchungsdatum = (datum != null && !datum.isBlank()) ? LocalDate.parse(datum) : null;
            transaktionService.transaktionAktualisieren(id, transaktionId, kategorieId, betrag, buchungsdatum, beschreibung);
            String monat = YearMonth.from(buchungsdatum).toString();
            return Response.seeOther(URI.create("/budgets/" + id + "?monat=" + monat + "&erfolg=Transaktion+gespeichert.")).build();
        } catch (IllegalArgumentException e) {
            Budgetmitglied meineMitgliedschaft = budgetService.pruefeZugriff(id, Berechtigung.SCHREIBEN);
            Transaktion tx = transaktionService.transaktionLaden(id, transaktionId);
            TemplateInstance seite = transaktionBearbeiten.data("aktuellerBenutzer", benutzerService.getAktuellerBenutzer())
                    .data("budget", meineMitgliedschaft.getBudget())
                    .data("transaktion", tx)
                    .data("kategorienListe", kategorieService.kategorienFuerBudget(id))
                    .data("fehler", e.getMessage());
            return Response.ok(seite).build();
        }
    }

    @POST
    @Path("/{transaktionId}/loeschen")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response loeschen(@RestPath Long id,
                             @RestPath Long transaktionId,
                             @RestForm String monat) {
        transaktionService.transaktionLoeschen(id, transaktionId);
        String zielMonat = (monat != null && !monat.isBlank()) ? monat : YearMonth.now().toString();
        return Response.seeOther(URI.create("/budgets/" + id + "?monat=" + zielMonat + "&erfolg=Transaktion+geloescht.")).build();
    }
}
