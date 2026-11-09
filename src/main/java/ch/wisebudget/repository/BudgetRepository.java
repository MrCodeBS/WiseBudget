package ch.wisebudget.repository;

import ch.wisebudget.entity.Budget;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BudgetRepository implements PanacheRepository<Budget> {
}
