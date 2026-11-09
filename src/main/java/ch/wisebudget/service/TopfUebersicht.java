package ch.wisebudget.service;

import java.math.BigDecimal;

import ch.wisebudget.entity.Topf;

public record TopfUebersicht(
        Topf topf,
        BigDecimal prozent,
        BigDecimal zielbetrag,
        BigDecimal ausgegeben,
        BigDecimal verbleibend
) {
}
