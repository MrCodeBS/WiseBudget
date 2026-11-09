package ch.wisebudget.repository;

import java.math.BigDecimal;

import ch.wisebudget.entity.Erwerbsstatus;
import ch.wisebudget.entity.Kanton;
import ch.wisebudget.entity.Steuerregel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SteuerregelRepository implements PanacheRepository<Steuerregel> {

    public Steuerregel findPassendeRegel(Kanton kanton, Erwerbsstatus status, int alter, BigDecimal brutto, int jahr) {
        return find(
                "kanton = ?1 and erwerbsstatus = ?2 and alterVon <= ?3 and alterBis >= ?3 and einkommenVon <= ?4 and einkommenBis >= ?4 and gueltigJahr = ?5",
                kanton, status, alter, brutto, jahr
        ).firstResult();
    }
}
