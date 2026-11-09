package ch.wisebudget.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import ch.wisebudget.entity.Berechtigung;
import ch.wisebudget.entity.Budgetmitglied;
import ch.wisebudget.entity.Kategorie;
import ch.wisebudget.entity.Transaktion;
import ch.wisebudget.repository.KategorieRepository;
import ch.wisebudget.repository.TransaktionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TransaktionService {

    @Inject
    TransaktionRepository transaktionRepository;

    @Inject
    KategorieRepository kategorieRepository;

    @Inject
    BudgetService budgetService;

    public List<Transaktion> transaktionenFuerMonat(Long budgetId, YearMonth jahrMonat) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.LESEN);
        LocalDate von = jahrMonat.atDay(1);
        LocalDate bis = jahrMonat.atEndOfMonth();
        return transaktionRepository.findByBudgetUndZeitraum(mitglied.getBudget(), von, bis);
    }

    public Transaktion transaktionLaden(Long budgetId, Long transaktionId) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.SCHREIBEN);
        return findeTransaktionImBudget(transaktionId, mitglied.getBudget().getId());
    }

    @Transactional
    public Transaktion transaktionBuchen(Long budgetId, Long kategorieId, BigDecimal betrag,
                                         LocalDate datum, String beschreibung) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.SCHREIBEN);
        validiereTransaktionDaten(kategorieId, betrag, datum, beschreibung);

        Kategorie kategorie = findeKategorieImBudget(kategorieId, mitglied.getBudget().getId());

        Transaktion transaktion = new Transaktion();
        transaktion.setBudget(mitglied.getBudget());
        transaktion.setKategorie(kategorie);
        transaktion.setErfasstVon(mitglied.getBenutzer());
        transaktion.setBetrag(betrag.setScale(2, RoundingMode.HALF_UP));
        transaktion.setDatum(datum);
        transaktion.setBeschreibung(beschreibung.trim());

        transaktionRepository.persist(transaktion);
        return transaktion;
    }

    @Transactional
    public void transaktionAktualisieren(Long budgetId, Long transaktionId, Long kategorieId,
                                         BigDecimal betrag, LocalDate datum, String beschreibung) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.SCHREIBEN);
        validiereTransaktionDaten(kategorieId, betrag, datum, beschreibung);

        Transaktion transaktion = findeTransaktionImBudget(transaktionId, mitglied.getBudget().getId());
        Kategorie kategorie = findeKategorieImBudget(kategorieId, mitglied.getBudget().getId());

        transaktion.setKategorie(kategorie);
        transaktion.setBetrag(betrag.setScale(2, RoundingMode.HALF_UP));
        transaktion.setDatum(datum);
        transaktion.setBeschreibung(beschreibung.trim());
    }

    @Transactional
    public void transaktionLoeschen(Long budgetId, Long transaktionId) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.SCHREIBEN);
        Transaktion transaktion = findeTransaktionImBudget(transaktionId, mitglied.getBudget().getId());
        transaktionRepository.delete(transaktion);
    }

    private void validiereTransaktionDaten(Long kategorieId, BigDecimal betrag, LocalDate datum, String beschreibung) {
        if (kategorieId == null || betrag == null || betrag.compareTo(BigDecimal.ZERO) <= 0
                || datum == null || beschreibung == null || beschreibung.isBlank()) {
            throw new IllegalArgumentException("Bitte Kategorie, positiven Betrag, Datum und Beschreibung angeben.");
        }
    }

    private Kategorie findeKategorieImBudget(Long kategorieId, Long budgetId) {
        Kategorie kategorie = kategorieRepository.findById(kategorieId);
        if (kategorie == null || !kategorie.getBudget().getId().equals(budgetId)) {
            throw new IllegalArgumentException("Ungültige Kategorie für dieses Budget.");
        }
        return kategorie;
    }

    private Transaktion findeTransaktionImBudget(Long transaktionId, Long budgetId) {
        Transaktion transaktion = transaktionRepository.findById(transaktionId);
        if (transaktion == null || !transaktion.getBudget().getId().equals(budgetId)) {
            throw new IllegalArgumentException("Transaktion wurde in diesem Budget nicht gefunden.");
        }
        return transaktion;
    }
}
