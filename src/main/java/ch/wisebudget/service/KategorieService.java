package ch.wisebudget.service;

import java.util.List;

import ch.wisebudget.entity.Berechtigung;
import ch.wisebudget.entity.Budgetmitglied;
import ch.wisebudget.entity.Kategorie;
import ch.wisebudget.entity.Topf;
import ch.wisebudget.repository.KategorieRepository;
import ch.wisebudget.repository.TransaktionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class KategorieService {

    @Inject
    KategorieRepository kategorieRepository;

    @Inject
    TransaktionRepository transaktionRepository;

    @Inject
    BudgetService budgetService;

    public List<Kategorie> kategorienFuerBudget(Long budgetId) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.LESEN);
        return kategorieRepository.findByBudget(mitglied.getBudget());
    }

    @Transactional
    public Kategorie kategorieErstellen(Long budgetId, String name, Topf topf) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.SCHREIBEN);
        if (name == null || name.isBlank() || topf == null) {
            throw new IllegalArgumentException("Bitte Name und Topf für die Kategorie angeben.");
        }

        Kategorie kategorie = new Kategorie();
        kategorie.setBudget(mitglied.getBudget());
        kategorie.setName(name.trim());
        kategorie.setTopf(topf);
        kategorieRepository.persist(kategorie);
        return kategorie;
    }

    @Transactional
    public void kategorieUmbenennen(Long budgetId, Long kategorieId, String neuerName, Topf neuerTopf) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.SCHREIBEN);
        if (neuerName == null || neuerName.isBlank() || neuerTopf == null) {
            throw new IllegalArgumentException("Bitte Name und Topf angeben.");
        }

        Kategorie kategorie = findeKategorieImBudget(kategorieId, mitglied.getBudget().getId());
        kategorie.setName(neuerName.trim());
        kategorie.setTopf(neuerTopf);
    }

    @Transactional
    public void kategorieLoeschen(Long budgetId, Long kategorieId) {
        Budgetmitglied mitglied = budgetService.pruefeZugriff(budgetId, Berechtigung.SCHREIBEN);
        Kategorie kategorie = findeKategorieImBudget(kategorieId, mitglied.getBudget().getId());

        long anzahlTransaktionen = transaktionRepository.count("kategorie", kategorie);
        if (anzahlTransaktionen > 0) {
            throw new IllegalArgumentException("Kategorie kann nicht gelöscht werden, da noch Transaktionen damit verknüpft sind.");
        }

        kategorieRepository.delete(kategorie);
    }

    private Kategorie findeKategorieImBudget(Long kategorieId, Long budgetId) {
        Kategorie kategorie = kategorieRepository.findById(kategorieId);
        if (kategorie == null || !kategorie.getBudget().getId().equals(budgetId)) {
            throw new IllegalArgumentException("Kategorie wurde in diesem Budget nicht gefunden.");
        }
        return kategorie;
    }
}
