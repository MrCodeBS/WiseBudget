package ch.wisebudget.repository;

import ch.wisebudget.entity.Kanton;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KantonRepository implements PanacheRepository<Kanton> {

    public Kanton findByKuerzel(String kuerzel) {
        return find("kuerzel", kuerzel).firstResult();
    }
}
