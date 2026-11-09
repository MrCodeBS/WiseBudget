package ch.wisebudget.service;

import java.time.LocalDate;
import java.util.List;

import ch.wisebudget.entity.Benutzer;
import ch.wisebudget.entity.Erwerbsstatus;
import ch.wisebudget.entity.Kanton;
import ch.wisebudget.entity.Rolle;
import ch.wisebudget.repository.BenutzerRepository;
import ch.wisebudget.repository.KantonRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BenutzerService {

    @Inject
    BenutzerRepository benutzerRepository;

    @Inject
    KantonRepository kantonRepository;

    @Inject
    SecurityIdentity securityIdentity;

    public Benutzer getAktuellerBenutzer() {
        if (securityIdentity == null || securityIdentity.isAnonymous()) {
            throw new UnauthorizedException("Nicht eingeloggt.");
        }
        String email = securityIdentity.getPrincipal().getName();
        Benutzer benutzer = benutzerRepository.findByEmail(email);
        if (benutzer == null) {
            throw new UnauthorizedException("Benutzer nicht gefunden.");
        }
        return benutzer;
    }

    public List<Kanton> alleKantone() {
        return kantonRepository.listAll();
    }

    public List<Benutzer> alleBenutzer() {
        return benutzerRepository.listAll();
    }

    @Transactional
    public Benutzer registrieren(String name, String email, String passwort, LocalDate geburtsdatum,
                                 Erwerbsstatus erwerbsstatus, Long kantonId) {
        if (name == null || name.isBlank() || email == null || email.isBlank()
                || passwort == null || passwort.length() < 6 || geburtsdatum == null
                || erwerbsstatus == null || kantonId == null) {
            throw new IllegalArgumentException("Bitte alle Felder gültig ausfüllen (Passwort mind. 6 Zeichen).");
        }

        String normalisierteEmail = email.trim().toLowerCase();
        if (benutzerRepository.findByEmail(normalisierteEmail) != null) {
            throw new IllegalArgumentException("Diese E-Mail-Adresse ist bereits registriert.");
        }

        Kanton kanton = kantonRepository.findById(kantonId);
        if (kanton == null) {
            throw new IllegalArgumentException("Ungültiger Kanton ausgewählt.");
        }

        Benutzer benutzer = new Benutzer();
        benutzer.setName(name.trim());
        benutzer.setEmail(normalisierteEmail);
        benutzer.setPasswortHash(BcryptUtil.bcryptHash(passwort));
        benutzer.setRolle(Rolle.USER);
        benutzer.setGeburtsdatum(geburtsdatum);
        benutzer.setErwerbsstatus(erwerbsstatus);
        benutzer.setKanton(kanton);

        benutzerRepository.persist(benutzer);
        return benutzer;
    }

    @Transactional
    public void profilAktualisieren(String name, LocalDate geburtsdatum, Erwerbsstatus erwerbsstatus, Long kantonId) {
        if (name == null || name.isBlank() || geburtsdatum == null || erwerbsstatus == null || kantonId == null) {
            throw new IllegalArgumentException("Bitte alle Felder ausfüllen.");
        }

        Benutzer benutzer = getAktuellerBenutzer();
        Kanton kanton = kantonRepository.findById(kantonId);
        if (kanton == null) {
            throw new IllegalArgumentException("Ungültiger Kanton ausgewählt.");
        }

        benutzer.setName(name.trim());
        benutzer.setGeburtsdatum(geburtsdatum);
        benutzer.setErwerbsstatus(erwerbsstatus);
        benutzer.setKanton(kanton);
    }
}
