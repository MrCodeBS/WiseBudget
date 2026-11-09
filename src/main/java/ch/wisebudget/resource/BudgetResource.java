package ch.wisebudget.resource;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import ch.wisebudget.entity.Berechtigung;
import ch.wisebudget.entity.BudgetTyp;
import ch.wisebudget.entity.Budgetmitglied;
import ch.wisebudget.entity.Einkommen;
import ch.wisebudget.entity.Transaktion;
import ch.wisebudget.service.BenutzerService;
import ch.wisebudget.service.BudgetService;
import ch.wisebudget.service.EinkommenService;
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

@Path("/budgets")
@Produces(MediaType.TEXT_HTML)
public class BudgetResource {

    @Inject
    BudgetService budgetService;

    @Inject
    BenutzerService benutzerService;

    @Inject
    EinkommenService einkommenService;

    @Inject
    KategorieService kategorieService;

    @Inject
    TransaktionService transaktionService;

    @Inject
    Template budgets;

    @Inject
    Template budgetUebersicht;

    @Inject
    Template budgetMitglieder;

    @GET
    @Path("/{id}")
    public TemplateInstance uebersicht(@RestPath Long id,
                                       @RestQuery String monat,
                                       @RestQuery String erfolg,
                                       @RestQuery String fehler) {
        Budgetmitglied meineMitgliedschaft = budgetService.pruefeZugriff(id, Berechtigung.LESEN);
        YearMonth gewaehlterMonat = (monat != null && !monat.isBlank())
                ? YearMonth.parse(monat)
                : YearMonth.now();
        String monatString = gewaehlterMonat.toString();

        List<Einkommen> einkommenListe = einkommenService.einkommenFuerBudgetUndMonat(id, monatString);
        BigDecimal gesamtNetto = budgetService.berechneGesamtNetto(einkommenListe);
        List<Transaktion> transaktionen = transaktionService.transaktionenFuerMonat(id, gewaehlterMonat);

        return budgetUebersicht.data("aktuellerBenutzer", benutzerService.getAktuellerBenutzer())
                .data("meineMitgliedschaft", meineMitgliedschaft)
                .data("budget", meineMitgliedschaft.getBudget())
                .data("monat", monatString)
                .data("heute", LocalDate.now().toString())
                .data("gesamtNetto", gesamtNetto)
                .data("topfZeilen", budgetService.berechneTopfUebersicht(meineMitgliedschaft.getBudget(), gesamtNetto, transaktionen))
                .data("transaktionen", transaktionen)
                .data("kategorienListe", kategorieService.kategorienFuerBudget(id))
                .data("schreibrecht", meineMitgliedschaft.getBerechtigung().hatMindestens(Berechtigung.SCHREIBEN))
                .data("erfolg", erfolg)
                .data("fehler", fehler);
    }

    @GET
    public TemplateInstance liste(@RestQuery String erfolg, @RestQuery String fehler) {
        return budgets.data("aktuellerBenutzer", benutzerService.getAktuellerBenutzer())
                .data("mitgliedschaften", budgetService.meineBudgets())
                .data("budgetTypen", BudgetTyp.values())
                .data("erfolg", erfolg)
                .data("fehler", fehler);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response erstellen(@RestForm String name,
                              @RestForm BudgetTyp typ,
                              @RestForm BigDecimal needsProzent,
                              @RestForm BigDecimal wantsProzent,
                              @RestForm BigDecimal sparenProzent) {
        try {
            budgetService.budgetErstellen(name, typ, needsProzent, wantsProzent, sparenProzent);
            return Response.seeOther(URI.create("/budgets?erfolg=Budget+wurde+erstellt.")).build();
        } catch (IllegalArgumentException e) {
            TemplateInstance seite = budgets.data("aktuellerBenutzer", benutzerService.getAktuellerBenutzer())
                    .data("mitgliedschaften", budgetService.meineBudgets())
                    .data("budgetTypen", BudgetTyp.values())
                    .data("erfolg", null)
                    .data("fehler", e.getMessage());
            return Response.ok(seite).build();
        }
    }

    @GET
    @Path("/{id}/mitglieder")
    public TemplateInstance mitgliederSeite(@RestPath Long id,
                                            @RestQuery String erfolg,
                                            @RestQuery String fehler) {
        Budgetmitglied meineMitgliedschaft = budgetService.pruefeZugriff(id, Berechtigung.LESEN);
        return budgetMitglieder.data("aktuellerBenutzer", benutzerService.getAktuellerBenutzer())
                .data("meineMitgliedschaft", meineMitgliedschaft)
                .data("budget", meineMitgliedschaft.getBudget())
                .data("mitglieder", budgetService.mitgliederAuflisten(id))
                .data("berechtigungen", Berechtigung.values())
                .data("budgetTypen", BudgetTyp.values())
                .data("erfolg", erfolg)
                .data("fehler", fehler);
    }

    @POST
    @Path("/{id}/aktualisieren")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response aktualisieren(@RestPath Long id,
                                  @RestForm String name,
                                  @RestForm BudgetTyp typ,
                                  @RestForm BigDecimal needsProzent,
                                  @RestForm BigDecimal wantsProzent,
                                  @RestForm BigDecimal sparenProzent,
                                  @RestForm Long version) {
        try {
            budgetService.budgetAktualisieren(id, name, typ, needsProzent, wantsProzent, sparenProzent, version);
            return Response.seeOther(URI.create("/budgets/" + id + "/mitglieder?erfolg=Budget+gespeichert.")).build();
        } catch (IllegalArgumentException e) {
            return Response.ok(mitgliederSeite(id, null, e.getMessage())).build();
        }
    }

    @POST
    @Path("/{id}/loeschen")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response loeschen(@RestPath Long id) {
        budgetService.budgetLoeschen(id);
        return Response.seeOther(URI.create("/budgets?erfolg=Budget+wurde+geloescht.")).build();
    }

    @POST
    @Path("/{id}/mitglieder")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response mitgliedHinzufuegen(@RestPath Long id,
                                        @RestForm String email,
                                        @RestForm Berechtigung berechtigung) {
        try {
            budgetService.mitgliedHinzufuegen(id, email, berechtigung);
            return Response.seeOther(URI.create("/budgets/" + id + "/mitglieder?erfolg=Mitglied+hinzugefuegt.")).build();
        } catch (IllegalArgumentException e) {
            return Response.ok(mitgliederSeite(id, null, e.getMessage())).build();
        }
    }

    @POST
    @Path("/{id}/mitglieder/{mitgliedId}/berechtigung")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response berechtigungAendern(@RestPath Long id,
                                        @RestPath Long mitgliedId,
                                        @RestForm Berechtigung berechtigung) {
        try {
            budgetService.berechtigungAendern(id, mitgliedId, berechtigung);
            return Response.seeOther(URI.create("/budgets/" + id + "/mitglieder?erfolg=Berechtigung+gespeichert.")).build();
        } catch (IllegalArgumentException e) {
            return Response.ok(mitgliederSeite(id, null, e.getMessage())).build();
        }
    }

    @POST
    @Path("/{id}/mitglieder/{mitgliedId}/entfernen")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response mitgliedEntfernen(@RestPath Long id, @RestPath Long mitgliedId) {
        try {
            budgetService.mitgliedEntfernen(id, mitgliedId);
            return Response.seeOther(URI.create("/budgets/" + id + "/mitglieder?erfolg=Mitglied+entfernt.")).build();
        } catch (IllegalArgumentException e) {
            return Response.ok(mitgliederSeite(id, null, e.getMessage())).build();
        }
    }
}
