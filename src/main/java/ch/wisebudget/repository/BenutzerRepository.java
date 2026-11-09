package ch.wisebudget.repository;

import ch.wisebudget.entity.Benutzer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BenutzerRepository implements PanacheRepository<Benutzer> {

    public Benutzer findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
