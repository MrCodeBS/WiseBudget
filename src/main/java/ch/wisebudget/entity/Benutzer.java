package ch.wisebudget.entity;

import java.time.LocalDate;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@UserDefinition
public class Benutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Username
    @Column(nullable = false, unique = true)
    private String email;

    @Password
    @Column(nullable = false)
    private String passwortHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rolle rolle;

    @Roles
    public String getRolleName() {
        return rolle != null ? rolle.name() : "USER";
    }

    @Column(nullable = false)
    private LocalDate geburtsdatum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Erwerbsstatus erwerbsstatus;

    @ManyToOne(optional = false)
    private Kanton kanton;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswortHash() {
        return passwortHash;
    }

    public void setPasswortHash(String passwortHash) {
        this.passwortHash = passwortHash;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Erwerbsstatus getErwerbsstatus() {
        return erwerbsstatus;
    }

    public void setErwerbsstatus(Erwerbsstatus erwerbsstatus) {
        this.erwerbsstatus = erwerbsstatus;
    }

    public Kanton getKanton() {
        return kanton;
    }

    public void setKanton(Kanton kanton) {
        this.kanton = kanton;
    }
}
