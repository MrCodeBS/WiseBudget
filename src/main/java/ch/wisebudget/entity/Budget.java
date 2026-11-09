package ch.wisebudget.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BudgetTyp typ;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal needsProzent = new BigDecimal("50.00");

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal wantsProzent = new BigDecimal("30.00");

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal sparenProzent = new BigDecimal("20.00");

    @Version
    private Long version;

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

    public BudgetTyp getTyp() {
        return typ;
    }

    public void setTyp(BudgetTyp typ) {
        this.typ = typ;
    }

    public BigDecimal getNeedsProzent() {
        return needsProzent;
    }

    public void setNeedsProzent(BigDecimal needsProzent) {
        this.needsProzent = needsProzent;
    }

    public BigDecimal getWantsProzent() {
        return wantsProzent;
    }

    public void setWantsProzent(BigDecimal wantsProzent) {
        this.wantsProzent = wantsProzent;
    }

    public BigDecimal getSparenProzent() {
        return sparenProzent;
    }

    public void setSparenProzent(BigDecimal sparenProzent) {
        this.sparenProzent = sparenProzent;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
