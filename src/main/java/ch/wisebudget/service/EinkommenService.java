package ch.wisebudget.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.List;

import ch.wisebudget.entity.Benutzer;
import ch.wisebudget.entity.Berechtigung;
import ch.wisebudget.entity.Budgetmitglied;
import ch.wisebudget.entity.Einkommen;
import ch.wisebudget.entity.Steuerregel;
import ch.wisebudget.repository.EinkommenRepository;
import ch.wisebudget.repository.SteuerregelRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EinkommenService {

    private static final BigDecimal AHV_ALV_SATZ = new BigDecimal("6.40");
    private static final BigDecimal HUNDERT = new BigDecimal("100");
    private static final BigDecimal MAX_BETRAG = new BigDecimal("9999999999.99");

    @Inject
    EinkommenRepository einkommenRepository;

    @Inject
    SteuerregelRepository steuerregelRepository;

    @Inject
    BudgetService budgetService;

    public List<Einkommen> einkommenFuerBudget(Long budgetId) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.LESEN);
        return einkommenRepository.findByBudget(mitglied.getBudget());
    }

    public List<Einkommen> einkommenFuerBudgetUndMonat(Long budgetId, String monat) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.LESEN);
        return einkommenRepository.findByBudgetUndMonat(mitglied.getBudget(), monat);
    }

    @Transactional
    public Einkommen einkommenErfassen(Long budgetId, String monat, BigDecimal brutto) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.SCHREIBEN);
        Einkommen einkommen = berechneEinkommen(mitglied.getBenutzer(), monat, brutto);
        einkommen.setBudget(mitglied.getBudget());
        einkommenRepository.persist(einkommen);
        return einkommen;
    }

    @Transactional
    public void einkommenLoeschen(Long budgetId, Long einkommenId) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.SCHREIBEN);
        Einkommen einkommen = einkommenRepository.findById(einkommenId);
        if (einkommen == null || !einkommen.getBudget().getId().equals(mitglied.getBudget().getId())) {
            throw new IllegalArgumentException("Einkommen nicht gefunden.");
        }
        einkommenRepository.delete(einkommen);
    }

    // Berechnet Steuer + AHV/IV/EO/ALV anhand von Kanton, Status, Alter am 1. Tag des Monats und Einkommensstufe
    public Einkommen berechneEinkommen(Benutzer benutzer, String monat, BigDecimal brutto) {
        if (monat == null || monat.isBlank() || brutto == null || brutto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Bitte gültigen Monat (YYYY-MM) und ein positives Bruttoeinkommen eingeben.");
        }

        YearMonth jahrMonat;
        try {
            jahrMonat = YearMonth.parse(monat.trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Ungültiges Monatsformat. Erwartet wird YYYY-MM.");
        }

        LocalDate ersterTag = jahrMonat.atDay(1);
        int alter = Period.between(benutzer.getGeburtsdatum(), ersterTag).getYears();

        Steuerregel regel = null;
        if (brutto.compareTo(MAX_BETRAG) <= 0) {
            regel = steuerregelRepository.findPassendeRegel(
                    benutzer.getKanton(),
                    benutzer.getErwerbsstatus(),
                    alter,
                    brutto,
                    ersterTag.getYear()
            );
        }

        if (regel == null) {
            throw new IllegalArgumentException(
                    "Keine passende Steuerregel gefunden für Kanton " + benutzer.getKanton().getKuerzel()
                            + ", Status " + benutzer.getErwerbsstatus()
                            + ", Alter " + alter
                            + " und Bruttoeinkommen " + brutto + " CHF im Jahr " + ersterTag.getYear() + "."
            );
        }

        // AHV/IV/EO + ALV Abzug (6.4%) gilt ab dem 1. Januar nach dem 17. Geburtstag
        LocalDate ahvPflichtAb = LocalDate.of(benutzer.getGeburtsdatum().plusYears(17).getYear() + 1, 1, 1);
        BigDecimal gesamtSatzProzent = regel.getSatzProzent();
        if (!ersterTag.isBefore(ahvPflichtAb)) {
            gesamtSatzProzent = gesamtSatzProzent.add(AHV_ALV_SATZ);
        }

        BigDecimal steuerUndAbzuege = brutto.multiply(gesamtSatzProzent)
                .divide(HUNDERT, 2, RoundingMode.HALF_UP);
        BigDecimal netto = brutto.subtract(steuerUndAbzuege);

        Einkommen einkommen = new Einkommen();
        einkommen.setBenutzer(benutzer);
        einkommen.setMonat(jahrMonat.toString());
        einkommen.setBrutto(brutto.setScale(2, RoundingMode.HALF_UP));
        einkommen.setSteuer(steuerUndAbzuege);
        einkommen.setNetto(netto);
        return einkommen;
    }
}
