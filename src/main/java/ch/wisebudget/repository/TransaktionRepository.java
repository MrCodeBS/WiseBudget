package ch.wisebudget.repository;

import java.time.LocalDate;
import java.util.List;

import ch.wisebudget.entity.Budget;
import ch.wisebudget.entity.Transaktion;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransaktionRepository implements PanacheRepository<Transaktion> {

    public List<Transaktion> findByBudgetUndZeitraum(Budget budget, LocalDate von, LocalDate bis) {
        return list("budget = ?1 and datum >= ?2 and datum <= ?3 order by datum desc, id desc", budget, von, bis);
    }
}
