package ch.wisebudget.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ch.wisebudget.entity.Topf;

public record TopfUebersicht(
        Topf topf,
        BigDecimal prozent,
        BigDecimal zielbetrag,
        BigDecimal ausgegeben,
        BigDecimal verbleibend
) {
    public int auslastungProzent() {
        if (zielbetrag == null || zielbetrag.compareTo(BigDecimal.ZERO) <= 0) {
            return ausgegeben != null && ausgegeben.compareTo(BigDecimal.ZERO) > 0 ? 100 : 0;
        }
        int pct = ausgegeben.multiply(new BigDecimal("100"))
                .divide(zielbetrag, 0, RoundingMode.HALF_UP)
                .intValue();
        return Math.min(Math.max(pct, 0), 100);
    }

    public boolean istUeberzogen() {
        return verbleibend != null && verbleibend.compareTo(BigDecimal.ZERO) < 0;
    }
}
