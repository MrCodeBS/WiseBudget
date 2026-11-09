package ch.wisebudget.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import ch.wisebudget.entity.Benutzer;
import ch.wisebudget.entity.Berechtigung;
import ch.wisebudget.entity.Budget;
import ch.wisebudget.entity.BudgetTyp;
import ch.wisebudget.entity.Budgetmitglied;
import ch.wisebudget.entity.Einkommen;
import ch.wisebudget.entity.Topf;
import ch.wisebudget.entity.Transaktion;
import ch.wisebudget.repository.BenutzerRepository;
import ch.wisebudget.repository.BudgetRepository;
import ch.wisebudget.repository.BudgetmitgliedRepository;
import ch.wisebudget.repository.EinkommenRepository;
import ch.wisebudget.repository.KategorieRepository;
import ch.wisebudget.repository.TransaktionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ForbiddenException;

@ApplicationScoped
public class BudgetService {

    @Inject
    BudgetRepository budgetRepository;

    @Inject
    BudgetmitgliedRepository budgetmitgliedRepository;

    @Inject
    BenutzerRepository benutzerRepository;

    @Inject
    KategorieRepository kategorieRepository;

    @Inject
    EinkommenRepository einkommenRepository;

    @Inject
    TransaktionRepository transaktionRepository;

    @Inject
    BenutzerService benutzerService;

    // Prüft, ob der eingeloggte Benutzer Mitglied im Budget ist und mindestens die geforderte Berechtigung besitzt
    public Budgetmitglied pruefeZugriff(Long budgetId, Berechtigung mindestBerechtigung) {
        Benutzer aktuellerBenutzer = benutzerService.getAktuellerBenutzer();
        Budget budget = budgetRepository.findById(budgetId);
        if (budget == null) {
            throw new ForbiddenException("Kein Zugriff auf dieses Budget.");
        }
        Budgetmitglied mitglied = budgetmitgliedRepository.findByBudgetUndBenutzer(budget, aktuellerBenutzer);
        if (mitglied == null || !mitglied.getBerechtigung().hatMindestens(mindestBerechtigung)) {
            throw new ForbiddenException("Keine ausreichende Berechtigung für dieses Budget.");
        }
        return mitglied;
    }

    public List<Budgetmitglied> meineBudgets() {
        Benutzer aktuellerBenutzer = benutzerService.getAktuellerBenutzer();
        return budgetmitgliedRepository.findByBenutzer(aktuellerBenutzer);
    }

    public List<Budgetmitglied> mitgliederAuflisten(Long budgetId) {
        Budgetmitglied mitglied = pruefeZugriff(budgetId, Berechtigung.LESEN);
        return budgetmitgliedRepository.findByBudget(mitglied.getBudget());
    }

    public BigDecimal berechneGesamtNetto(List<Einkommen> einkommenListe) {
        BigDecimal summe = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        for (Einkommen e : einkommenListe) {
            summe = summe.add(e.getNetto());
        }
        return summe;
    }

    public List<TopfUebersicht> berechneTopfUebersicht(Budget budget, BigDecimal gesamtNetto, List<Transaktion> transaktionen) {
        return List.of(
                erstelleTopfZeile(Topf.NEEDS, budget.getNeedsProzent(), gesamtNetto, transaktionen),
                erstelleTopfZeile(Topf.WANTS, budget.getWantsProzent(), gesamtNetto, transaktionen),
                erstelleTopfZeile(Topf.SPAREN, budget.getSparenProzent(), gesamtNetto, transaktionen)
        );
    }

    private TopfUebersicht erstelleTopfZeile(Topf topf, BigDecimal prozent, BigDecimal gesamtNetto, List<Transaktion> transaktionen) {
        BigDecimal zielbetrag = gesamtNetto.multiply(prozent)
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        BigDecimal ausgegeben = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        for (Transaktion tx : transaktionen) {
            if (tx.getKategorie().getTopf() == topf) {
                ausgegeben = ausgegeben.add(tx.getBetrag());
            }
        }
        BigDecimal verbleibend = zielbetrag.subtract(ausgegeben);
        return new TopfUebersicht(topf, prozent, zielbetrag, ausgegeben, verbleibend);
    }

    @Transactional
    public Budget budgetErstellen(String name, BudgetTyp typ, BigDecimal needsProzent,
                                  BigDecimal wantsProzent, BigDecimal sparenProzent) {
        Benutzer ersteller = benutzerService.getAktuellerBenutzer();
        BigDecimal needs = needsProzent != null ? needsProzent : new BigDecimal("50.00");
        BigDecimal wants = wantsProzent != null ? wantsProzent : new BigDecimal("30.00");
        BigDecimal sparen = sparenProzent != null ? sparenProzent : new BigDecimal("20.00");

        validiereBudgetDaten(name, typ, needs, wants, sparen);

        Budget budget = new Budget();
        budget.setName(name.trim());
        budget.setTyp(typ);
        budget.setNeedsProzent(needs);
        budget.setWantsProzent(wants);
        budget.setSparenProzent(sparen);
        budgetRepository.persist(budget);

        Budgetmitglied mitglied = new Budgetmitglied();
        mitglied.setBudget(budget);
        mitglied.setBenutzer(ersteller);
        mitglied.setBerechtigung(Berechtigung.BESITZER);
        budgetmitgliedRepository.persist(mitglied);

        return budget;
    }

