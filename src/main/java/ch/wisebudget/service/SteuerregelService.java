package ch.wisebudget.service;

import java.math.BigDecimal;
import java.util.List;

import ch.wisebudget.entity.Erwerbsstatus;
import ch.wisebudget.entity.Kanton;
import ch.wisebudget.entity.Steuerregel;
import ch.wisebudget.repository.KantonRepository;
import ch.wisebudget.repository.SteuerregelRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SteuerregelService {

    @Inject
    SteuerregelRepository steuerregelRepository;

    @Inject
    KantonRepository kantonRepository;

    public List<Steuerregel> alleSteuerregeln() {
        return steuerregelRepository.listAll();
    }

    @Transactional
    public Steuerregel steuerregelErstellen(Long kantonId, Erwerbsstatus erwerbsstatus,
                                            int alterVon, int alterBis,
                                            BigDecimal einkommenVon, BigDecimal einkommenBis,
                                            BigDecimal satzProzent, int gueltigJahr) {
        Kanton kanton = kantonRepository.findById(kantonId);
        if (kanton == null || erwerbsstatus == null || einkommenVon == null
                || einkommenBis == null || satzProzent == null) {
            throw new IllegalArgumentException("Bitte alle Felder für die Steuerregel ausfüllen.");
        }
        if (alterVon < 0 || alterBis < alterVon || alterBis > 120
                || einkommenVon.compareTo(BigDecimal.ZERO) < 0
                || einkommenBis.compareTo(einkommenVon) < 0
                || einkommenBis.compareTo(new BigDecimal("9999999999.99")) > 0
                || satzProzent.compareTo(BigDecimal.ZERO) < 0
                || satzProzent.compareTo(new BigDecimal("100")) > 0
                || gueltigJahr < 2000 || gueltigJahr > 2100) {
            throw new IllegalArgumentException("Ungültige Bereichswerte für Alter, Einkommen, Steuersatz oder Jahr.");
        }

        Steuerregel regel = new Steuerregel();
        regel.setKanton(kanton);
        regel.setErwerbsstatus(erwerbsstatus);
        regel.setAlterVon(alterVon);
        regel.setAlterBis(alterBis);
        regel.setEinkommenVon(einkommenVon);
        regel.setEinkommenBis(einkommenBis);
        regel.setSatzProzent(satzProzent);
        regel.setGueltigJahr(gueltigJahr);

        steuerregelRepository.persist(regel);
        return regel;
    }

    @Transactional
    public void steuerregelLoeschen(Long id) {
        Steuerregel regel = steuerregelRepository.findById(id);
        if (regel != null) {
            steuerregelRepository.delete(regel);
        }
    }
}
