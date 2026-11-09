package ch.wisebudget.repository;

import java.util.List;

import ch.wisebudget.entity.Budget;
import ch.wisebudget.entity.Einkommen;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EinkommenRepository implements PanacheRepository<Einkommen> {

    public List<Einkommen> findByBudgetUndMonat(Budget budget, String monat) {
        return list("budget = ?1 and monat = ?2", budget, monat);
    }

    public List<Einkommen> findByBudget(Budget budget) {
        return list("budget = ?1 order by monat desc", budget);
    }
}
