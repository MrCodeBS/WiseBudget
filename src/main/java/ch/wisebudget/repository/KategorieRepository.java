package ch.wisebudget.repository;

import java.util.List;

import ch.wisebudget.entity.Budget;
import ch.wisebudget.entity.Kategorie;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KategorieRepository implements PanacheRepository<Kategorie> {

    public List<Kategorie> findByBudget(Budget budget) {
        return list("budget", budget);
    }
}
