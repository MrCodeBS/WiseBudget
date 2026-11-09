package ch.wisebudget.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Einkommen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Benutzer benutzer;

    @ManyToOne(optional = false)
    private Budget budget;

    @Column(nullable = false, length = 7)
    private String monat;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal brutto;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal steuer;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal netto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public String getMonat() {
        return monat;
    }

    public void setMonat(String monat) {
        this.monat = monat;
    }

    public BigDecimal getBrutto() {
        return brutto;
    }

    public void setBrutto(BigDecimal brutto) {
        this.brutto = brutto;
    }

    public BigDecimal getSteuer() {
        return steuer;
    }

    public void setSteuer(BigDecimal steuer) {
        this.steuer = steuer;
    }

    public BigDecimal getNetto() {
        return netto;
    }

    public void setNetto(BigDecimal netto) {
        this.netto = netto;
    }
}
