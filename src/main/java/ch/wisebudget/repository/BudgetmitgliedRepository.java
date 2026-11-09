package ch.wisebudget.repository;

import java.util.List;

import ch.wisebudget.entity.Benutzer;
import ch.wisebudget.entity.Berechtigung;
import ch.wisebudget.entity.Budget;
import ch.wisebudget.entity.Budgetmitglied;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BudgetmitgliedRepository implements PanacheRepository<Budgetmitglied> {

    public List<Budgetmitglied> findByBenutzer(Benutzer benutzer) {
        return list("benutzer", benutzer);
    }

    public List<Budgetmitglied> findByBudget(Budget budget) {
        return list("budget", budget);
    }

    public Budgetmitglied findByBudgetUndBenutzer(Budget budget, Benutzer benutzer) {
        return find("budget = ?1 and benutzer = ?2", budget, benutzer).firstResult();
    }

    public long countBesitzer(Budget budget) {
        return count("budget = ?1 and berechtigung = ?2", budget, Berechtigung.BESITZER);
    }
}