    @Transactional
    public Budget budgetAktualisieren(Long budgetId, String name, BudgetTyp typ, BigDecimal needsProzent,
                                      BigDecimal wantsProzent, BigDecimal sparenProzent, Long erwarteteVersion) {
        Budgetmitglied mitglied = pruefeZugriff(budgetId, Berechtigung.BESITZER);
        Budget budget = mitglied.getBudget();

        if (erwarteteVersion != null && !erwarteteVersion.equals(budget.getVersion())) {
            throw new OptimisticLockException("Budget wurde zwischenzeitlich geändert.");
        }

        validiereBudgetDaten(name, typ, needsProzent, wantsProzent, sparenProzent);

        budget.setName(name.trim());
        budget.setTyp(typ);
        budget.setNeedsProzent(needsProzent);
        budget.setWantsProzent(wantsProzent);
        budget.setSparenProzent(sparenProzent);
        budgetRepository.getEntityManager().flush();
        return budget;
    }

    @Transactional
    public void budgetLoeschen(Long budgetId) {
        Budgetmitglied mitglied = pruefeZugriff(budgetId, Berechtigung.BESITZER);
        Budget budget = mitglied.getBudget();

        transaktionRepository.delete("budget", budget);
        einkommenRepository.delete("budget", budget);
        kategorieRepository.delete("budget", budget);
        budgetmitgliedRepository.delete("budget", budget);
        budgetRepository.delete(budget);
    }

    @Transactional
    public void mitgliedHinzufuegen(Long budgetId, String email, Berechtigung berechtigung) {
        Budgetmitglied besitzer = pruefeZugriff(budgetId, Berechtigung.BESITZER);
        if (email == null || email.isBlank() || berechtigung == null) {
            throw new IllegalArgumentException("Bitte E-Mail und Berechtigung angeben.");
        }

        Benutzer neuerBenutzer = benutzerRepository.findByEmail(email.trim().toLowerCase());
        if (neuerBenutzer == null) {
            throw new IllegalArgumentException("Kein Benutzer mit dieser E-Mail gefunden.");
        }

        Budget budget = besitzer.getBudget();
        if (budgetmitgliedRepository.findByBudgetUndBenutzer(budget, neuerBenutzer) != null) {
            throw new IllegalArgumentException("Dieser Benutzer ist bereits Mitglied im Budget.");
        }

        Budgetmitglied neuesMitglied = new Budgetmitglied();
        neuesMitglied.setBudget(budget);
        neuesMitglied.setBenutzer(neuerBenutzer);
        neuesMitglied.setBerechtigung(berechtigung);
        budgetmitgliedRepository.persist(neuesMitglied);
    }

    @Transactional
    public void berechtigungAendern(Long budgetId, Long mitgliedId, Berechtigung neueBerechtigung) {
        Budgetmitglied besitzer = pruefeZugriff(budgetId, Berechtigung.BESITZER);
        Budget budget = besitzer.getBudget();

        Budgetmitglied zielMitglied = budgetmitgliedRepository.findById(mitgliedId);
        if (zielMitglied == null || !zielMitglied.getBudget().getId().equals(budget.getId())) {
            throw new IllegalArgumentException("Mitglied nicht gefunden.");
        }

        // Letzten Besitzer schützen
        if (zielMitglied.getBerechtigung() == Berechtigung.BESITZER
                && neueBerechtigung != Berechtigung.BESITZER
                && budgetmitgliedRepository.countBesitzer(budget) <= 1) {
            throw new IllegalArgumentException("Ein Budget muss immer mindestens einen Besitzer haben.");
        }

        zielMitglied.setBerechtigung(neueBerechtigung);
    }

    @Transactional
    public void mitgliedEntfernen(Long budgetId, Long mitgliedId) {
        Budgetmitglied besitzer = pruefeZugriff(budgetId, Berechtigung.BESITZER);
        Budget budget = besitzer.getBudget();

        Budgetmitglied zielMitglied = budgetmitgliedRepository.findById(mitgliedId);
        if (zielMitglied == null || !zielMitglied.getBudget().getId().equals(budget.getId())) {
            throw new IllegalArgumentException("Mitglied nicht gefunden.");
        }

        // Letzten Besitzer schützen
        if (zielMitglied.getBerechtigung() == Berechtigung.BESITZER
                && budgetmitgliedRepository.countBesitzer(budget) <= 1) {
            throw new IllegalArgumentException("Der letzte Besitzer kann nicht aus dem Budget entfernt werden.");
        }

        budgetmitgliedRepository.delete(zielMitglied);
    }

    private void validiereBudgetDaten(String name, BudgetTyp typ, BigDecimal needsProzent,
                                      BigDecimal wantsProzent, BigDecimal sparenProzent) {
        if (name == null || name.isBlank() || typ == null
                || needsProzent == null || wantsProzent == null || sparenProzent == null) {
            throw new IllegalArgumentException("Bitte alle Budgetfelder ausfüllen.");
        }
        if (needsProzent.compareTo(BigDecimal.ZERO) < 0
                || wantsProzent.compareTo(BigDecimal.ZERO) < 0
                || sparenProzent.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Prozentwerte dürfen nicht negativ sein.");
        }
        BigDecimal summe = needsProzent.add(wantsProzent).add(sparenProzent);
        if (summe.compareTo(new BigDecimal("100")) != 0) {
            throw new IllegalArgumentException("Die Summe aus Needs, Wants und Sparen muss genau 100% ergeben.");
        }
    }
}
