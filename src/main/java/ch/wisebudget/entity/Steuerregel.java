package ch.wisebudget.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Steuerregel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Kanton kanton;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Erwerbsstatus erwerbsstatus;

    @Column(nullable = false)
    private int alterVon;

    @Column(nullable = false)
    private int alterBis;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal einkommenVon;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal einkommenBis;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal satzProzent;

    @Column(nullable = false)
    private int gueltigJahr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kanton getKanton() {
        return kanton;
    }

    public void setKanton(Kanton kanton) {
        this.kanton = kanton;
    }

    public Erwerbsstatus getErwerbsstatus() {
        return erwerbsstatus;
    }

    public void setErwerbsstatus(Erwerbsstatus erwerbsstatus) {
        this.erwerbsstatus = erwerbsstatus;
    }

    public int getAlterVon() {
        return alterVon;
    }

    public void setAlterVon(int alterVon) {
        this.alterVon = alterVon;
    }

    public int getAlterBis() {
        return alterBis;
    }

    public void setAlterBis(int alterBis) {
        this.alterBis = alterBis;
    }

    public BigDecimal getEinkommenVon() {
        return einkommenVon;
    }

    public void setEinkommenVon(BigDecimal einkommenVon) {
        this.einkommenVon = einkommenVon;
    }

    public BigDecimal getEinkommenBis() {
        return einkommenBis;
    }

    public void setEinkommenBis(BigDecimal einkommenBis) {
        this.einkommenBis = einkommenBis;
    }

    public BigDecimal getSatzProzent() {
        return satzProzent;
    }

    public void setSatzProzent(BigDecimal satzProzent) {
        this.satzProzent = satzProzent;
    }

    public int getGueltigJahr() {
        return gueltigJahr;
    }

    public void setGueltigJahr(int gueltigJahr) {
        this.gueltigJahr = gueltigJahr;
    }
}
